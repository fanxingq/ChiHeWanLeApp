package com.qust.chihewanleapp.tuan;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.entity.Goods;
import com.qust.chihewanleapp.entity.Shop;
import com.squareup.picasso.Picasso;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsDetailActivity extends Activity{

	@ViewInject(R.id.goods_image)
	private ImageView goodsImg;   //ʳ��ͼƬ
	@ViewInject(R.id.goods_title)
	private TextView goodTitle;   //����
	@ViewInject(R.id.goods_detail_food)
	private TextView goodsDesc;   //�����⣬��ϸ����
	@ViewInject(R.id.shop_title)
	private TextView shopTitle;   //������
	@ViewInject(R.id.shop_tel)
	private TextView shopTel;      //������ϵ��ʽ
	@ViewInject(R.id.goods_price)
	private TextView goodsPrice;   //�۸�
	@ViewInject(R.id.goods_old_price)
	private TextView oldPrice;  
	@ViewInject(R.id.tv_more_detail_web_view)
	private WebView tv_more_detail_web_view;  //��������web
	@ViewInject(R.id.wv_gn_warm_prompt)
	private WebView wv_gn_warm_prompt;    //��ܰ��ʾweb
	private Goods goods;
	@ViewInject(R.id.btn_buy_now)   //��������
	private Button buyNow;
	private String goodsId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuan_goods_detail);
		ViewUtils.inject(this);
		//ʵ��oldPrice���л���Ч��
		oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		//��ҳ����Ӧ��Ļ,������ʾ
		WebSettings webSettings=tv_more_detail_web_view.getSettings();
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		WebSettings webSettings1=wv_gn_warm_prompt.getSettings();
		webSettings1.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		
		Bundle bundle=getIntent().getExtras();
		if (bundle!=null) {
			goods=(Goods) bundle.get("goods");
			goodsId=goods.getId();
		}
		if (goods!=null) {
			//����ҳ�������е�����
			updateTitleImage();
			updateGoodsInfo();
			updateShopInfo();
			updateMoreDetails();
		}
		
	}
	//����
	@OnClick({R.id.shop_tel_index,R.id.good_detail_back,R.id.btn_buy_now})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.shop_tel_index:   //�绰
			Intent callin=new Intent(Intent.ACTION_DIAL);
			callin.setData(Uri.parse("tel:"+goods.getShop().getShopTel()));
			startActivity(callin);
			break;
		case R.id.good_detail_back:  //����
			finish();
			break;
		case R.id.btn_buy_now:       //��������
			putGoodsOrder();
			break;

		default:
			break;
		}
	}

	/**
	 * �򶩵��������Ʒ
	 */
	private void putGoodsOrder(){
		
		Intent intent=new Intent(GoodsDetailActivity.this, OrderNow.class);
		//���ݲ���
		Bundle bundle=new Bundle();
		bundle.putString("sortTitle",goodTitle.getText().toString());
		bundle.putString("desc", goodsDesc.getText().toString());
		bundle.putString("price",goods.getPrice());	
		bundle.putString("goodsid", goodsId);
		intent.putExtras(bundle);		
		startActivity(intent);
	}
	
	
	private void updateMoreDetails() {
		String[] data=htmlSub(goods.getDetail());
		tv_more_detail_web_view.loadDataWithBaseURL("", data[0], "text/html", "utf-8", "");
		wv_gn_warm_prompt.loadDataWithBaseURL("", data[1], "text/html", "utf-8", "");
	}
	
	/**
	 * ������Ʒ��Ϣ������HTML���ַ�������ȡ��������
	 * <div class="prodetail-sp"><h4 style="background:#ff6600">
	 * ���������顿</h4><p class="ti">��ȯ������������һ�ݣ�10��Լ2.5������ˮ������8ѡһ
	 * ����ܰ��ʾ��</h4><ul><li><p>�����Ź��������͵�ַΪ�ൺ�Ƽ���ѧ��������ͣ�&nbmp;&nbmp;</p><>
	 * ����Ʒչʾ��</h4><p><strong>������</strong></p><p class="tc"><img src="http://img.">����Ϊ����������ṩ��õķ���!</p></div>
	 */
	public String[] htmlSub(String html){
		char[] str=html.toCharArray();
		int len =str.length;
		Log.i("TAG", "������"+len);
		int n=0;
		String[] data=new String[3];
		int oneIndex=0;
		int secIndex=1;
		int thiIndex=2;
		for (int i = 0; i < len; i++) {
			if (str[i]=='��') {
				n++;
				if (n==1) oneIndex=i;
				if (n==2) secIndex=i;
				if (n==3) thiIndex=i;
			}
		}
		if (oneIndex>0 && secIndex>1 && thiIndex>2) {
			data[0]=html.substring(oneIndex,secIndex);
			data[1]=html.substring(secIndex,thiIndex);
			data[2]=html.substring(thiIndex,html.length()-6);
			
		}
		
		return data;
	}

	/**
	 * �̼���Ϣ
	 */
	private void updateGoodsInfo() {
		
		Shop shop=goods.getShop();
		shopTitle.setText(shop.getShopName());  //�̼�����
		shopTel.setText(shop.getShopTel());  //�̼���ϵ��ʽ
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	private void updateShopInfo() {
		
		goodsPrice.setText("��"+goods.getPrice());  //���ü�Ǯ
		goodTitle.setText(goods.getSortTitle());   //���ñ���
		goodsDesc.setText(goods.getTitle());   //������ϸ��Ϣ
	    oldPrice.setText("��"+goods.getValue());  //ԭ��
		
	}

	/**
	 * ������Ʒ�ı���ͼƬ
	 */
	private void updateTitleImage() {
	Picasso.with(this).load("file:///android_asset/" + goods.getImgUrl()).placeholder(R.drawable.tuan_item).into(goodsImg);
		
	}
}

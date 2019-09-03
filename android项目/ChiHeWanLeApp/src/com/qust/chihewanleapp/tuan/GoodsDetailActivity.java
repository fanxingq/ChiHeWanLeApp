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
	private ImageView goodsImg;   //食物图片
	@ViewInject(R.id.goods_title)
	private TextView goodTitle;   //标题
	@ViewInject(R.id.goods_detail_food)
	private TextView goodsDesc;   //副标题，详细描述
	@ViewInject(R.id.shop_title)
	private TextView shopTitle;   //商铺名
	@ViewInject(R.id.shop_tel)
	private TextView shopTel;      //商铺联系方式
	@ViewInject(R.id.goods_price)
	private TextView goodsPrice;   //价格
	@ViewInject(R.id.goods_old_price)
	private TextView oldPrice;  
	@ViewInject(R.id.tv_more_detail_web_view)
	private WebView tv_more_detail_web_view;  //本单详情web
	@ViewInject(R.id.wv_gn_warm_prompt)
	private WebView wv_gn_warm_prompt;    //温馨提示web
	private Goods goods;
	@ViewInject(R.id.btn_buy_now)   //立即购买
	private Button buyNow;
	private String goodsId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuan_goods_detail);
		ViewUtils.inject(this);
		//实现oldPrice的中划线效果
		oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		//网页自适应屏幕,居中显示
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
			//更新页面上所有的内容
			updateTitleImage();
			updateGoodsInfo();
			updateShopInfo();
			updateMoreDetails();
		}
		
	}
	//监听
	@OnClick({R.id.shop_tel_index,R.id.good_detail_back,R.id.btn_buy_now})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.shop_tel_index:   //电话
			Intent callin=new Intent(Intent.ACTION_DIAL);
			callin.setData(Uri.parse("tel:"+goods.getShop().getShopTel()));
			startActivity(callin);
			break;
		case R.id.good_detail_back:  //返回
			finish();
			break;
		case R.id.btn_buy_now:       //立即购买
			putGoodsOrder();
			break;

		default:
			break;
		}
	}

	/**
	 * 向订单中添加商品
	 */
	private void putGoodsOrder(){
		
		Intent intent=new Intent(GoodsDetailActivity.this, OrderNow.class);
		//传递参数
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
	 * 更多商品信息，解析HTML的字符串，获取以下内容
	 * <div class="prodetail-sp"><h4 style="background:#ff6600">
	 * 【本单详情】</h4><p class="ti">屏券享受披萨蛋糕一份，10寸约2.5磅精美水果蛋糕8选一
	 * 【温馨提示】</h4><ul><li><p>本次团购仅限配送地址为青岛科技大学，免费配送；&nbmp;&nbmp;</p><>
	 * 【精品展示】</h4><p><strong>优乐美</strong></p><p class="tc"><img src="http://img.">力争为广大消费者提供最好的服务!</p></div>
	 */
	public String[] htmlSub(String html){
		char[] str=html.toCharArray();
		int len =str.length;
		Log.i("TAG", "长度是"+len);
		int n=0;
		String[] data=new String[3];
		int oneIndex=0;
		int secIndex=1;
		int thiIndex=2;
		for (int i = 0; i < len; i++) {
			if (str[i]=='【') {
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
	 * 商家信息
	 */
	private void updateGoodsInfo() {
		
		Shop shop=goods.getShop();
		shopTitle.setText(shop.getShopName());  //商家名字
		shopTel.setText(shop.getShopTel());  //商家联系方式
	}

	/**
	 * 更新商品信息
	 */
	private void updateShopInfo() {
		
		goodsPrice.setText("￥"+goods.getPrice());  //设置价钱
		goodTitle.setText(goods.getSortTitle());   //设置标题
		goodsDesc.setText(goods.getTitle());   //设置详细信息
	    oldPrice.setText("￥"+goods.getValue());  //原价
		
	}

	/**
	 * 更新商品的标题图片
	 */
	private void updateTitleImage() {
	Picasso.with(this).load("file:///android_asset/" + goods.getImgUrl()).placeholder(R.drawable.tuan_item).into(goodsImg);
		
	}
}

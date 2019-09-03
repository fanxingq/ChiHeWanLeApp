package com.qust.chihewanleapp.home;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.Category;
import com.qust.chihewanleapp.entity.Goods;
import com.qust.chihewanleapp.tuan.GoodsDetailActivity;
import com.qust.chihewanleapp.utils.MyUtils;
import com.qust.chihewanleapp.utils.SharedUtils;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsCategoryList extends Activity {

	private Category cat; // ��Ʒ
	@ViewInject(R.id.category_goods_click)
	private TextView catClick;
	private List<Goods> list;
	@ViewInject(R.id.cat_click_listView) // ��Ʒ�б�
	private ListView catList;
	private MyListAdapter adapter; // ��Ʒ�б�������
	Object position = 0;
	// ����Ʒ�б�����ʱ����ʾ����
	@OnItemClick(R.id.cat_click_listView)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// position--;
		Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
		Log.i("TAG", "��������item" + list.get(position));
		intent.putExtra("goods", list.get(position));
		SharedUtils.putGoodsId(this, list.get(position).getId());
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_cat_list);
		ViewUtils.inject(this);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			position =  bundle.get("category");
			Log.i("TAG", "�������ݵ������item" + position);
		}
		catClick.setText(MyUtils.nvaSorts[(Integer) position-1]);
//		if (cat != null) {
			// ����ҳ�������е�����
			loadDatas();
//		}

	}
	
	@OnClick(R.id.category_goods_back)
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.category_goods_back:  //����
			finish();
			break;

		default:
			break;
		}
	}
	
	/**
	 * ��������
	 * 
	 * @author zheng
	 *
	 */
	public void loadDatas() {

		// ����
		RequestParams rParams = new RequestParams();
		// ʹ��Xutils����е����HTTP�����װ�õķ���
		new HttpUtils().send(HttpMethod.GET,
				Consts.Goods_Category_URL + "&cityId=1" + "&catId=c0" + position, rParams,
				new RequestCallBack<String>() {

					// ����ɹ�ʱ��ִ�еķ���������Ϊ���ص����ݣ���������һϵ�еĶ����ַ������õ���ʱ����д���
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						Log.i("TAG", "��������" + responseInfo.result);
						list = JSON.parseArray(responseInfo.result, Goods.class); // responseInfo.result��һ��String���͵���
						// ��ȡ���ݵĶ����װ������
						Log.i("TAG", "��������" + list.size());
						adapter = new MyListAdapter();
						catList.setAdapter(adapter);

					}

					// ����ʧ��ʱ
					@Override
					public void onFailure(HttpException error, String msg) {
						// listView.onRefreshComplete(); // ֹͣˢ��
						// ��ʾ
						Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show();
					}
				});

	}

	private class MyListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		// ��Ⱦÿһ��item��Ӧ����ͼ
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// ��convertViewΪ�յ�ʱ�򣬽�����ת����view,�Ż�����
			MyHolder2 myHolder = null;
			if (convertView == null) {
				myHolder = new MyHolder2();
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuan_index_list1, null);
				ViewUtils.inject(myHolder, convertView); // ע�����ʽ���г�ʼ��
				convertView.setTag(myHolder); // ���ǩ
			} else {
				myHolder = (MyHolder2) convertView.getTag();
			}

			// ��ȡ��Ӧ����������
			Goods goods = list.get(position);
			// ʹ��Picasso���������ͼƬ�ڴ������ͼƬ��λ

			Picasso.with(parent.getContext()).load("file:///android_asset/" + goods.getImgUrl())
					.placeholder(R.drawable.tuan_item).resize(120, 72).into(myHolder.imgView);

			StringBuffer stringBuffer = new StringBuffer("��" + goods.getValue()); // ƴ���ַ���������ʾԭ�۸�
			SpannableString sp = new SpannableString(stringBuffer); // ����л���
			sp.setSpan(new StrikethroughSpan(), 0, stringBuffer.length(), sp.SPAN_INCLUSIVE_INCLUSIVE);
			myHolder.value.setText(sp);
			myHolder.count.setText(goods.getBought() + "��"); // ����
			myHolder.price.setText("��" + goods.getPrice()); // ��ǰ�۸�
			myHolder.textView.setText(goods.getSortTitle());
			myHolder.xtextView.setText(goods.getTitle());
			myHolder.distant.setText(goods.getDistance());

			return convertView;
		}

	}

	public class MyHolder2 {
		@ViewInject(R.id.tuan_index_text_item)
		public TextView textView;
		@ViewInject(R.id.tuan_index_image_item)
		public ImageView imgView;
		@ViewInject(R.id.tuan_index_xtext_item)
		public TextView xtextView;
		@ViewInject(R.id.tuan_distant)
		public TextView distant;
		@ViewInject(R.id.tuan_price)
		public TextView price;
		@ViewInject(R.id.tuan_count)
		public TextView count;
		@ViewInject(R.id.tuan_value)
		private TextView value;
	}
}

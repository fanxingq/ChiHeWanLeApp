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

	private Category cat; // 商品
	@ViewInject(R.id.category_goods_click)
	private TextView catClick;
	private List<Goods> list;
	@ViewInject(R.id.cat_click_listView) // 商品列表
	private ListView catList;
	private MyListAdapter adapter; // 商品列表适配器
	Object position = 0;
	// 当商品列表点击的时候显示详情
	@OnItemClick(R.id.cat_click_listView)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// position--;
		Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
		Log.i("TAG", "测试数据item" + list.get(position));
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
			Log.i("TAG", "测试数据点击分类item" + position);
		}
		catClick.setText(MyUtils.nvaSorts[(Integer) position-1]);
//		if (cat != null) {
			// 更新页面上所有的内容
			loadDatas();
//		}

	}
	
	@OnClick(R.id.category_goods_back)
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.category_goods_back:  //返回
			finish();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 请求数据
	 * 
	 * @author zheng
	 *
	 */
	public void loadDatas() {

		// 参数
		RequestParams rParams = new RequestParams();
		// 使用Xutils框架中的这对HTTP请求封装好的方法
		new HttpUtils().send(HttpMethod.GET,
				Consts.Goods_Category_URL + "&cityId=1" + "&catId=c0" + position, rParams,
				new RequestCallBack<String>() {

					// 请求成功时，执行的方法，参数为返回的数据，对象中是一系列的对象字符串，用到的时候进行处理
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						Log.i("TAG", "测试数据" + responseInfo.result);
						list = JSON.parseArray(responseInfo.result, Goods.class); // responseInfo.result是一个String类型的数
						// 获取传递的对象封装的内容
						Log.i("TAG", "测试数据" + list.size());
						adapter = new MyListAdapter();
						catList.setAdapter(adapter);

					}

					// 请求失败时
					@Override
					public void onFailure(HttpException error, String msg) {
						// listView.onRefreshComplete(); // 停止刷新
						// 提示
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

		// 渲染每一个item对应的视图
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 在convertView为空的时候，将布局转换成view,优化缓存
			MyHolder2 myHolder = null;
			if (convertView == null) {
				myHolder = new MyHolder2();
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuan_index_list1, null);
				ViewUtils.inject(myHolder, convertView); // 注解的形式进行初始化
				convertView.setTag(myHolder); // 打标签
			} else {
				myHolder = (MyHolder2) convertView.getTag();
			}

			// 获取对应的索引内容
			Goods goods = list.get(position);
			// 使用Picasso框架来避免图片内存溢出和图片错位

			Picasso.with(parent.getContext()).load("file:///android_asset/" + goods.getImgUrl())
					.placeholder(R.drawable.tuan_item).resize(120, 72).into(myHolder.imgView);

			StringBuffer stringBuffer = new StringBuffer("￥" + goods.getValue()); // 拼接字符串进行显示原价格
			SpannableString sp = new SpannableString(stringBuffer); // 添加中划线
			sp.setSpan(new StrikethroughSpan(), 0, stringBuffer.length(), sp.SPAN_INCLUSIVE_INCLUSIVE);
			myHolder.value.setText(sp);
			myHolder.count.setText(goods.getBought() + "份"); // 销量
			myHolder.price.setText("￥" + goods.getPrice()); // 当前价格
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

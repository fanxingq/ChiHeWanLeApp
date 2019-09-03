package com.qust.chihewanleapp.fragment;

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
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.Goods;
import com.qust.chihewanleapp.tuan.GoodsDetailActivity;
import com.qust.chihewanleapp.utils.SharedUtils;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * 团购
 * 
 * @author zheng
 *
 */
public class FragmentTuan extends Fragment {

	@ViewInject(R.id.listView)
	private ListView listView;
	private List<Goods> list;
	private MyListAdapter adapter;

	// 当商品列表点击的时候显示详情
	@OnItemClick(R.id.listView)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		position--;
		Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
		Log.i("TAG", "测试数据item" + list.get(position));
		intent.putExtra("goods", list.get(position));
		SharedUtils.putGoodsId(getContext(), list.get(position).getId());
		startActivity(intent);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tuan_index, null);
		ViewUtils.inject(this, view);
		System.out.println(view);
		System.out.println(list);
		// 设置商品的信息列表属性
//		listView.setMode(Mode.BOTH); // 支持上拉也支持下拉
//		listView.setScrollingWhileRefreshingEnabled(true); // 滚动的时候不加载数据
//		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//			// 刷新商品时执行
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				// 下拉刷新y<0
//				loadDatas(listView.getScaleY() < 0);
//			}
//		});
//		// 首次来到页面时，自动加载数据
//		new Handler(new Handler.Callback() {
//			// 处理接收到的消息的方法
//			@Override
//			public boolean handleMessage(Message msg) {
//				// 实现页面跳转
//				listView.setRefreshing();
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 100); // 300毫秒后主动添加数据
		loadDatas(true);
		// l.setAdapter(new MyViewAdapter());
		return view;

	}

	private int page, size = 10, count, num; // 初始化数据

	/**
	 * 请求数据
	 * 
	 * @author zheng
	 *
	 */
	public void loadDatas(final boolean reflush) {
		// 判断是否需要刷新
//		if (reflush) {
//			page = 1;
//		} else {
//			page++;
//		}
		// 参数
		RequestParams rParams = new RequestParams();
		rParams.addQueryStringParameter("page", page + "");
		rParams.addQueryStringParameter("size", size + "");

		// 使用Xutils框架中的这对HTTP请求封装好的方法
		new HttpUtils().send(HttpMethod.GET, Consts.Goods_Datas_URL, rParams, new RequestCallBack<String>() {

			// 请求成功时，执行的方法，参数为返回的数据，对象中是一系列的对象字符串，用到的时候进行处理
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
//				listView.onRefreshComplete(); // 停止刷新
				// Log.i("TAG", responseInfo.result);
				list = JSON.parseArray(responseInfo.result, Goods.class); // responseInfo.result是一个String类型的数
				// FoodsObject<List<Goods>> object=new
				// FoodsObject<List<Goods>>(0, list);
				// size=object.getSize();
//				count = (list.size()) / size + 1;
				// 获取传递的对象封装的内容
				Log.i("TAG", "测试数据" + list.size());
				Log.i("TAG", "page=" + page + ",count=" + count);
				//
				// 判断下拉刷新还是上拉加载
				// if (reflush) { //下拉刷新

				// list=object.getDatas();
				adapter = new MyListAdapter();
				listView.setAdapter(adapter);
				//
				// }else {
				// list.addAll(object.getDatas());
				// adapter.notifyDataSetChanged();

				// }
//				if (count == page) { // 没有更多的数据显示
//					listView.setMode(Mode.PULL_FROM_START); // 只能刷新
//				}

			}

			// 请求失败时
			@Override
			public void onFailure(HttpException error, String msg) {
//				listView.onRefreshComplete(); // 停止刷新
				// 提示
				Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class MyListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			num = list.size();
			return num;
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

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
 * �Ź�
 * 
 * @author zheng
 *
 */
public class FragmentTuan extends Fragment {

	@ViewInject(R.id.listView)
	private ListView listView;
	private List<Goods> list;
	private MyListAdapter adapter;

	// ����Ʒ�б�����ʱ����ʾ����
	@OnItemClick(R.id.listView)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		position--;
		Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
		Log.i("TAG", "��������item" + list.get(position));
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
		// ������Ʒ����Ϣ�б�����
//		listView.setMode(Mode.BOTH); // ֧������Ҳ֧������
//		listView.setScrollingWhileRefreshingEnabled(true); // ������ʱ�򲻼�������
//		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//			// ˢ����Ʒʱִ��
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				// ����ˢ��y<0
//				loadDatas(listView.getScaleY() < 0);
//			}
//		});
//		// �״�����ҳ��ʱ���Զ���������
//		new Handler(new Handler.Callback() {
//			// ������յ�����Ϣ�ķ���
//			@Override
//			public boolean handleMessage(Message msg) {
//				// ʵ��ҳ����ת
//				listView.setRefreshing();
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 100); // 300����������������
		loadDatas(true);
		// l.setAdapter(new MyViewAdapter());
		return view;

	}

	private int page, size = 10, count, num; // ��ʼ������

	/**
	 * ��������
	 * 
	 * @author zheng
	 *
	 */
	public void loadDatas(final boolean reflush) {
		// �ж��Ƿ���Ҫˢ��
//		if (reflush) {
//			page = 1;
//		} else {
//			page++;
//		}
		// ����
		RequestParams rParams = new RequestParams();
		rParams.addQueryStringParameter("page", page + "");
		rParams.addQueryStringParameter("size", size + "");

		// ʹ��Xutils����е����HTTP�����װ�õķ���
		new HttpUtils().send(HttpMethod.GET, Consts.Goods_Datas_URL, rParams, new RequestCallBack<String>() {

			// ����ɹ�ʱ��ִ�еķ���������Ϊ���ص����ݣ���������һϵ�еĶ����ַ������õ���ʱ����д���
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
//				listView.onRefreshComplete(); // ֹͣˢ��
				// Log.i("TAG", responseInfo.result);
				list = JSON.parseArray(responseInfo.result, Goods.class); // responseInfo.result��һ��String���͵���
				// FoodsObject<List<Goods>> object=new
				// FoodsObject<List<Goods>>(0, list);
				// size=object.getSize();
//				count = (list.size()) / size + 1;
				// ��ȡ���ݵĶ����װ������
				Log.i("TAG", "��������" + list.size());
				Log.i("TAG", "page=" + page + ",count=" + count);
				//
				// �ж�����ˢ�»�����������
				// if (reflush) { //����ˢ��

				// list=object.getDatas();
				adapter = new MyListAdapter();
				listView.setAdapter(adapter);
				//
				// }else {
				// list.addAll(object.getDatas());
				// adapter.notifyDataSetChanged();

				// }
//				if (count == page) { // û�и����������ʾ
//					listView.setMode(Mode.PULL_FROM_START); // ֻ��ˢ��
//				}

			}

			// ����ʧ��ʱ
			@Override
			public void onFailure(HttpException error, String msg) {
//				listView.onRefreshComplete(); // ֹͣˢ��
				// ��ʾ
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

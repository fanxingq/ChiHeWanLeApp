package com.qust.chihewanleapp;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.qust.chihewanleapp.fragment.FragmentHome.MyHolder;
import com.qust.chihewanleapp.home.GoodsCategoryList;
import com.qust.chihewanleapp.utils.MyUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AllCategoryActivity extends Activity {

	@ViewInject(R.id.category_list)
	private ListView allCategory;
	
	/**
	 * item��ѡ��
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick(R.id.category_list)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position++;
		Intent intent = new Intent(getApplicationContext(), GoodsCategoryList.class);
		Log.i("TAG", "�������ݵ������item" + position);
		intent.putExtra("category", position);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//���ز���
		setContentView(R.layout.activity_all_category);
		ViewUtils.inject(this);
		//����
		allCategory.setAdapter(new MyAllAdapter());
	}
	public class MyAllAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MyUtils.allSorts.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyHolder myHolder=null;
			if (convertView==null) {
				myHolder=new MyHolder();
				convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_all_category_item, null);
				ViewUtils.inject(myHolder,convertView);  //ע�����ʽ���г�ʼ��
			    convertView.setTag(myHolder);   //���ǩ
			}else {
				myHolder=(MyHolder) convertView.getTag();
			}
			//��ֵ
			myHolder.textDes.setText(MyUtils.allSorts[position]);
			myHolder.imageView.setImageResource(MyUtils.allCategorys[position]);
			return convertView;
		}
		
	}
	public class MyHolder{  
		@ViewInject(R.id.category_index_item_img)
		public ImageView imageView;
		@ViewInject(R.id.category_index_item_text)
		public TextView textDes;
		@ViewInject(R.id.category_index_num)
		public TextView textNum;
	}
	/**
	 * ������ذ�ť�ļ����¼�
	 */
	@OnClick(R.id.category_index_back)
	private void OnClick(View view) {
		switch (view.getId()) {
		case R.id.category_index_back:  //�������
			finish();
			break;

		
		default:
			break;
		}
		
	}
	
	
	
	
}

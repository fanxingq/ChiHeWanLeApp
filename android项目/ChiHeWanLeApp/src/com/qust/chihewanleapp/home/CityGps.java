package com.qust.chihewanleapp.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.AllCategoryActivity.MyHolder;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.City;
import com.qust.chihewanleapp.fragment.FragmentHome;
import com.qust.chihewanleapp.myview.SideBar;
import com.qust.chihewanleapp.myview.SideBar.OnTouchingLetterChangedListener;

import android.app.Activity;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 城市定位
 * @author zheng
 *
 */
public class CityGps extends Activity implements OnTouchingLetterChangedListener{
	
	@ViewInject(R.id.city_list)
	private ListView listDatas;
//	@ViewInject(R.id.home_city_index_search)
//	private TextView tv_city;
//	@ViewInject(R.id.city_index_remen_list)
//	private GridView remenList;
	private List<City> listCity;
	@ViewInject(R.id.sidebar)
	private SideBar siderBar;    //索引
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_index);
		ViewUtils.inject(this);
		View view=LayoutInflater.from(this).inflate(R.layout.home_city_search, null);
		TextView tv_city = (TextView) view.findViewById(R.id.home_city_index_search);   //将数据从文件中取出来
		tv_city.setText(getSharedPreferences("city", MODE_PRIVATE).getString("cityName", getString(R.string.cityname)));
		listDatas.addHeaderView(view);
		//执行异步任务
		new CityDataTask().execute();
		//城市索引监听
		siderBar.setOnTouchingLetterChangedListener(this);
	}
	//按钮监听
	@OnClick(R.id.home_city_back)
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.home_city_back:  //返回按钮
			finish();
			break;
//		case R.id.home_city_back:  //刷新按钮
//			new CityDataTask().execute();
//			break;
		default:
			break;
		}
	}
	//Item的监听事件,回传城市名
	@OnItemClick({R.id.city_list})
	public void onItemClick(AdapterView<?> parent,View view,int position,long id){
		if (position!=0) {
			TextView textView=(TextView) view.findViewById(R.id.city_list_item_tv_cityName);
			
			Intent intent =new Intent();
			System.out.println( textView.getText().toString());
			intent.putExtra("cityName", textView.getText().toString());
			SharedPreferences sharedPreferences = getSharedPreferences("city", MODE_PRIVATE);  //将数据存入文件“city”
			SharedPreferences.Editor editor =  sharedPreferences.edit();
			editor.putString("cityName", textView.getText().toString());
			editor.apply();
			setResult(RESULT_OK,intent);
			finish();
		}
		
	}
	
	/**
	 * 使用异步任务获取城市的json串
	 */
	public class CityDataTask extends AsyncTask<Void, Void, List<City>>{

		@Override
		protected List<City> doInBackground(Void... params) {
			
			HttpClient client=new DefaultHttpClient();
			HttpPost httpPost=new HttpPost(Consts.City_Data_URI);   //服务器城市数据地址，请求数据
			try {
				HttpResponse httpResponse= client.execute(httpPost);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
		
				if (httpResponse.getStatusLine().getStatusCode()==200) {
					String jsonString=EntityUtils.toString(httpResponse.getEntity());  //获取json串
					System.out.println(jsonString);
					//封装一个方法处理收到的json串
					return ParseCityDatasJson(jsonString);
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(List<City> result) {	
			super.onPostExecute(result);
			listCity=result;   //赋值
			//适配显示
			MyAdpter adpter=new MyAdpter(listCity);
			listDatas.setAdapter(adpter);
			
		}
	}
	
	/**
	 * 处理收到的json串
	 */
	public List<City> ParseCityDatasJson(String json){
		List<City> list=JSON.parseArray(json, City.class);	
		System.out.println(list);
		return list;
	}
	
	private StringBuffer buffer=new StringBuffer();  //用来第一次保存首字母的索引
	private List<String> FirstList=new ArrayList<String>();   //用来保存索引对象的名称
	/**
	 * 适配显示
	 */
	public class MyAdpter extends BaseAdapter{

		private List<City> listCityDatas;
		
		//构造函数
		public MyAdpter(List<City> listCityDatas) {
			this.listCityDatas = listCityDatas;
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
//			System.out.println(listCityDatas);
			return listCityDatas.size();
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listCityDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
			MyHolder myHolder;
			if (convertView==null) {
				myHolder=new MyHolder();
				//布局加载器
				convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_city_list_item, null);
				ViewUtils.inject(myHolder,convertView);  //注解的形式进行初始化
			    convertView.setTag(myHolder);   //对holder对象打标签
			}else {
				myHolder=(MyHolder) convertView.getTag();
			}
			//数据显示的处理
			City city=listCityDatas.get(position);   //获取city对象的索引位置
			String cityName=city.getCityName();
			String sortKey=city.getSortKey();
			//索引不存在，就添加进去，例如：A
			if (buffer.indexOf(sortKey)==-1) {
				buffer.append(sortKey);
				FirstList.add(cityName);
			}
			
			if (FirstList.contains(cityName)) {
				myHolder.sortKey.setText(sortKey);
				myHolder.sortKey.setVisibility(View.VISIBLE);  //包含对应的城市，就显示索引
			}else {
				myHolder.sortKey.setVisibility(View.GONE);
			}
			myHolder.cityName.setText(cityName);   //不管索引存不存在都显示城市名称
			return convertView;
		}
		
	}
	
	public class MyHolder{
		
		@ViewInject(R.id.city_list_item_tv_cityName)
		private TextView cityName;
		@ViewInject(R.id.city_list_item_tv_sortKey)
		private TextView sortKey;
	}

	/**
	 * 城市索引监听
	 */
	@Override
	public void onTouchingLetterChanged(String s) {
		//找到list中显示的索引位置
		listDatas.setSelection(findIndex(listCity, s));
		
	}
	//数据s找到对应s的位置
	public int findIndex(List<City>list,String s){
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				City city=list.get(i);
				if (s.equals(city.getSortKey())) {
					return i;
				}
			}
		}else {
			Toast.makeText(this, "暂无信息", Toast.LENGTH_SHORT).show();
		}
		return -1;
	}
}

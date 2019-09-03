package com.qust.chihewanleapp.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.qust.chihewanleapp.AllCategoryActivity;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.entity.Category;
import com.qust.chihewanleapp.entity.Goods;
import com.qust.chihewanleapp.home.CityGps;
import com.qust.chihewanleapp.home.GoodsCategoryList;
import com.qust.chihewanleapp.tuan.GoodsDetailActivity;
import com.qust.chihewanleapp.utils.MyUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class FragmentHome extends Fragment {

	//因为要拿到一个视图，每个视图都会定义一个布局，so每个fragment都要重写onCreatView方法
	//第一个参数inflater表示布局加载器
	@ViewInject(R.id.index_top_city)
	private TextView topCity;
	@ViewInject(R.id.home_city_index_search)
	private TextView home_search;
	private String cityName;  //当前城市名
	private LocationManager locationManager;
//	private MyPagerAdapter myAdapter = new MyPagerAdapter();
	private ScheduledExecutorService scheduledExecutorService;  //轮播图
	//轮播图图片数量
	private final static int IMAGE_COUNT = 5;
	//自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5;
	//自动轮播启用开关
	private final static boolean isAutoPlay = true; 
	private List<ImageView> imageViews;
	private int currentItem=0;
	@ViewInject(R.id.v_dot1)
	private View v_dot1;
	@ViewInject(R.id.v_dot2)
	private View v_dot2;
	@ViewInject(R.id.v_dot3)
	private View v_dot3;
	@ViewInject(R.id.v_dot4)
	private View v_dot4;
	@ViewInject(R.id.v_dot5)
	private View v_dot5;
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	//放圆点的list集合
	private ArrayList<View> dotViews;  
	private Handler handler;
	//自动轮播图的resource
    private int[] imagesResID;
    //item
    @ViewInject(R.id.index_home_gridview_my)
	private GridView avaView;
    private List<Category> list;  //item商品信息
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//直接将xml文件映射成一个view对象，调用inflate方法传入布局文件
		View view=inflater.inflate(R.layout.home_index, null);   //根节点设为空
		ViewUtils.inject(this,view);
//		topCity.setOnClickListener(new CityListenerBtn());
//		//获取数据并且显示
//		topCity.setText(SharedUtils.getCityName(getActivity()));
		
	    dotViews=new ArrayList<View>();
		dotViews.add(v_dot1);
		dotViews.add(v_dot2);
		dotViews.add(v_dot3);
		dotViews.add(v_dot4);
		dotViews.add(v_dot5);
//		viewPager.setAdapter(myAdapter);
		
		imagesResID=new int[]{
				   R.drawable.home_wel2,R.drawable.home_wel3,R.drawable.home_wel1,
				   R.drawable.home_wel4,R.drawable.home_wel5};
		imageViews=new ArrayList<ImageView>();
		avaView.setAdapter(new AvaViewAdapter());
		//初始化相关data
//		initData();
		initPager(getContext());
		if(isAutoPlay){
			startPlay();
		}

//		topCity.setText("青岛");
		return view;  //返回view对象
	}
	
	/**
	 * item被选中
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick(R.id.index_home_gridview_my)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position++;
		Intent intent = new Intent(getActivity(), GoodsCategoryList.class);
		Log.i("TAG", "测试数据点击分类item" + position);
		intent.putExtra("category", position);
		startActivity(intent);
	}

	
	
	/**
	 * 页面跳转
	 * @param v
	 */
	//城市监听
	@OnClick(R.id.index_top_city)
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.index_top_city:  //城市
			//带有返回值的页面跳转
			startActivityForResult(new Intent(getActivity(), CityGps.class), MyUtils.RequstCityCode);
			break;

		default:
			break;
		}
	}
	//处理返回值的结果
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==MyUtils.RequstCityCode && resultCode==Activity.RESULT_OK) {
//		if (requestCode==Activity.RESULT_OK) {
			cityName=data.getStringExtra("cityName");
			System.out.println(cityName);
			topCity.setText(cityName);
		}
		
	}
	
	private void initPager(Context context){

//		LayoutInflater.from(context).inflate(R.layout.slideshow, null);
		for(int imageID : imagesResID){
			ImageView view =  new ImageView(context);
			view.setImageResource(imageID);
			view.setScaleType(ScaleType.FIT_XY);  //坐标
			imageViews.add(view); 
		}
		    viewPager.setFocusable(true);
            viewPager.setAdapter(new MyPagerAdapter());
            viewPager.setOnPageChangeListener(new MyPageChangeListener());
        }
        
	
	/**
	 * 初始化相关data
	
	private void initData() {
		imagesResID=new int[]{
		   R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5};	
	}
	 */
	
	/**
	 * 定位按钮监听
	 */
	private class LocationButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			locationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//			locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, intent);
		}
		
	}
	
	
	/**
	 *item适配器 
	 * @author zheng
	 */
	private class AvaViewAdapter extends BaseAdapter{

		//计算需要适配的数据总量
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MyUtils.nvaSorts.length;
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
        //item对应的view的数据渲染
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//在convertView为空的时候，将布局转换成view,优化缓存
			MyHolder myHolder=null;
			if (convertView==null) {
				myHolder=new MyHolder();
				convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_index_item_1, null);
				ViewUtils.inject(myHolder,convertView);  //注解的形式进行初始化
			    convertView.setTag(myHolder);   //打标签
			}else {
				myHolder=(MyHolder) convertView.getTag();
			}
			//赋值
			myHolder.textView.setText(MyUtils.nvaSorts[position]);
			myHolder.imageView.setImageResource(MyUtils.nvaImages[position]);
			//如果选中的是全部的话
			if (position==MyUtils.nvaSorts.length-1) {
				myHolder.imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					startActivity(new Intent(getActivity(), AllCategoryActivity.class));
						
					}
				});
			}
			return convertView;
		}
		
		
	}
	public class MyHolder{
		@ViewInject(R.id.home_index_item_img)
		public ImageView imageView;
		@ViewInject(R.id.home_index_item_text)
		public TextView textView;
	}
	/**
	 * 填充ViewPager的适配器
	 * @author zheng
	 *
	 */
	private class MyPagerAdapter extends PagerAdapter{
		
		//计算需要多少item显示
		@Override
		public int getCount() {
			return imageViews.size();
		}

		//将View对象和Object进行对比
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;   
		}
		//初始化item实例的方法。加载页卡
		//滑动一个页卡的时候相当于将一个页卡添加到ViewPager中
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageViews.get(position));
			return imageViews.get(position);   //返回position对象
		}
		//销毁掉页卡item的方法
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView(imageViews.get(position));
		}
	}
	
	
	/**
	 *检查当前的GPS是否打开，如果没打开，则帮助打开 
	*/

	public void startPlay() {
      
        //检查当前GPS的模块
//        checkGPSIsOpen();
       
        scheduledExecutorService=Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 1, 4, TimeUnit.SECONDS);     
	}
	/**
	 * 停止轮播图切换
	 */
	private void stopPlay(){
		scheduledExecutorService.shutdown();
	}
	/*
	 * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViews.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    /**
     * 城市监听按钮
     * @author zheng
     *
     */
    private class CityListenerBtn implements OnClickListener{

		@Override
		public void onClick(View v) {
			startActivity(new Intent(getActivity(), CityGps.class));
		}
    	
    }
    
    
	private class ViewPagerTask implements  Runnable {

		public void run() {
			currentItem=(currentItem+1)%imageViews.size();
			handler.obtainMessage().sendToTarget();
		}
		
		private Handler handler=new Handler(){
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				viewPager.setCurrentItem(currentItem);
				
			}
		};
	}
	
	private class MyPageChangeListener implements OnPageChangeListener{

		boolean isAutoPlay = false;
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
            case 1:// 手势滑动，空闲中
                isAutoPlay = false;
                stopPlay();
                destoryBitmaps();
                break;
            case 2:// 界面切换中
            	isAutoPlay = true;
                break;
            case 0:// 滑动结束，即切换完毕或者加载完毕
                // 当前为最后一张，此时从右向左滑，则切换到第一张
            	
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
        @Override
		public void onPageSelected(int arg0) {
			currentItem = arg0;
			for(int i=0;i < dotViews.size();i++){
				if(i == arg0){
					((View)dotViews.get(arg0)).setBackgroundResource(R.drawable.list_bkg_line_d);
				}else {
					((View)dotViews.get(i)).setBackgroundResource(R.drawable.list_bkg_line_u);
				}
			}		
        }
		}
	
	
}
	
//	private void checkGPSIsOpen(){
//		//获取当前的location对象
		
//		boolean isOpen=locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
//		if (!isOpen) {
//			//进入GPS设置页面
//			Intent intent=new Intent();
//			intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivityForResult(intent, 0);
//		}
//		//开始定位
//		startLocation();
//	}
//	//开启GPS定位的方法
//	private void startLocation(){
//		locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 2000, 10, this);   //2秒，10米，监听本对象
//	}
//	
	
	/**
	 * 线程，定义一个handler对象,接受并处理信息
	
	private Handler handler=new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {

			if (msg.what==1) {
				topCity.setText(cityName);  //将城市名修改为定位到的地方
			}
			return false;
		}
	});
	 */
	/**
	 * 获取对应位置的经纬度，并且定位城市
	 * @param location
	 
	private void updateWithNewLocation(Location location) {

		double lat=0.0,lng=0.0;
		if (location!=null) {
			lat=location.getLatitude();
			lng=location.getLongitude();
			Log.i("TAG", "经度是"+lat+",纬度是:"+lng );
		}else {
			cityName="无法获取城市信息";
			
		}
		//通过经纬度获取地址，由于地址会有多个，这个和经纬度精确度有关，在此定义了最大返回数2，即在集合对象中有两个值
		List<Address> list=null;   //定义list对象，存放地址
		Geocoder ge=new Geocoder(getActivity());    //此类根据经纬度获取地址，或者根据地址获取经纬度
		try {
			list=ge.getFromLocation(lat, lng, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list!=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Address address=list.get(i);
				cityName=address.getLocality();  //获取城市
			}
		}
		//发送空消息
		handler.sendEmptyMessage(1);
	}
	
	*/
	
	
	/**
	 * 位置信息更改执行方法
	
	@Override
	public void onLocationChanged(Location location) {
      //更新当前的位置信息
		updateWithNewLocation(location);
		
		
	}
	//定位状态发生变化
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	//可用不可用
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	} */
	/**
	 * 关闭GPS定位
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//保存城市
		SharedUtils.putCityName(getActivity(), cityName);
		//停止定位
		stopLocation();
	}
	//停止定位
	private void stopLocation() {
		locationManager.removeUpdates(this);
	}
	 */
	/**
	 * ViewPager的监听器
	 * 当ViewPager中页面的状态发生改变时调用
	 * @author zheng
	 *
	 */
	

	


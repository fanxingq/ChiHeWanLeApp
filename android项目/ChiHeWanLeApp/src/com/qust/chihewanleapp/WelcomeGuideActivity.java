package com.qust.chihewanleapp;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.qust.chihewanleapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
/**
 * 实现页面滑动
 * @author 郑晴
 *
 */
public class WelcomeGuideActivity extends Activity {

	//使用注解进行关联与填充
	@ViewInject(R.id.welcome_guide_btn)
	private Button btn;
	@ViewInject(R.id.welcome_pager)
	private ViewPager pager;
	private List<View>list;  //定义一个list集合存放图片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		ViewUtils.inject(this);  //填充activity
		intViewPager();
		
	}
	//添加一个onClick注解，监听button按钮
	@OnClick(R.id.welcome_guide_btn)
	public void click(View view){
		//页面的跳转
		startActivity(new Intent(getBaseContext(),MainActivity.class));
		finish();
	}
	
	//初始化ViewPager方法
	public void intViewPager(){
		list=new ArrayList<View>();
		ImageView iv=new ImageView(this);
		iv.setImageResource(R.drawable.guide_welcome1);  //获取图片的方法
		list.add(iv);
		ImageView iv1=new ImageView(this);
		iv1.setImageResource(R.drawable.guide_welcome2);
		list.add(iv1);
		ImageView iv2=new ImageView(this);
		iv2.setImageResource(R.drawable.guide_welcome2);
		list.add(iv2);
		System.out.println("%%%%%%");
		pager.setAdapter(new MyPagerAdapter());   //显示页卡
		 


		//监听ViewPager滑动效果,根据页卡的改变
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			//页卡被选中的方法
			@Override
			public void onPageSelected(int arg0) {

				//如果是第三个页面，即最后一个页面，button按钮出现
				if (arg0==2) {
					btn.setVisibility(View.VISIBLE);  //button按钮保留并显示
				}else {
					btn.setVisibility(View.GONE);  //button按钮不保留不显示
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
	}
	
	
	//定义ViewPager的适配器 的内部类
	class MyPagerAdapter extends PagerAdapter{

		//计算需要多少item显示
		@Override
		public int getCount() {
			return list.size();
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
			container.addView(list.get(position));
			return list.get(position);   //返回position对象
		}
		//销毁掉页卡item的方法
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView(list.get(position));
		}
	}
	
	
}

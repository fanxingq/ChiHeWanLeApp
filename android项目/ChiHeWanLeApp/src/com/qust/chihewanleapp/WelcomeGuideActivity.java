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
 * ʵ��ҳ�滬��
 * @author ֣��
 *
 */
public class WelcomeGuideActivity extends Activity {

	//ʹ��ע����й��������
	@ViewInject(R.id.welcome_guide_btn)
	private Button btn;
	@ViewInject(R.id.welcome_pager)
	private ViewPager pager;
	private List<View>list;  //����һ��list���ϴ��ͼƬ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		ViewUtils.inject(this);  //���activity
		intViewPager();
		
	}
	//���һ��onClickע�⣬����button��ť
	@OnClick(R.id.welcome_guide_btn)
	public void click(View view){
		//ҳ�����ת
		startActivity(new Intent(getBaseContext(),MainActivity.class));
		finish();
	}
	
	//��ʼ��ViewPager����
	public void intViewPager(){
		list=new ArrayList<View>();
		ImageView iv=new ImageView(this);
		iv.setImageResource(R.drawable.guide_welcome1);  //��ȡͼƬ�ķ���
		list.add(iv);
		ImageView iv1=new ImageView(this);
		iv1.setImageResource(R.drawable.guide_welcome2);
		list.add(iv1);
		ImageView iv2=new ImageView(this);
		iv2.setImageResource(R.drawable.guide_welcome2);
		list.add(iv2);
		System.out.println("%%%%%%");
		pager.setAdapter(new MyPagerAdapter());   //��ʾҳ��
		 


		//����ViewPager����Ч��,����ҳ���ĸı�
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			//ҳ����ѡ�еķ���
			@Override
			public void onPageSelected(int arg0) {

				//����ǵ�����ҳ�棬�����һ��ҳ�棬button��ť����
				if (arg0==2) {
					btn.setVisibility(View.VISIBLE);  //button��ť��������ʾ
				}else {
					btn.setVisibility(View.GONE);  //button��ť����������ʾ
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
	
	
	//����ViewPager�������� ���ڲ���
	class MyPagerAdapter extends PagerAdapter{

		//������Ҫ����item��ʾ
		@Override
		public int getCount() {
			return list.size();
		}

		//��View�����Object���жԱ�
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;   
		}
		//��ʼ��itemʵ���ķ���������ҳ��
		//����һ��ҳ����ʱ���൱�ڽ�һ��ҳ����ӵ�ViewPager��
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);   //����position����
		}
		//���ٵ�ҳ��item�ķ���
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView(list.get(position));
		}
	}
	
	
}

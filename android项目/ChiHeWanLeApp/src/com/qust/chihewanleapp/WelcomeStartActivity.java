package com.qust.chihewanleapp;

import java.util.Timer;
import java.util.TimerTask;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.utils.SharedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 延时跳转
 * 可以使用handler
 * @author 郑晴
 *
 */
public class WelcomeStartActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_guide); //加载开始界面
//		new Handler(new Handler.Callback() {
//			//处理接收到的消息的方法
//			@Override
//			public boolean handleMessage(Message msg) {
//				//实现页面跳转
//				startActivity( new Intent(getApplicationContext(),MainActivity.class));
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 3000);  //发送一条空消息延时三秒钟
		
		//使用Java中的定时器timer进行处理
		Timer timer=new Timer();
		timer.schedule(new Task(), 3000);  //定时器延时执行任务的方法
}
	//创建个内部类
	class Task extends TimerTask{

		@Override
		public void run() {
			/**
			 * getBaseContext返回用户构造方法中的activity的上下文，属于activity，activity摧毁它就摧毁
			 * getApplicationContext返回应用的上下文，生命周期是整个应用，应用摧毁它才摧毁
			 */

			//实现页面的跳转，判断是否是第一次进入
			if (SharedUtils.getWelcomeBoolean(getBaseContext())) {   //如果为true，表示不是第一次进入
				startActivity(new Intent(getBaseContext(),MainActivity.class));  //从此页面跳转到下一个页面
			}else {
				startActivity(new Intent(WelcomeStartActivity.this,WelcomeGuideActivity.class));
				//保存访问记录
				SharedUtils.putWelcomeBoolean(getBaseContext(), true);
			}
			finish();
		}
		
	}
	
	
	
	
	
}
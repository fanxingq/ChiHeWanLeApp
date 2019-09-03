package com.qust.chihewanleapp;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.fragment.FragmentHome;
import com.qust.chihewanleapp.fragment.FragmentMy;
import com.qust.chihewanleapp.fragment.FragmentSearch;
import com.qust.chihewanleapp.fragment.FragmentTuan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{   //要实现页卡的切换，不继承activity，继承FragmentActivity,并监听按钮
	//对控件进行初始化
	@ViewInject(R.id.main_bottom_tabs)  //对控件进行绑定
	private RadioGroup group;
	@ViewInject(R.id.main_home)
	private RadioButton main_home;
	private FragmentManager fragmentMagager;  //管理fragment的类
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		//初始化FragmentManager
		fragmentMagager=getSupportFragmentManager();
		//设置默认选中页面
		main_home.setChecked(true);
		group.setOnCheckedChangeListener(this);  //对4个button都设置了监听对象，就可以响应后续变化
		//切换不同的fragment
		changeFragment(new FragmentHome(), false);
	}

	
 

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//遍历check Id
		switch (checkedId) {
		case R.id.main_home:   //首页
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.main_tuan:   //团购
			changeFragment(new FragmentTuan(), true);
			break;
		case R.id.main_order:  //订单
			changeFragment(new FragmentSearch(), true);
			break;
		case R.id.main_my:     //我的
			changeFragment(new FragmentMy(), true);
			break;

		default:
			break;
		}
		
	}
	

    //切换不同的fragment
    public void changeFragment(Fragment fragment,boolean isInit){  //isFirst主要控制fragment是否回退栈
    	//fragment需要开启事务，调用beginTransaction
    	//开启事务
    	FragmentTransaction transaction=fragmentMagager.beginTransaction();
    	transaction.replace(R.id.main_content, fragment);      //将fragment填充到第一个参数中
    	if (!isInit) {                             //判断是否是第一次调用fragment
			transaction.addToBackStack(null);
		}
    	transaction.commit();  //提交事务
    	
    } 
    
    private long time = 0;
    @Override
    	public void onBackPressed() {
    		// TODO Auto-generated method stub
    		super.onBackPressed();
//    		long currentTime = System.currentTimeMillis();
//    		if ((currentTime - time)>2000) {
//				Toast.makeText(getApplicationContext(), "再按一次退出本程序！！！", Toast.LENGTH_SHORT).show();
//				time = currentTime;
//			}else {
//				finish();
//			}
    	}

}








/*
	//完全注解方式就可以进行UI绑定和事件绑定
    //无需findViewbyid和setClicklistener等
//	@ViewInject(R.id.btn)
//	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);  //注入view和事件
//		//控件的初始化
//		btn=(Button) findViewById(R.id.btn);
//		btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this,"实现了点击" , Toast.LENGTH_SHORT).show();
//				
//			}
//		});
		
		
	}
	@OnClick(R.id.btn)
	public void click(View view){
		Toast.makeText(MainActivity.this,"实现了点击" , Toast.LENGTH_SHORT).show();
	}
	*/
	

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

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{   //Ҫʵ��ҳ�����л������̳�activity���̳�FragmentActivity,��������ť
	//�Կؼ����г�ʼ��
	@ViewInject(R.id.main_bottom_tabs)  //�Կؼ����а�
	private RadioGroup group;
	@ViewInject(R.id.main_home)
	private RadioButton main_home;
	private FragmentManager fragmentMagager;  //����fragment����
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		//��ʼ��FragmentManager
		fragmentMagager=getSupportFragmentManager();
		//����Ĭ��ѡ��ҳ��
		main_home.setChecked(true);
		group.setOnCheckedChangeListener(this);  //��4��button�������˼������󣬾Ϳ�����Ӧ�����仯
		//�л���ͬ��fragment
		changeFragment(new FragmentHome(), false);
	}

	
 

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//����check Id
		switch (checkedId) {
		case R.id.main_home:   //��ҳ
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.main_tuan:   //�Ź�
			changeFragment(new FragmentTuan(), true);
			break;
		case R.id.main_order:  //����
			changeFragment(new FragmentSearch(), true);
			break;
		case R.id.main_my:     //�ҵ�
			changeFragment(new FragmentMy(), true);
			break;

		default:
			break;
		}
		
	}
	

    //�л���ͬ��fragment
    public void changeFragment(Fragment fragment,boolean isInit){  //isFirst��Ҫ����fragment�Ƿ����ջ
    	//fragment��Ҫ�������񣬵���beginTransaction
    	//��������
    	FragmentTransaction transaction=fragmentMagager.beginTransaction();
    	transaction.replace(R.id.main_content, fragment);      //��fragment��䵽��һ��������
    	if (!isInit) {                             //�ж��Ƿ��ǵ�һ�ε���fragment
			transaction.addToBackStack(null);
		}
    	transaction.commit();  //�ύ����
    	
    } 
    
    private long time = 0;
    @Override
    	public void onBackPressed() {
    		// TODO Auto-generated method stub
    		super.onBackPressed();
//    		long currentTime = System.currentTimeMillis();
//    		if ((currentTime - time)>2000) {
//				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳������򣡣���", Toast.LENGTH_SHORT).show();
//				time = currentTime;
//			}else {
//				finish();
//			}
    	}

}








/*
	//��ȫע�ⷽʽ�Ϳ��Խ���UI�󶨺��¼���
    //����findViewbyid��setClicklistener��
//	@ViewInject(R.id.btn)
//	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);  //ע��view���¼�
//		//�ؼ��ĳ�ʼ��
//		btn=(Button) findViewById(R.id.btn);
//		btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this,"ʵ���˵��" , Toast.LENGTH_SHORT).show();
//				
//			}
//		});
		
		
	}
	@OnClick(R.id.btn)
	public void click(View view){
		Toast.makeText(MainActivity.this,"ʵ���˵��" , Toast.LENGTH_SHORT).show();
	}
	*/
	

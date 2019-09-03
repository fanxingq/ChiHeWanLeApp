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
 * ��ʱ��ת
 * ����ʹ��handler
 * @author ֣��
 *
 */
public class WelcomeStartActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_guide); //���ؿ�ʼ����
//		new Handler(new Handler.Callback() {
//			//������յ�����Ϣ�ķ���
//			@Override
//			public boolean handleMessage(Message msg) {
//				//ʵ��ҳ����ת
//				startActivity( new Intent(getApplicationContext(),MainActivity.class));
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 3000);  //����һ������Ϣ��ʱ������
		
		//ʹ��Java�еĶ�ʱ��timer���д���
		Timer timer=new Timer();
		timer.schedule(new Task(), 3000);  //��ʱ����ʱִ������ķ���
}
	//�������ڲ���
	class Task extends TimerTask{

		@Override
		public void run() {
			/**
			 * getBaseContext�����û����췽���е�activity�������ģ�����activity��activity�ݻ����ʹݻ�
			 * getApplicationContext����Ӧ�õ������ģ���������������Ӧ�ã�Ӧ�ôݻ����Ŵݻ�
			 */

			//ʵ��ҳ�����ת���ж��Ƿ��ǵ�һ�ν���
			if (SharedUtils.getWelcomeBoolean(getBaseContext())) {   //���Ϊtrue����ʾ���ǵ�һ�ν���
				startActivity(new Intent(getBaseContext(),MainActivity.class));  //�Ӵ�ҳ����ת����һ��ҳ��
			}else {
				startActivity(new Intent(WelcomeStartActivity.this,WelcomeGuideActivity.class));
				//������ʼ�¼
				SharedUtils.putWelcomeBoolean(getBaseContext(), true);
			}
			finish();
		}
		
	}
	
	
	
	
	
}
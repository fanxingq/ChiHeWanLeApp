package com.qust.chihewanleapp.my;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.qust.chihewanleapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginByMessage extends Activity{

	@ViewInject(R.id.login_by_pwd_text)  
	private TextView pwdText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_by_phone);
		ViewUtils.inject(this);
		

		
	}
	
	@OnClick(R.id.login_by_pwd_text)
	private void onClick(View v){
		switch (v.getId()) {
		case R.id.login_by_phone_text:
			finish();
			break;

		default:
			break;
		}
	}
	
	
}

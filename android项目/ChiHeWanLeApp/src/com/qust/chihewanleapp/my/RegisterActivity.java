package com.qust.chihewanleapp.my;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.User;
import com.qust.chihewanleapp.fragment.FragmentMy;
import com.qust.chihewanleapp.fragment.FragmentTuan;
import com.qust.chihewanleapp.utils.MyUtils;
import com.qust.chihewanleapp.utils.SharedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	@ViewInject(R.id.etUser)
	private EditText userName;
	@ViewInject(R.id.etPassw)
	private EditText userPwd;
	@ViewInject(R.id.etRePassw)
	private EditText userRePwd;
	@ViewInject(R.id.payPwd)
	private EditText payPwd;
	@ViewInject(R.id.register_btn)
	private Button reBtn;
	@ViewInject(R.id.res_tel)
	private EditText resTel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ViewUtils.inject(this);
	}

	@OnClick({ R.id.register_btn, R.id.register_index_back })
	public void OnClick(View v) {
		switch (v.getId()) {
		case R.id.register_btn:
			registerNow();
			break;
		case R.id.register_index_back:
			finish();
			break;

		default:

			break;

		}
	}

	private void registerNow() {
		final String userName = this.userName.getText().toString();
		String userPwd = this.userPwd.getText().toString();
		String userRePwd = this.userRePwd.getText().toString();
		String userPayPwd = this.payPwd.getText().toString();
		String userTel = this.resTel.getText().toString();
		User user=null;
		if (userName != null && userPayPwd != null && userPwd != null && userRePwd != null && userTel != null) {
			if (userPwd.equals(userRePwd)) {
				
				user=new User(0, userName, userPwd, userPayPwd, userTel);
				
		
			} else {
				Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(RegisterActivity.this, "请将信息补充完整", Toast.LENGTH_SHORT).show();
		}
		String json=JSON.toJSONString(user);
		UserManager.getInstance().registerUser(json, userName, new LoginCallback() {

			@Override
			public void success() {
				// TODO Auto-generated method stub
				SharedUtils.putUserName(RegisterActivity.this, userName);
				RegisterSuccess();
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void error() {
				// TODO Auto-generated method stub
				Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void fail() {
				// TODO Auto-generated method stub
				Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
			}

		});
		}
				
			/**
			 * 登录成功
			 */
			public void RegisterSuccess() {
				String userName = this.userName.getText().toString();
				// 创建intent对象实现跳转
				Intent intent = new Intent(this, FragmentMy.class);
				intent.putExtra("register", userName);
				setResult(MyUtils.RequstLoginCode, intent);
				finish();
			}

}

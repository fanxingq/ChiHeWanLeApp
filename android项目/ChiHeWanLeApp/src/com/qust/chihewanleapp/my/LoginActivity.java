package com.qust.chihewanleapp.my;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qust.chihewanleapp.MainActivity;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.Goods;
import com.qust.chihewanleapp.entity.LoginObject;
import com.qust.chihewanleapp.entity.RespondsObject;
import com.qust.chihewanleapp.entity.User;
import com.qust.chihewanleapp.fragment.FragmentTuan;
import com.qust.chihewanleapp.utils.MyUtils;
import com.qust.chihewanleapp.utils.SharedUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.R.raw;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ��¼����
 * 
 * @author zheng
 *
 */
public class LoginActivity extends Activity {

	@ViewInject(R.id.login_check_btn) // ��֤��
	private TextView checkBtn;
	@ViewInject(R.id.login_check_num)
	private EditText checkNum;
	@ViewInject(R.id.my_login_btn_pwd) // �����¼
	private ImageView loginBtn;

	@ViewInject(R.id.login_QQ) // QQ��������¼
	private TextView loginQQ;
	@ViewInject(R.id.login_weibo) // ΢����������¼
	private TextView loginWeibo;
	@ViewInject(R.id.login_user_name) // �û�����
	private EditText loginName;
	@ViewInject(R.id.login_by_pwd) // ��������
	private EditText loginPwd;
	@ViewInject(R.id.login_by_phone_text)
	private TextView phoneText;

	private String checksum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_login);
		ViewUtils.inject(this);

		// ��֤�������ʼ��
		setRandomView(checkBtn);

	}

	/**
	 * ������֤��
	 * 
	 * @param v
	 */
	private void setRandomView(TextView textView) {
		checksum = MyUtils.getRandom(4);
		textView.setText(checksum);
	}

	@OnClick({ R.id.my_login_btn_pwd, R.id.login_check_btn, R.id.login_QQ, R.id.login_index_back,
			R.id.login_by_phone_text,R.id.user_register })
	public void OnClick(View v) {
		switch (v.getId()) {
		case R.id.my_login_btn_pwd: // �����¼
			LoginIn();
			break;
		case R.id.login_check_btn: // ��ȡ��֤��
			setRandomView(checkBtn);
			break;
		case R.id.login_QQ: // QQ��¼
			loginByQQ();
			break;
		case R.id.login_weibo: // ΢����¼

			break;
		case R.id.login_index_back: // ���ؼ�
			finish();
			break;

		case R.id.login_by_phone_text:
			Intent intent = new Intent(this, LoginByMessage.class);
			startActivity(intent);
			break;
		case R.id.user_register:
			Intent intent1 = new Intent(this, RegisterActivity.class);
			startActivity(intent1);
			finish();
			break;

		default:
			break;
		}
	}

	/**
	 * QQ��������¼
	 */
	public void loginByQQ() {
		System.out.println("hello,i am QQ!");
	}

	/**
	 * �����¼
	 */
	private void LoginIn() {
		String check = checkNum.getText().toString();
		if (!check.toLowerCase().equals(checksum.toLowerCase())) {
			Toast.makeText(LoginActivity.this, "��֤�����", Toast.LENGTH_SHORT).show();
			setRandomView(checkBtn);
			return;
		}

		// ��ȡ�û�������û���������
		final String username = loginName.getText().toString();

		
		final String userpwd = loginPwd.getText().toString();
		final String userId = null;
		// Toast.makeText(LoginActivity.this, userpwd,
		// Toast.LENGTH_SHORT).show();
		// �ͷ���������
		UserManager.getInstance().login(username, userpwd,userId, new LoginCallback() {

			@Override
			public void success() {
				SharedUtils.putUserName(LoginActivity.this, username);
//				SharedUtils.putUserId(LoginActivity.this, userId);
				LoginSuccess();
				// LoginSuccess(); // ������¼��Ϣ
				Toast.makeText(LoginActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void error() {
				Toast.makeText(LoginActivity.this, "�û������������", Toast.LENGTH_SHORT).show();

			}

			@Override
			public void fail() {
				Toast.makeText(LoginActivity.this, "��¼ʧ��", Toast.LENGTH_SHORT).show();

			}
		});
	}

	/**
	 * ��¼�ɹ�
	 */
	public void LoginSuccess() {
		String userName = loginName.getText().toString();
		// ����intent����ʵ����ת
		Intent intent = new Intent(this, FragmentTuan.class);
		intent.putExtra("login", userName);
		SharedUtils.putUserImg(getApplicationContext(), R.drawable.person+"");
		setResult(MyUtils.RequstLoginCode, intent);
		finish();
	}
//	  public void LoginOrder(){
//		  String 
//	  }

}

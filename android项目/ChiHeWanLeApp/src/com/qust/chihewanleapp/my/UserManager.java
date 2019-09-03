package com.qust.chihewanleapp.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.qust.chihewanleapp.consts.Consts;
import com.qust.chihewanleapp.entity.LoginObject;
import com.qust.chihewanleapp.entity.User;
import com.qust.chihewanleapp.fragment.FragmentTuan;
import com.qust.chihewanleapp.utils.MyUtils;
import com.qust.chihewanleapp.utils.SharedUtils;

/**
 * Created by The_onE on 2016/1/10. �û���������������
 */
public class UserManager {
	private static UserManager instance;

	public synchronized static UserManager getInstance() {
		if (null == instance) {
			instance = new UserManager();
		}
		return instance;
	}

	// �Ƿ��ѵ�¼(ע�ᡢ��¼���Զ���¼)
	private boolean loginFlag = false;
	private String mUsername;
	private int mUserId;

	// login(USERNAME, PASSWORD)
	public void login(final String username, String password, String userId, final LoginCallback callback) {
		new HttpUtils().send(HttpMethod.GET, Consts.User_Login + "&username=" + username + "&password=" + password,
				new RequestCallBack<String>() {

					// ��ȡ�ɹ�
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						LoginObject object = JSON.parseObject(responseInfo.result, LoginObject.class);
						Log.i("@@@@@", object.toString());
						System.out.println(object);
						User user = object.getDatas();
						// Toast.makeText(LoginActivity.this,
						// responseInfo.result, Toast.LENGTH_SHORT).show();
						// LoginObject object=new LoginObject(json);
						if (object.getState() == 1) { // ��¼�ɹ�
							callback.success();
							loginFlag = true;
							mUsername = username;
							mUserId = user.getUserId();
						} else {
							callback.error();
						}
					}

					// ��ȡʧ��
					@Override
					public void onFailure(HttpException error, String msg) {
						// ��ӡʧ��
						callback.fail();
					}
				});

	}

	/**
	 * ע��
	 * 
	 * @param json
	 * @param username
	 * @param callback
	 */
	public void registerUser(String json, final String username, final LoginCallback callback) {
		RequestParams requestParams = new RequestParams();
		requestParams.addQueryStringParameter("user", json);
		requestParams.addQueryStringParameter("flag", "register");
		new HttpUtils().send(HttpMethod.POST, Consts.Register, requestParams, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				callback.success();
				loginFlag = true;
				mUsername = username;
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				callback.fail();
			}

		});

	}

	/**
	 * �Ƿ��ѵ�¼
	 * 
	 * @return
	 */
	public boolean checkLogin() {
		return loginFlag;
	}

	public String getUserInfo() {
		if (loginFlag) {
			return mUsername;
		} else {
			return null;
		}
	}
	public int getUserId() {
		if (loginFlag) {
			return mUserId;
		} else {
			return 0;
		}
	}
}
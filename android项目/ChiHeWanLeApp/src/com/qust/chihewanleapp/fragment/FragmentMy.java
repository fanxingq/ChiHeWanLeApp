package com.qust.chihewanleapp.fragment;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.qust.chihewanleapp.R;
import com.qust.chihewanleapp.my.LoginActivity;
import com.qust.chihewanleapp.my.MyCommentActivity;
import com.qust.chihewanleapp.my.MyFavoriteActivity;
import com.qust.chihewanleapp.my.MyWalletActivity;
import com.qust.chihewanleapp.my.PersonSetActivity;
import com.qust.chihewanleapp.my.UserManager;
import com.qust.chihewanleapp.utils.MyUtils;
import com.qust.chihewanleapp.utils.SharedUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * �ҵ�
 * @author zheng
 *
 */
public class FragmentMy extends Fragment implements OnClickListener{

	@ViewInject(R.id.index_my_list1_photo)  //ͷ��
	private ImageView loginPhoto;
	@ViewInject(R.id.my_login_text)  //�������¼��
	private TextView loginText;
	@ViewInject(R.id.login_by_right_arrow)
	private ImageView loginArrow;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.index_my, null);
		ViewUtils.inject(this,view);

		
		String username = UserManager.getInstance().getUserInfo();
		if (username != null) {
			loginText.setText(username);
		}
		
		return view;
	}
	@OnClick({R.id.index_my_list1_photo,R.id.my_login_text,R.id.login_by_right_arrow})
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.index_my_list1_photo:  //�û���¼
		case R.id.login_by_right_arrow:
		case R.id.my_login_text:
			if (UserManager.getInstance().checkLogin()) {  //����ѵ�¼������ģʽ
				// ��ת������Ϣ
				Toast.makeText(getContext(), "��ת������Ϣ", Toast.LENGTH_SHORT).show();
			} else {
				login();
			}
			break;

		default:
			break;
		}
	}

	
	/**
	 * ��¼��ť������ת
	 */
	private void login()  {
		
		Intent intent=new Intent(getActivity(),LoginActivity.class);
		startActivityForResult(intent, MyUtils.RequstLoginCode);				
		
	}
	
	/**
	 * ���շ���ֵ����
	 */
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==MyUtils.RequstLoginCode && resultCode==MyUtils.RequstLoginCode) {
//			loginText.setText(data.getStringExtra("login"));
			loginPhoto.setImageResource(R.drawable.person);
			String username = UserManager.getInstance().getUserInfo();
			if (username != null) {
				loginText.setText(username);
			}
		}
	}

	
}

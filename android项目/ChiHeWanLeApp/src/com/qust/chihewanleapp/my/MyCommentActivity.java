package com.qust.chihewanleapp.my;

import com.lidroid.xutils.ViewUtils;
import com.qust.chihewanleapp.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * �ҵĵ�������
 * @author zheng
 *
 */
public class MyCommentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_comment);
		ViewUtils.inject(this);
	}
}

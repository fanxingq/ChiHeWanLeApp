package com.qust.chihewanleapp.my;

import com.lidroid.xutils.ViewUtils;
import com.qust.chihewanleapp.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * �û����ý���
 * @author zheng
 *
 */
public class PersonSetActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_setindex);
		ViewUtils.inject(this);
		
		
	}
	
}

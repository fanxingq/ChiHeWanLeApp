package com.qust.chihewanleapp.tuan;



import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.qust.chihewanleapp.entity.Order;
import com.qust.chihewanleapp.fragment.FragmentMy;
import com.qust.chihewanleapp.fragment.FragmentTuan;
import com.qust.chihewanleapp.my.LoginActivity;
import com.qust.chihewanleapp.my.LoginCallback;
import com.qust.chihewanleapp.my.RegisterActivity;
import com.qust.chihewanleapp.my.UserManager;
import com.qust.chihewanleapp.utils.SharedUtils;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderNow extends Activity implements TextWatcher{

	@ViewInject(R.id.order_sort_title)
	private TextView goodsSortTitle;
	@ViewInject(R.id.order_desc)
	private TextView goodsDesc;
	@ViewInject(R.id.order_goods_price)
	private TextView Price;
	@ViewInject(R.id.order_price_c) // 优惠前价格
	private TextView order_price;
	@ViewInject(R.id.order_price_total) // 优惠后价格
	private TextView price_total;
	@ViewInject(R.id.order_count_text) // 数量
	private EditText countText;

	private String goodsId;
	private String goodsPrice; // 单价

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.now_order);
		ViewUtils.inject(this);
		String sortTitle; // 商品名
		String desc; // 副标题

		Bundle bundle = this.getIntent().getExtras();
		sortTitle = bundle.getString("sortTitle");
		desc = bundle.getString("desc");
		goodsPrice = bundle.getString("price");
		goodsId=bundle.getString("goodsid");
		goodsSortTitle.setText("【" + sortTitle + "】");
		goodsDesc.setText(desc);
		Price.setText("￥"+goodsPrice);
		countText.addTextChangedListener(this);
	}

	@OnClick({ R.id.order_delete, R.id.order_count_text, R.id.order_btn })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_delete:   //取消订单
			finish();
			break;
		case R.id.order_btn:     //立即下单
			orderNowSend();
			break;
//		case R.id.order_count_text:
//			EditTextLis(countText);
		default:
			break;
		}
	}

//	private void EditTextLis(EditText countText2) {
//		afterTextChanged((Editable) countText2);
//		
//	}

	

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			s=countText.getEditableText();
			if (s.toString().isEmpty()) {
				order_price.setText(("0.0"));
				price_total.setText(("0.0"));
				return;
			}
			int count = Integer.parseInt(s.toString());
			int price = Integer.parseInt(goodsPrice);
			order_price.setText((count * price) + "");
			price_total.setText((count * price) + "");

		}

	
	
	// 下订单
	private void orderNowSend() {
		if (!UserManager.getInstance().checkLogin()) {
			Toast.makeText(OrderNow.this, "请您先登录", Toast.LENGTH_SHORT).show();
			finish();
			Intent intent =new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			return;
		}
		final int foodId=Integer.parseInt(goodsId);
		Log.e("aaa", foodId+"");
		final int userId = UserManager.getInstance().getUserId();
		final String count = countText.getText().toString();
		final String goodsPrice2 = ((Integer.parseInt(countText.getText().toString())*(Integer.parseInt(goodsPrice)))+"");
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		final String time = format.format(date);

		Order order = null;
		if (count != null) {
			
			order = new Order(0, userId, count, time, goodsPrice2, foodId);
			
		} else {
			Toast.makeText(OrderNow.this, "请填写购买数量", Toast.LENGTH_SHORT).show();
		}
		String json = JSON.toJSONString(order);
		Toast.makeText(OrderNow.this,json, Toast.LENGTH_SHORT).show();
		Log.e("aa", json);
		RequestParams requestParams = new RequestParams();
		requestParams.addQueryStringParameter("order", json);
		requestParams.addQueryStringParameter("flag", "orderNow");
		new HttpUtils().send(HttpMethod.POST, Consts.Order, requestParams, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Toast.makeText(OrderNow.this, "下单成功", Toast.LENGTH_SHORT).show();
				finish();
				}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(OrderNow.this, "下单失败", Toast.LENGTH_SHORT).show();
			}

		});
	}

	

	
}

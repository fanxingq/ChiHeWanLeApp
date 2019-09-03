package com.qust.chihewanleapp.utils;

import com.qust.chihewanleapp.R;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;

/**
 * 实现标记的写入与读取，判断是否是第一次进入APP，导航页面显不显示
 *
 */
public class SharedUtils {
	private static final String FTLE_NAME="activity_main";   //定义一个文件名
	private static final String MODE_NAME="welcome";
	//获取boolean类型的值
	public static boolean getWelcomeBoolean(Context context) {    //context传入上下文对象
		//如果这个值在文件中存在，就读取出来，如果不存在，就返回false
		return context.getSharedPreferences(FTLE_NAME, context.MODE_PRIVATE).getBoolean(MODE_NAME, false);
	}
	/**
	 * 写入boolean类型的值
	 * @param context
	 * @param isFirst
	 */
	public static void putWelcomeBoolean(Context context,boolean isFirst){  //boolean判断是否是第一次进入
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();   //第二个参数表示操作模式
		editor.putBoolean(MODE_NAME, isFirst);   //根据传入值的不同，填充SharedPreferences对象
		editor.commit();  //调用commit方法进行提交
	}
	
	/**
	 * 写入一个String类型的数据
	 * @param context
	 * @param cityName
	 */
	public static void putCityName(Context context,String cityName){
		Editor editor=context.getSharedPreferences(FTLE_NAME, Context.MODE_APPEND).edit();
		editor.putString("cityName", cityName);
		editor.commit();
	}
	
	/**
	 * 获得string类型的值
	 * @param context
	 * @return
	 */
	public static String getCityName(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("cityName", "选择城市");
		
	}
	
	/**
	 * 写入登录的名称
	 * @param context
	 */
	public static void putUserName(Context context,String userName){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("userName", userName);
		editor.commit();
		
	}
	
	public static String getUserName(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("userName", "点击登录");
		
	}
	
	/**
	 * 用户ID
	 * @param context
	 * @param userId
	 
	public static void putUserId(Context context,String userId){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("userId", userId);
		editor.commit();
		
	}
	public static String getUserId(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("userId", "请先登录");
		
	}
	*/
	
	/**
	 * 商品id
	 * @param context
	 * @param goodsId
	 */
	public static void putGoodsId(Context context,String goodsId){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("goodsId", goodsId);
		editor.commit();
		
	}
	public static String getGoodsId(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("goodsId", "请选择商品");
		
	}
	/**
	 * zhaopian
	 * @param context
	 * @param goodsId
	 */
	public static void putUserImg(Context context,String goodsId){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("goodsId", goodsId);
		editor.commit();
		
	}
	public static String getUserImg(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("userImg",R.drawable.person+"" );
		
	}
	
}

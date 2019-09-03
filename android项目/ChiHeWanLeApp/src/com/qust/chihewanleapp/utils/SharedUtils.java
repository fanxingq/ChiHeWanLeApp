package com.qust.chihewanleapp.utils;

import com.qust.chihewanleapp.R;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;

/**
 * ʵ�ֱ�ǵ�д�����ȡ���ж��Ƿ��ǵ�һ�ν���APP������ҳ���Բ���ʾ
 *
 */
public class SharedUtils {
	private static final String FTLE_NAME="activity_main";   //����һ���ļ���
	private static final String MODE_NAME="welcome";
	//��ȡboolean���͵�ֵ
	public static boolean getWelcomeBoolean(Context context) {    //context���������Ķ���
		//������ֵ���ļ��д��ڣ��Ͷ�ȡ��������������ڣ��ͷ���false
		return context.getSharedPreferences(FTLE_NAME, context.MODE_PRIVATE).getBoolean(MODE_NAME, false);
	}
	/**
	 * д��boolean���͵�ֵ
	 * @param context
	 * @param isFirst
	 */
	public static void putWelcomeBoolean(Context context,boolean isFirst){  //boolean�ж��Ƿ��ǵ�һ�ν���
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();   //�ڶ���������ʾ����ģʽ
		editor.putBoolean(MODE_NAME, isFirst);   //���ݴ���ֵ�Ĳ�ͬ�����SharedPreferences����
		editor.commit();  //����commit���������ύ
	}
	
	/**
	 * д��һ��String���͵�����
	 * @param context
	 * @param cityName
	 */
	public static void putCityName(Context context,String cityName){
		Editor editor=context.getSharedPreferences(FTLE_NAME, Context.MODE_APPEND).edit();
		editor.putString("cityName", cityName);
		editor.commit();
	}
	
	/**
	 * ���string���͵�ֵ
	 * @param context
	 * @return
	 */
	public static String getCityName(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("cityName", "ѡ�����");
		
	}
	
	/**
	 * д���¼������
	 * @param context
	 */
	public static void putUserName(Context context,String userName){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("userName", userName);
		editor.commit();
		
	}
	
	public static String getUserName(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("userName", "�����¼");
		
	}
	
	/**
	 * �û�ID
	 * @param context
	 * @param userId
	 
	public static void putUserId(Context context,String userId){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("userId", userId);
		editor.commit();
		
	}
	public static String getUserId(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("userId", "���ȵ�¼");
		
	}
	*/
	
	/**
	 * ��Ʒid
	 * @param context
	 * @param goodsId
	 */
	public static void putGoodsId(Context context,String goodsId){
		
		Editor editor=context.getSharedPreferences(FTLE_NAME, context.MODE_APPEND).edit();
		editor.putString("goodsId", goodsId);
		editor.commit();
		
	}
	public static String getGoodsId(Context context){
		return context.getSharedPreferences(FTLE_NAME, Context.MODE_PRIVATE).getString("goodsId", "��ѡ����Ʒ");
		
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

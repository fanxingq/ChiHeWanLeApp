package com.qust.chihewanleapp.utils;

import com.qust.chihewanleapp.R.drawable;

public class MyUtils {

	
	public static String[] nvaSorts={"美食","电影","酒店","KTV","外卖","休闲娱乐","旅游","周边游","都市丽人",
			"全部分类"};
	public static int[] nvaImages={drawable.nav_food,drawable.nav_movie,drawable.nav_hotel,
			drawable.nav_ktv,drawable.nav_footer,drawable.nav_relax,drawable.nav_zhoubianyou,
			drawable.nav_discount,drawable.nav_beauty,drawable.nav_more
			};
	public static String[] allSorts={"全部分类","美食","电影","酒店","KTV","外卖","休闲娱乐","旅游","周边游","都市丽人","车来了",
			"亲子","学习培训","温泉","家装","运动健身"};
	public static int[] allCategorys={drawable.nav_more,drawable.nav_food,drawable.nav_movie,drawable.nav_hotel,
			drawable.nav_ktv,drawable.nav_footer,drawable.nav_relax,drawable.nav_zhoubianyou,
			drawable.nav_discount,drawable.nav_beauty,drawable.nav_baby,drawable.nav_coffee,
			drawable.nav_shoppingmall,drawable.nav_jingdian,drawable.nav_jiazhuang,drawable.nav_relax
			};
	public static String[] remenSorts={"美食","电影","外卖","休闲娱乐","全部"};
	public static int[] remenImages={drawable.nav_food,drawable.nav_movie,drawable.nav_coffee,
			drawable.nav_relax,drawable.nav_more};
	//返回值
	public static final int RequstCityCode=2;   //城市
	public static final int RequstLoginCode=1;  //登录
	
	//定义字符
	private static final String RANDOMS="1234567890asdfghjklqwertyuiopzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	//定义验证码
	public static String getRandom(int num){
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < num; i++) {
			int random=(int) (Math.random()*RANDOMS.length());
			stringBuffer.append(RANDOMS.charAt(random));
		}
		return stringBuffer.toString();
	}
	
	
	
	
	
	
	
	
	
}

package com.qust.chihewanleapp.utils;

import com.qust.chihewanleapp.R.drawable;

public class MyUtils {

	
	public static String[] nvaSorts={"��ʳ","��Ӱ","�Ƶ�","KTV","����","��������","����","�ܱ���","��������",
			"ȫ������"};
	public static int[] nvaImages={drawable.nav_food,drawable.nav_movie,drawable.nav_hotel,
			drawable.nav_ktv,drawable.nav_footer,drawable.nav_relax,drawable.nav_zhoubianyou,
			drawable.nav_discount,drawable.nav_beauty,drawable.nav_more
			};
	public static String[] allSorts={"ȫ������","��ʳ","��Ӱ","�Ƶ�","KTV","����","��������","����","�ܱ���","��������","������",
			"����","ѧϰ��ѵ","��Ȫ","��װ","�˶�����"};
	public static int[] allCategorys={drawable.nav_more,drawable.nav_food,drawable.nav_movie,drawable.nav_hotel,
			drawable.nav_ktv,drawable.nav_footer,drawable.nav_relax,drawable.nav_zhoubianyou,
			drawable.nav_discount,drawable.nav_beauty,drawable.nav_baby,drawable.nav_coffee,
			drawable.nav_shoppingmall,drawable.nav_jingdian,drawable.nav_jiazhuang,drawable.nav_relax
			};
	public static String[] remenSorts={"��ʳ","��Ӱ","����","��������","ȫ��"};
	public static int[] remenImages={drawable.nav_food,drawable.nav_movie,drawable.nav_coffee,
			drawable.nav_relax,drawable.nav_more};
	//����ֵ
	public static final int RequstCityCode=2;   //����
	public static final int RequstLoginCode=1;  //��¼
	
	//�����ַ�
	private static final String RANDOMS="1234567890asdfghjklqwertyuiopzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	//������֤��
	public static String getRandom(int num){
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < num; i++) {
			int random=(int) (Math.random()*RANDOMS.length());
			stringBuffer.append(RANDOMS.charAt(random));
		}
		return stringBuffer.toString();
	}
	
	
	
	
	
	
	
	
	
}

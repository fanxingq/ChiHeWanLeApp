package com.qust.chihewanleapp.consts;

/**
 * ��ų��������ӷ�����
 * @author zheng
 *
 */
public class Consts {
    //���ӷ�����
	public static final String HOST="http://10.0.2.2:8080/ChiHeWanLeapp_Server";
	//city���ݵ�ַ
	public static final String City_Data_URI=HOST+"/CityServlet";
	
	//��Ʒ����
	public static final String Category_Data_URI=HOST+"/";
	
	//��Ʒ���б���Ϣ
	public static final String Goods_Datas_URL=HOST+"/GoodsServlet";
	
	//��¼��֤
	public static final String User_Login=HOST+"/UserServlet?flag=login";
	
	//�û�ע��
	public static final String Register=HOST+"/RegisterServlet";
	
	//�µ�
	public static final String Order=HOST+"/OrderServlet";
	
	//��Ʒ������
	public static final String Goods_Category_URL=HOST+"/GoodsCategory?flag=selectGoods";
}

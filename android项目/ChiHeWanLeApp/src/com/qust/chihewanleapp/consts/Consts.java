package com.qust.chihewanleapp.consts;

/**
 * 存放常量，连接服务器
 * @author zheng
 *
 */
public class Consts {
    //连接服务器
	public static final String HOST="http://10.0.2.2:8080/ChiHeWanLeapp_Server";
	//city数据地址
	public static final String City_Data_URI=HOST+"/CityServlet";
	
	//商品分类
	public static final String Category_Data_URI=HOST+"/";
	
	//商品的列表信息
	public static final String Goods_Datas_URL=HOST+"/GoodsServlet";
	
	//登录验证
	public static final String User_Login=HOST+"/UserServlet?flag=login";
	
	//用户注册
	public static final String Register=HOST+"/RegisterServlet";
	
	//下单
	public static final String Order=HOST+"/OrderServlet";
	
	//商品分类点击
	public static final String Goods_Category_URL=HOST+"/GoodsCategory?flag=selectGoods";
}

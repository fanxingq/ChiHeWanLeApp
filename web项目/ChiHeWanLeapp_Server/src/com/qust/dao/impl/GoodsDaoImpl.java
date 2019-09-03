package com.qust.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import org.apache.commons.lang.StringUtils;

import com.qust.bean.Goods;
import com.qust.bean.Shop;
import com.qust.dao.GoodsDao;



public class GoodsDaoImpl extends BaseDao implements GoodsDao{
	//定义的表中的字段
	private String str="product.product_id,product.category_id,product.shop_id,product.city_id,"
			+ " product.product_sort_title,product.product_title,product.product_image,"
			+ "product.product_start_time,product.product_value,product.product_price,product.product_ribat,"
			+ "product.product_bought,product.product_post,"
			+ "product.product_soldout,product.product_tip,product.product_end_time,product.product_detail,"
			+ "product.product_distance,shop.shop_id,"
			+ "shop.shop_name,shop.shop_tel,shop.shop_address,shop.shop_area,shop.shop_open_time";
	
	

	/**
	 * 获取商品集合的方法实现
	 */
	@Override
	public List<Goods> getList(String cityId) {
	
		Connection conn=null;
		Statement stm=null;
		ResultSet res=null;
		String sql="select" +" "+str +" "+"from product,shop where product.shop_id=shop.shop_id"
				+ " and city_id =1";
		List<Goods> goods=null;
		try{
			conn=getConn();
		    stm=conn.createStatement();
		    System.out.println("产品查询的sql语句"+sql);
		    res=stm.executeQuery(sql);
		    goods=new ArrayList<Goods>();
		    while (res.next()) {
				Goods product=new Goods();
				product.setId(res.getString("product_id"));
				product.setCategoryId(res.getString("category_id"));
				product.setShopId(res.getString("shop_id"));
				product.setCityId(res.getString("city_id"));
				product.setSortTitle(res.getString("product_sort_title"));
				product.setTitle(res.getString("product_title"));
				product.setImgUrl(res.getString("product_image"));
				product.setStartTime(res.getString("product_start_time"));
				product.setValue(res.getString("product_value"));
				product.setPrice(res.getString("product_price"));
				product.setRibat(res.getString("product_ribat"));
				product.setBought(res.getString("product_bought"));
				product.setPost(res.getString("product_post"));
				product.setSoldOut(res.getString("product_soldout"));
				product.setTip(res.getString("product_tip"));
				product.setEndTime(res.getString("product_end_time"));
				product.setDetail(res.getString("product_detail"));
				product.setDistance(res.getString("product_distance"));
				
				Shop shop=new Shop();
				shop.setShopId(res.getString("shop_id"));
				shop.setShopName(res.getString("shop_name"));
				shop.setShopTel(res.getString("shop_tel"));
				shop.setShopAddress(res.getString("shop_address"));
				shop.setShopArea(res.getString("shop_area"));
				shop.setShopOpentime(res.getString("shop_open_time"));
				product.setShop(shop);
				
				goods.add(product);
			
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(res, stm, conn);
		}

		return goods;
	}



	@Override
	public List<Goods> getListByCategory(String cityId, String catId) {

		Connection conn=null;
		Statement stm=null;
		ResultSet res=null;
		String sql="select" +" "+str +" "+"from product,shop where product.shop_id=shop.shop_id"
				+ " and  city_id =" +cityId+" and category_id ='"+catId+"'";
		List<Goods> goods=null;
		try{
			conn=getConn();
		    stm=conn.createStatement();
		    System.out.println("按类别产品查询的sql语句"+sql);
		    res=stm.executeQuery(sql);
		    goods=new ArrayList<Goods>();
		    while (res.next()) {
				Goods product=new Goods();
				product.setId(res.getString("product_id"));
				product.setCategoryId(res.getString("category_id"));
				product.setShopId(res.getString("shop_id"));
				product.setCityId(res.getString("city_id"));
				product.setSortTitle(res.getString("product_sort_title"));
				product.setTitle(res.getString("product_title"));
				product.setImgUrl(res.getString("product_image"));
				product.setStartTime(res.getString("product_start_time"));
				product.setValue(res.getString("product_value"));
				product.setPrice(res.getString("product_price"));
				product.setRibat(res.getString("product_ribat"));
				product.setBought(res.getString("product_bought"));
				product.setPost(res.getString("product_post"));
				product.setSoldOut(res.getString("product_soldout"));
				product.setTip(res.getString("product_tip"));
				product.setEndTime(res.getString("product_end_time"));
				product.setDetail(res.getString("product_detail"));
				product.setDistance(res.getString("product_distance"));
				
				Shop shop=new Shop();
				shop.setShopId(res.getString("shop_id"));
				shop.setShopName(res.getString("shop_name"));
				shop.setShopTel(res.getString("shop_tel"));
				shop.setShopAddress(res.getString("shop_address"));
				shop.setShopArea(res.getString("shop_area"));
				shop.setShopOpentime(res.getString("shop_open_time"));
				product.setShop(shop);

				
				goods.add(product);
			
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(res, stm, conn);
		}

		return goods;
	}

	
}

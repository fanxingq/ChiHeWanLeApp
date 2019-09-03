package com.qust.dao;

import java.util.List;

import com.qust.bean.Goods;

public interface GoodsDao {

	//根据城市id，分类id，当前的页码，每一页中的容量，返回获取的商品集合信息
	public List<Goods>getListByCategory(String cityId,String catId);  //城市id 分类id
	
	public List<Goods>getList(String cityId);

//	public double getCount(String cityId, String catId);  
	
	

}

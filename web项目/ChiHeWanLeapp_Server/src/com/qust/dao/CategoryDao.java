package com.qust.dao;

import java.util.List;

import com.qust.bean.Category;

/**
 * 分类
 * @author zheng
 *
 */
public interface CategoryDao {

	List<Category> getCategoryList();  //存放category的list集合，即每一种商品类别的总数
	long getCategoryTotal();   //总分类条数
	
}

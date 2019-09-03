package com.qust.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.qust.bean.Category;
import com.qust.dao.CategoryDao;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	public List<Category> getCategoryList() {
		Connection conn=null;
		Statement stm=null;
		ResultSet res=null;
		List<Category> result=null;
		
		try {
			conn=getConn();
			stm=conn.createStatement();
			res=stm.executeQuery("select category_id,count (category_id)number from products group by category_id order by category_id");
			result=new ArrayList<Category>();
			while (res.next()) {
				Category category=new Category();
				category.setCategoryId(res.getString("category_id"));
				category.setCategoryNum(res.getLong("number"));
				result.add(category);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close(res, stm, conn);
		}
		return result;
		
		
		
	}

	public long getCategoryTotal() {
		
		return 0;
	}

}

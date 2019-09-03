package com.qust.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.qust.bean.City;
import com.qust.dao.CityDao;

public class CityDaoImpl extends BaseDao implements CityDao{

	@Override
	public List<City> getCity() {
		Connection conn=null;
		Statement stm=null;
		ResultSet res=null;
		List<City>cities=null;
		try {
			conn=getConn(); //获取数据库的连接对象
			stm=(Statement) conn.createStatement(); //执行sql语句
			res=stm.executeQuery("select * from city order by city_sortkey");   //获取结果集对象
			cities=new ArrayList<City>();   //初始化list集合
			//将结果集放进循环列表里
			while (res.next()) {
				City city=new City();
				city.setCityId(res.getString("city_id"));
				city.setCityName(res.getString("city_name"));
				city.setSortKey(res.getString("city_sortkey"));
				cities.add(city);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  return null;
		  }finally{
			  close(res, stm, conn);
		  }
		
		return cities;
		
	}

}

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
			conn=getConn(); //��ȡ���ݿ�����Ӷ���
			stm=(Statement) conn.createStatement(); //ִ��sql���
			res=stm.executeQuery("select * from city order by city_sortkey");   //��ȡ���������
			cities=new ArrayList<City>();   //��ʼ��list����
			//��������Ž�ѭ���б���
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

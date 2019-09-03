package com.qust.dao.impl;

import java.sql.PreparedStatement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.qust.bean.Order;
import com.qust.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	
	/**
	 * 下订单
	 */
	@Override
	public int orderNow(Order order) {

		
		Connection conn=null;
		Statement stm=null;
		PreparedStatement pstm;
		int result=0;
		try {
			conn=getConn(); //获取数据库的连接对象
			stm=(Statement) conn.createStatement(); //执行sql语句
			String sql="INSERT INTO `orders` VALUES(0,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, order.getUser_id());
			pstm.setString(2, order.getOrders_product_count());
			pstm.setString(3, order.getOrders_time());
			pstm.setString(4, order.getOrders_all_price());
			pstm.setInt(5, order.getOrders_product_id());
			result=pstm.executeUpdate();			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }finally{
			  close(null, stm, conn);
		  }
		
	return result;	
	}
	}


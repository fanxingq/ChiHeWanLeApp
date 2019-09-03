package com.qust.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.qust.bean.City;
import com.qust.bean.User;
import com.qust.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	/**
	 * 用户登录
	 */
	@Override
	public User login(String username,String password) {
		Connection conn=null;
		Statement stm=null;
		ResultSet res=null;
		List<User> users=null;
		try {
			conn=getConn(); //获取数据库的连接对象
			stm=(Statement) conn.createStatement(); //执行sql语句
			res=stm.executeQuery("select * from user where user_name='"+username+"' and user_login_pwd='"+password+"'");   //获取结果集对象
			
			//将结果集放进循环列表里
			while (res.next()) {
				User user=new User();
				user.setUserId(res.getInt("user_id"));
				user.setUserame(res.getString("user_name"));
				user.setUserLoginPwd(res.getString("user_login_pwd"));
				user.setUserPayPwd(res.getString("user_pay_pwd"));
				user.setUserTel(res.getString("user_tel"));
				return user;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }finally{
			  close(res, stm, conn);
		  }
		
	return null;	
	}

	/**
	 * 用户注册
	 */
	@Override
	public int  Register(User user) {
		
		Connection conn=null;
		Statement stm=null;
		PreparedStatement pstm;
		int result=0;
		try {
			conn=getConn(); //获取数据库的连接对象
			stm=(Statement) conn.createStatement(); //执行sql语句
			String sql="INSERT INTO `user` VALUES(0,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserame());
			pstm.setString(2, user.getUserLoginPwd());
			pstm.setString(3, user.getUserPayPwd());
			pstm.setString(4, user.getUserTel());
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

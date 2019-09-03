package com.qust.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.qust.dao.Dao;


public class BaseDao {

	private static String url="jdbc:mysql://localhost:3306/eatplay";
	private static String user="root";
	private static String password="123";
   
		static{
		//注册数据库的驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
//		System.out.println("1111111111111");
//		//通过Connection对象获得Statement对象
//				Statement  stmt=(Statement) conn.createStatement();
//				//使用Statement执行sql语句
//				String sql="select * from city";
//				ResultSet res=stmt.executeQuery(sql);
//				//操作ResultSet结果集
//				System.out.println("city_id|city_name|city_sortkey");
//				while (res.next()) {
//					int id=res.getInt("city_id");
//					String name=res.getString("city_name");
//					String sortkey=res.getString("city_sortkey");
//					System.out.println(id+"|"+name+"|"+sortkey);	
//				}
//				//回收数据库
//				res.close();
//				stmt.close();
//				conn.close();
}
    //获取数据库连接的方法
    protected Connection getConn() throws Exception{
		return new Inner().getConn();
		
	}
    //关闭当前的资源对象
    protected void  close(ResultSet res,Statement stm,Connection conn) {
		new Inner().close(res, stm, conn);
	}
    //继承接口Dao
    private class Inner implements Dao{
    	public Connection getConn() throws Exception{
			return (Connection) DriverManager.getConnection(url,user,password);
		}
    	
    	public void close(ResultSet res,Statement stm,Connection conn){
    		if (res!=null) {
    			try {
				res.close();
				res=null;
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
    		if (stm!=null) {
				try {
					stm.close();
					stm=null;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
    	}

		@Override
		public void close(ResultSet res, com.mysql.jdbc.Statement stm,
				Connection conn) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    }
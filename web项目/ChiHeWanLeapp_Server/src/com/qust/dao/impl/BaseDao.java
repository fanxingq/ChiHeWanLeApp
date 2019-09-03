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
		//ע�����ݿ������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
//		System.out.println("1111111111111");
//		//ͨ��Connection������Statement����
//				Statement  stmt=(Statement) conn.createStatement();
//				//ʹ��Statementִ��sql���
//				String sql="select * from city";
//				ResultSet res=stmt.executeQuery(sql);
//				//����ResultSet�����
//				System.out.println("city_id|city_name|city_sortkey");
//				while (res.next()) {
//					int id=res.getInt("city_id");
//					String name=res.getString("city_name");
//					String sortkey=res.getString("city_sortkey");
//					System.out.println(id+"|"+name+"|"+sortkey);	
//				}
//				//�������ݿ�
//				res.close();
//				stmt.close();
//				conn.close();
}
    //��ȡ���ݿ����ӵķ���
    protected Connection getConn() throws Exception{
		return new Inner().getConn();
		
	}
    //�رյ�ǰ����Դ����
    protected void  close(ResultSet res,Statement stm,Connection conn) {
		new Inner().close(res, stm, conn);
	}
    //�̳нӿ�Dao
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
package com.qust.dao;

import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public interface Dao {

	public Connection getConn()throws Exception;
	public void close(ResultSet res,Statement stm,Connection conn);
}

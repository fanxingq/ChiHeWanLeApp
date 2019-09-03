package com.qust.dao;

import com.qust.bean.User;

public interface UserDao {

	public User login(String username,String password);   //获取用户列表
//	public List<User>getUser(String name,String pwd);
	
	public int Register(User user);  //用户注册
	
}

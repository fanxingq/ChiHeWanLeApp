package com.qust.dao;

import com.qust.bean.User;

public interface UserDao {

	public User login(String username,String password);   //��ȡ�û��б�
//	public List<User>getUser(String name,String pwd);
	
	public int Register(User user);  //�û�ע��
	
}

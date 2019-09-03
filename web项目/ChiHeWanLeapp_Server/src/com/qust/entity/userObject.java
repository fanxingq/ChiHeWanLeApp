package com.qust.entity;

import java.util.List;

import com.qust.bean.City;

/**
 * 定义的一个数据库获取对象返回监听
 * @author zheng
 *
 */
public class userObject {

	private String msg;
	private int state=1;  //0表示失败；1表示成功
	private int userId; //存放真正需要解析的数据
	private int page;
	private int size;
	private int count;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public userObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	//构造函数
//	public userObject (int state,String msg) {
//		this.state=state;
//		this.msg=msg;
//	}
//	public userObject (int state,String userid) {
//		this.state=state;
//		this.userId=userid;
//	}
	public userObject (int state,int i,String msg) {
		this.state=state;
		this.userId=i;
		this.msg=msg;
	}
	
	
}

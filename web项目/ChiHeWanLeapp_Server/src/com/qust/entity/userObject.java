package com.qust.entity;

import java.util.List;

import com.qust.bean.City;

/**
 * �����һ�����ݿ��ȡ���󷵻ؼ���
 * @author zheng
 *
 */
public class userObject {

	private String msg;
	private int state=1;  //0��ʾʧ�ܣ�1��ʾ�ɹ�
	private int userId; //���������Ҫ����������
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
	
//	//���캯��
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

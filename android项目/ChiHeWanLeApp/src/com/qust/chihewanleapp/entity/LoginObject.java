package com.qust.chihewanleapp.entity;

public class LoginObject{

	private String msg;
	private int state;
	private User datas;
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
	public User getDatas() {
		return datas;
	}
	public void setDatas(User datas) {
		this.datas = datas;
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
	public LoginObject() {
		super();
		// TODO Auto-generated constructor stub
	}
//public LoginObject(User datas) {
//		
////		this.state = state;
//		this.datas = datas;
//	}
	@Override
	public String toString() {
		return "LoginObject [msg=" + msg + ", state=" + state + ", datas=" + datas + ", page=" + page + ", size=" + size
				+ ", count=" + count + "]";
	}
	
	
	
	
}

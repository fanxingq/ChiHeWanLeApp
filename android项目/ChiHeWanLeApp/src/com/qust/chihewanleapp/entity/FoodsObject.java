package com.qust.chihewanleapp.entity;

public class FoodsObject<Goods> {

	private String msg;
	private int state;
	private Goods datas;
	private int page;
	private int size;
	private int count;
	private int num;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
	public Goods getDatas() {
		return datas;
	}
	public void setDatas(Goods datas) {
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
	public FoodsObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodsObject(int state, Goods datas) {
		
		this.state = state;
		this.datas = datas;
	}
	
	
	
	
}

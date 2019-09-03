package com.qust.entity;

import java.util.List;

import com.qust.bean.City;

/**
 * �����һ�����ݿ��ȡ���󷵻ؼ���
 * @author zheng
 *
 */
public class ResponseObject {

	private String msg;
	private int state=1;  //0��ʾʧ�ܣ�1��ʾ�ɹ�
	private Object datas; //���������Ҫ����������
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
	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
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
	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//���캯��
	public ResponseObject (int state,String msg) {
		this.state=state;
		this.msg=msg;
	}
	public ResponseObject (int state,Object datas) {
		this.state=state;
		this.datas=datas;
	}
	public ResponseObject (int state,Object datas,String msg) {
		this.state=state;
		this.datas=datas;
		this.msg=msg;
	}
	
	
}

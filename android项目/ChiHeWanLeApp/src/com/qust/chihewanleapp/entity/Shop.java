package com.qust.chihewanleapp.entity;

import java.io.Serializable;

/**
 * �̼���Ϣ
 * @author zheng
 *
 */
public class Shop implements Serializable{

	private String shopId;  //�̼�id
	private String shopName;  //�̼�����
	private String shopTel;  //�̼���ϵ��ʽ
	private String shopAddress;  //�̼ҵ�ַ
	private String shopArea;  //�̼���������
	private String shopOpentime;  
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getShopOpentime() {
		return shopOpentime;
	}
	public void setShopOpentime(String shopOpentime) {
		this.shopOpentime = shopOpentime;
	}
	
	
	
}

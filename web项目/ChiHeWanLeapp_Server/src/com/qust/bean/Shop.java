package com.qust.bean;

/**
 * 商家信息
 * @author zheng
 *
 */
public class Shop {

	private String shopId;  //商家id
	private String shopName;  //商家名称
	private String shopTel;  //商家联系方式
	private String shopAddress;  //商家地址
	private String shopArea;  //商家所在区域
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

 package com.qust.chihewanleapp.entity;

import java.io.Serializable;

/**
  * ��Ʒ
  * @author zheng
  *
  */
public class Goods implements Serializable{

	private String id;   //��ƷId
	private String categoryId; //����Id
	private String shopId;  //�̼�ID
	private String cityId;  //����id
	private String title;   //��Ʒ���ƣ�С����
	private String sortTitle;  //��Ʒ���⣬�Ӵ�
	private String imgUrl;   //��ƷͼƬ·��
	private String startTime;  //��ʼʱ��
	private String value;  //��Ʒԭ��
	private String price;  //��Ʒ�����ۼ�
	private String ribat;  //��Ʒ���ۿ�
	private String bought;  //��ǰ������

	private String post;  //
	private String soldOut;  //�Ƿ�������
	private String tip;  //  �Ż�ȯ
	private String endTime;  //����ʱ��
	private String detail;   //������Ʒ��ϸ��Ϣ

	private String distance;  //
	private Shop shop;  //��Ʒ�����̼�
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSortTitle() {
		return sortTitle;
	}
	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRibat() {
		return ribat;
	}
	public void setRibat(String ribat) {
		this.ribat = ribat;
	}
	public String getBought() {
		return bought;
	}
	public void setBought(String bought) {
		this.bought = bought;
	}
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
	
}

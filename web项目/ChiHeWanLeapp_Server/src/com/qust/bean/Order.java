package com.qust.bean;

public class Order {

	private int order_id;
	private int user_id;
	private String orders_product_count;
	private String orders_time;
	private String orders_all_price;
	private int orders_product_id;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOrders_product_count() {
		return orders_product_count;
	}
	public void setOrders_product_count(String orders_product_count) {
		this.orders_product_count = orders_product_count;
	}
	public String getOrders_time() {
		return orders_time;
	}
	public void setOrders_time(String orders_time) {
		this.orders_time = orders_time;
	}
	public String getOrders_all_price() {
		return orders_all_price;
	}
	public void setOrders_all_price(String orders_all_price) {
		this.orders_all_price = orders_all_price;
	}
	public int getOrders_product_id() {
		return orders_product_id;
	}
	public void setOrders_product_id(int orders_product_id) {
		this.orders_product_id = orders_product_id;
	}
	
	
}

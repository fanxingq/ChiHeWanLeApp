package com.qust.bean;

/**
 * ����
 * @author zheng
 *
 */
public class Category {

	private String categoryId;  //����id
	private long categoryNum;   //����������Ŀ
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public long getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(long categoryNum) {
		this.categoryNum = categoryNum;
	}
	
	public String toString(){
		return categoryId+"-->"+categoryNum;
		
	}
	
	
}

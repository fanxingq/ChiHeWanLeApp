package com.qust.bean;

/**
 * 分类
 * @author zheng
 *
 */
public class Category {

	private String categoryId;  //分类id
	private long categoryNum;   //具体类别的数目
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

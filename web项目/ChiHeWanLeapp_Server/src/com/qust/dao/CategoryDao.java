package com.qust.dao;

import java.util.List;

import com.qust.bean.Category;

/**
 * ����
 * @author zheng
 *
 */
public interface CategoryDao {

	List<Category> getCategoryList();  //���category��list���ϣ���ÿһ����Ʒ��������
	long getCategoryTotal();   //�ܷ�������
	
}

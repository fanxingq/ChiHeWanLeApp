package com.qust.dao;

import java.util.List;

import com.qust.bean.Goods;

public interface GoodsDao {

	//���ݳ���id������id����ǰ��ҳ�룬ÿһҳ�е����������ػ�ȡ����Ʒ������Ϣ
	public List<Goods>getListByCategory(String cityId,String catId);  //����id ����id
	
	public List<Goods>getList(String cityId);

//	public double getCount(String cityId, String catId);  
	
	

}

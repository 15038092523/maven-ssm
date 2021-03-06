package com.cmcc.service;

import java.util.List;

import com.cmcc.entity.Sku;

public interface GoodsService {

	Sku getEntity(Integer id);
	
	void updateSku(Sku sku);
	
	List<Sku> getAll(int startPage,int rows);
	
	Integer deleteGoods(Integer id);
	
	Integer deleteGoodsList(List<Integer> list);
	
	List<Sku> getAllByParam(Sku sku);
	
	Integer getTotal(Sku sku);
}

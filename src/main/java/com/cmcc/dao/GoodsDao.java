package com.cmcc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmcc.entity.Sku;

public interface GoodsDao {

	Sku getEntity(Integer id);
	
	void updateSku(Sku sku);
	
	List<Sku> getAll(@Param("startPage")int startPage,@Param("rows")int rows);
	
	Integer deleteGoods(Integer id);
	
	Integer deleteGoodsList(List<Integer> list);

	List<Sku> getAllByParam(@Param("sku")Sku sku);
}

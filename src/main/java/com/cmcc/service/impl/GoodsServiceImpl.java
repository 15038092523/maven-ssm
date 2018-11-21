package com.cmcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.dao.GoodsDao;
import com.cmcc.entity.Sku;
import com.cmcc.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsDao goodsDao;
	
	public Sku getEntity(Integer id) {
		return goodsDao.getEntity(id);
	}

	public void updateSku(Sku sku) {
		goodsDao.updateSku(sku);
	}

	public List<Sku> getAll(int startPage,int rows) {
		return goodsDao.getAll(startPage,rows);
	}

	public Integer deleteGoods(Integer id) {
		return goodsDao.deleteGoods(id);
	}

}

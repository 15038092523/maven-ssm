package com.cmcc.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.common.DataSource;
import com.cmcc.common.DynamicDataSourceHolder;
import com.cmcc.common.JsonUtils;
import com.cmcc.dao.GoodsDao;
import com.cmcc.dao.JedisClient;
import com.cmcc.entity.Sku;
import com.cmcc.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private JedisClient jedisClient;

	@DataSource("dataSource2")
	public Sku getEntity(Integer id) {
		Sku sku = null;
		// 从缓存中取内容
		try {
			String json = jedisClient.get("SkuId:" + id);
			if (!StringUtils.isBlank(json)) {
				// 把字符串转换成对象
				sku = JsonUtils.jsonToPojo(json, Sku.class);
				return sku;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DynamicDataSourceHolder.setDataSource("dataSource2");
		// ======不使用springAop，手动切换数据源
		sku = goodsDao.getEntity(id);
		try {
			if (sku != null) {
				// 把商品信息写入缓存
				jedisClient.set("SkuId:" + id, JsonUtils.objectToJson(sku));
				// 设置key的有效期
				jedisClient.expire("SkuId:" + id, 1000000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sku;
	}

	public void updateSku(Sku sku) {
		try {
			goodsDao.updateSku(sku);
			jedisClient.del("SkuId:" + sku.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Sku> getAll(int startPage, int rows) {
		return goodsDao.getAll(startPage, rows);
	}

	public Integer deleteGoods(Integer id) {
		Integer result = goodsDao.deleteGoods(id);
		if (result > 0) {
			try {
				jedisClient.del("SkuId:" + id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Integer deleteGoodsList(List<Integer> list) {
		if (list != null && !list.isEmpty()) {
			Integer result = goodsDao.deleteGoodsList(list);
			for (Integer id : list) {
				jedisClient.del("SkuId:" + id);
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	public List<Sku> getAllByParam(Sku sku) {
		// TODO Auto-generated method stub

		return goodsDao.getAllByParam(sku);
	}

	public Integer getTotal(Sku sku) {
		// TODO Auto-generated method stub
		return goodsDao.getTotal(sku);
	}

}

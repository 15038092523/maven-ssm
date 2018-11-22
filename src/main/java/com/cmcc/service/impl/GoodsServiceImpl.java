package com.cmcc.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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

	public Sku getEntity(Integer id) {
		Sku sku = null;
		// �ӻ�����ȡ����
		try {
			String json = jedisClient.get("SkuId:" + id);
			System.out.println(json);
			if (!StringUtils.isBlank(json)) {
				// ���ַ���ת���ɶ���
				sku =JsonUtils.jsonToPojo(json, Sku.class);
				return sku;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sku = goodsDao.getEntity(id); 
		try {
			//����Ʒ��Ϣд�뻺��
			jedisClient.set("SkuId:"+id, JsonUtils.objectToJson(sku));
			//����key����Ч��
			jedisClient.expire("SkuId:"+id, 1000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sku;
	}

	public void updateSku(Sku sku) {
		try{
			goodsDao.updateSku(sku);
			jedisClient.del("SkuId:"+sku.getId());
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	public List<Sku> getAll(int startPage, int rows) {
		return goodsDao.getAll(startPage, rows);
	}

	public Integer deleteGoods(Integer id) {
		Integer result = goodsDao.deleteGoods(id);
		if(result > 0){
			try{
			jedisClient.del("SkuId:"+id);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

}

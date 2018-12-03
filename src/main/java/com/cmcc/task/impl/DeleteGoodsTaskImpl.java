package com.cmcc.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cmcc.service.GoodsService;
import com.cmcc.task.DeleteGoodsTask;
@Component
public class DeleteGoodsTaskImpl implements DeleteGoodsTask{
	
	@Autowired
	private GoodsService goodsService;
	
	@Scheduled(cron="0/10 * *  * * ? ") 
	public void delete() {
		//System.out.println("delete方法执行,删除"+goodsService.deleteGoods(12)+"条数据。");
	}
	
	@Scheduled(cron="0/10 * *  * * ? ") 
	public void select() {
		for (int i=1; i<12;i++ ) {
			//System.out.println("查询id为"+i+"的商品信息，商品名称是"+goodsService.getEntity(i).getName());
		}
	}

}

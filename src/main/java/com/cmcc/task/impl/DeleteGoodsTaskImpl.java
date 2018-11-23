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
		System.out.println("delete����ִ��,ɾ��"+goodsService.deleteGoods(12)+"�����ݡ�");
	}

}

package com.cmcc.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.common.PageUtil;
import com.cmcc.entity.Sku;
import com.cmcc.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/getEntity")
	public String getEntity(Integer id,HttpServletRequest request){
		Sku sku = goodsService.getEntity(id);
		HttpSession session=request.getSession();
		session.setAttribute("currentUser", sku);
		return "success";
		}
	
	@RequestMapping("/getEntityJson")
	@ResponseBody
	public Sku getEntityJson(Integer id,HttpServletRequest request){
		Sku sku = goodsService.getEntity(id);
		return sku;
		}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> updateGoods(Sku sku){
		Map<String,Object> map = new HashMap<String, Object>();
		goodsService.updateSku(sku);
		map.put("status", 200);
		return map;
	}
	
	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Map<String,Object> deleteGoods(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		int result = goodsService.deleteGoods(id);
		map.put("status", 200);
		if(result == 0){
			map.put("message", "数据不存在");
		}else{
		map.put("message", "已删除id为"+id+"的数据,共"+result+"条");
		}
		return map;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String,Object> getAllGoods(@RequestParam(value="page", defaultValue="1")int page,@RequestParam(value="rows", defaultValue="3")int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		List list = goodsService.getAll(PageUtil.getStartPage(page,rows),rows);
		map.put("status", 200);
		map.put("data", list);
		return map;
	}
}

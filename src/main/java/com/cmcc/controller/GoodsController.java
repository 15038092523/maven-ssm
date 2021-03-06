package com.cmcc.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
	public String getEntity(Integer id, HttpServletRequest request) {
		Sku sku = goodsService.getEntity(id);
		HttpSession session = request.getSession();
		session.setAttribute("currentUser", sku);
		return "success";
	}

	@RequestMapping("/getEntityJson")
	@ResponseBody
	public Sku getEntityJson(Integer id, HttpServletRequest request) {
		Sku sku = goodsService.getEntity(id);
		return sku;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updateGoods(Sku sku) {
		Map<String, Object> map = new HashMap<String, Object>();
		goodsService.updateSku(sku);
		map.put("status", 200);
		return map;
	}

	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Map<String, Object> deleteGoods(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = goodsService.deleteGoods(id);
		map.put("status", 200);
		if (result == 0) {
			map.put("message", "数据不存在");
		} else {
			map.put("message", "已删除id为" + id + "的数据,共" + result + "条");
		}
		return map;
	}

	@RequestMapping("/deleteGoodsList")
	@ResponseBody
	public Map<String, Object> deleteGoodsList(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (ids != null && !"".equals(ids)) {
			List<String> sList = Arrays.asList(ids.split(","));
			List<Integer> list = new ArrayList<Integer>();
			for (String str : sList) {
				int i = Integer.parseInt(str);
				list.add(i);
			}
			 goodsService.deleteGoodsList(list);
			
		}
		map.put("status", 200);
		return map;
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String, Object> getAllGoods(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "3") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		List list = goodsService.getAll(PageUtil.getStartPage(page, rows), rows);
		map.put("status", 200);
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/getAllByParam")
	@ResponseBody
	public Map<String, Object> getAllGoodsByParam(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Sku> list = goodsService.getAllByParam(fillQueryParam(request));
		Integer total =  goodsService.getTotal(fillQueryParam(request));
		map.put("status", 200);
		map.put("data", list);
		map.put("total", total);
		return map;
	}
	
	private Sku fillQueryParam(HttpServletRequest request){
		
		Sku sku = new Sku();
		String color = request.getParameter("color") == null? null:request.getParameter("color").trim();
		String name = request.getParameter("name") == null? null:request.getParameter("name").trim();
		String simpleName = request.getParameter("simpleName") == null? null:request.getParameter("simpleName").trim();
		String brand = request.getParameter("brand") == null? null:request.getParameter("brand").trim();
		Integer page = 1,rows =10;
		if(request.getParameter("page") != null &&request.getParameter("page") != ""){
			page = Integer.valueOf(request.getParameter("page").trim());
		}
		if(request.getParameter("rows") != null &&request.getParameter("rows") != ""){
			rows = Integer.valueOf(request.getParameter("rows").trim());
		}
		sku.setColor(color);
		sku.setName(name);
		sku.getItem().setBrand(brand);
		sku.getItem().setSimpleName(simpleName);
		sku.setRows(rows);
		sku.setStartPage(PageUtil.getStartPage(page, rows));
		return sku;
		
	}
	
}

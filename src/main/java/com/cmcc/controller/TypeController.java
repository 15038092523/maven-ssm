package com.cmcc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.entity.Type;
import com.cmcc.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController {
	@Autowired
	private TypeService typeService;

	@RequestMapping("/getList")
	@ResponseBody
	public Map<String,Object> getList(Integer id) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Type> list = typeService.getList(id);
		map.put("status", 200);
		map.put("list", list);
		return map;
	}
}

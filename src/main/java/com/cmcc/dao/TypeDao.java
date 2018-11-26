package com.cmcc.dao;
import java.util.List;

import com.cmcc.entity.Type;
public interface TypeDao {
	
	List<Type> getList(Integer id);
}

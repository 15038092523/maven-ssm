package com.cmcc.service;

import java.util.Set;
import com.cmcc.entity.User;


public interface UserService {
	/**
	 *  通过用户名查找用户
	 *  @param username
	 *  @return User
	 */
	 User getByUserName(String username);

	/**
	 *  通过用户名查找该用户所有的角色并保存在Set集合中
	 *  @param username
	 *  @return Set<String>
	 */
	 Set<String> getRoles(String username);

	/**
	 *  通过用户名查找该用户所有的权限并保存在Set集合中
	 *  @param username
	 *  @return Set<String>
	 */
	 Set<String> getPermissions(String username);
}
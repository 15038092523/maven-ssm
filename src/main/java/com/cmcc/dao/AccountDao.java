package com.cmcc.dao;

import com.cmcc.entity.Account;

public interface AccountDao {

	Account selectByOpenId(String openId);

	Integer updateAccountWallet(Account account);
	
	Account selectAccount(String openId);
	
	Integer updateAccount(Account account);
	
}

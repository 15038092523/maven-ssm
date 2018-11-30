package com.cmcc.service;

import com.cmcc.entity.Account;

public interface AccountService {

	Account selectByOpenId(String openId);

	Integer updateAccountWallet(Account account);
	
	Integer updateAccount(String openId,String openType,Double amount);
}

package com.cmcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.dao.AccountDao;
import com.cmcc.entity.Account;
import com.cmcc.service.AccountService;
@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	public Account selectByOpenId(String openId) {
		// TODO Auto-generated method stub
		return accountDao.selectByOpenId(openId);
	}

	public Integer updateAccountWallet(Account account) {
		// TODO Auto-generated method stub
		return accountDao.updateAccountWallet(account);
	}

}

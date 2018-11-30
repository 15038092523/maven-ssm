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

	public Integer updateAccount(String openId,String openType,Double amount) {
		// TODO Auto-generated method stub
		Account account = accountDao.selectAccount(openId);
		if (Integer.parseInt(openType) == 1) {
			account.setUserAmount(account.getUserAmount()+amount);
		} else if (Integer.parseInt(openType) == 2) {
			account.setUserAmount(account.getUserAmount()-amount);
		}
		Integer result = accountDao.updateAccount(account);
		return result;
	}

}

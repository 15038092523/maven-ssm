package com.cmcc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.entity.Account;
import com.cmcc.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);
    
	@Autowired
	private AccountService accountService;

	/**
	 * 针对业务系统高并发-----修改用户钱包数据余额，采用乐观锁
	 */
	@RequestMapping(value = "/walleroptimisticlock")
	@ResponseBody
	public String walleroptimisticlock(HttpServletRequest request) {

		String result = "";

		try {
			// 用户唯一编号
			String openId = request.getParameter("openId") == null ? null : request.getParameter("openId").trim();
			// 1:代表增加，2：代表减少
			String openType = request.getParameter("openType") == null ? null : request.getParameter("openType").trim();
			// 金额
			String amount = request.getParameter("amount") == null ? null : request.getParameter("amount").trim();

			if (StringUtils.isEmpty(openId)) {
				return "openId is null";
			}
			if (StringUtils.isEmpty(openType)) {
				return "openType is null";
			}
			if (StringUtils.isEmpty(amount)) {
				return "amount is null";
			}

			Account account = accountService.selectByOpenId(openId);
			// 用户操作金额
			Double cash =Double.parseDouble(amount);
			if (Integer.parseInt(openType) == 1) {
				account.setUserAmount(account.getUserAmount()+cash);
			} else if (Integer.parseInt(openType) == 2) {
				account.setUserAmount(account.getUserAmount()-cash);
			}
			int target = accountService.updateAccountWallet(account);
			logger.info("修改用户金额是否：" + (target == 1 ? "成功" : "失败"));

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
			return result;
		}

		return "success";
	}
}

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
	 * ���ҵ��ϵͳ�߲���-----�޸��û�Ǯ�������������ֹ���
	 */
	@RequestMapping(value = "/walleroptimisticlock")
	@ResponseBody
	public String walleroptimisticlock(HttpServletRequest request) {

		String result = "";

		try {
			// �û�Ψһ���
			String openId = request.getParameter("openId") == null ? null : request.getParameter("openId").trim();
			// 1:�������ӣ�2���������
			String openType = request.getParameter("openType") == null ? null : request.getParameter("openType").trim();
			// ���
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
			// �û��������
			Double cash =Double.parseDouble(amount);
			if (Integer.parseInt(openType) == 1) {
				account.setUserAmount(account.getUserAmount()+cash);
			} else if (Integer.parseInt(openType) == 2) {
				account.setUserAmount(account.getUserAmount()-cash);
			}
			int target = accountService.updateAccountWallet(account);
			logger.info("�޸��û�����Ƿ�" + (target == 1 ? "�ɹ�" : "ʧ��"));

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
			return result;
		}

		return "success";
	}
}

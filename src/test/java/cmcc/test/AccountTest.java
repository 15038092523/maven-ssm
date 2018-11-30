package cmcc.test;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmcc.entity.Account;
import com.cmcc.service.AccountService;

public class AccountTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountTest.class);
	@Test
	public void selectAndUpdate(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		AccountService accountService = (AccountService) ctx.getBean("accountService");
		Account account = accountService.selectByOpenId("1");
		logger.info(account.getPayPassword());
		account.setPayPassword("2");
		logger.info(accountService.updateAccountWallet(account)+"");
	}
	
	@Test
	public void update(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		AccountService accountService = (AccountService) ctx.getBean("accountService");
		Integer i = accountService.updateAccount("1","1",36.0);
		logger.info(i+"");
	}
}

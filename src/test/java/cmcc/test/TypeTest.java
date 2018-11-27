package cmcc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmcc.service.GoodsService;
import com.cmcc.service.TypeService;

public class TypeTest {

	@Test
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		TypeService typeService = (TypeService) ctx.getBean("typeService");
		System.out.println(typeService.getList(2).get(1).getName());
	}
}

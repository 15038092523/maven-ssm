package cmcc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmcc.entity.Type;
import com.cmcc.service.GoodsService;
import com.cmcc.service.TypeService;

public class TypeTest {

	@Test
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		TypeService typeService = (TypeService) ctx.getBean("typeService");
		System.out.println(typeService.getList(2).get(1).getName());
	}
	
	@Test
	public void deleteTypeById(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		TypeService typeService = (TypeService) ctx.getBean("typeService");
		System.out.println(typeService.deleteTypeById(1));
		System.out.println(typeService.deleteTypeByPid(1));
		Type t = new Type();
		t.setDeleteStatus(0);
		t.setId(10);
		t.setName("Ï´µÓ");
		t.setPid(0);
		t.setVersion(0);
		System.out.println(typeService.addType(t));
	}
}

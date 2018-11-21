package goods.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmcc.entity.Sku;
import com.cmcc.service.GoodsService;
import com.cmcc.service.impl.GoodsServiceImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class GoodsTest {

	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
		Sku sku = new Sku();
		sku.setId(1);
		sku.setColor(null);
		sku.setName(null);
		goodsService.updateSku(sku);
	}

	@Test
	public void deleteGoodsTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
		System.out.println(goodsService.deleteGoods(12));
	}

	@Test
	public void jedisTest() {
		Jedis jedis = new Jedis("192.168.199.152", 6379); // �����ͻ��� ����IP�Ͷ˿�
		jedis.auth("foobared");
		jedis.set("name", "java"); // ����ֵ
		String value = jedis.get("name"); // ��ȡֵ
		System.out.println(value);
		jedis.close(); // �ͷ�������Դ
	}

	@Test
	public void jedisPoolTest() {
		JedisPoolConfig config = new JedisPoolConfig(); // ���ӳص����ö���
		config.setMaxTotal(100); // �������������
		config.setMaxIdle(10); // ����������������

		JedisPool jedisPool = new JedisPool(config, "192.168.199.152", 6379);

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource(); // ��ȡ����
			jedis.auth("foobared"); // ��������
			jedis.set("aaaaa", "javaaaaa"); // ����ֵ
			String value = jedis.get("aaaaa"); // ��ȡֵ
			System.out.println(value);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}
	}

	@Test
	public void jedisPoolTest2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		JedisPool pool = (JedisPool) ctx.getBean("jedisPool");
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("foobared");
			jedis.set("name", "lisi");
			String name = jedis.get("name");
			System.out.println(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				// �ر�����
				jedis.close();
			}
		}
	}
}

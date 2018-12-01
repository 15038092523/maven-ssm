package cmcc.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.cmcc.common.JsonUtils;
import com.cmcc.entity.Sku;

public class JsonTest {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JsonTest.class);
	@Test
	public void testList() throws IOException{
		Sku sku = new Sku();
		sku.setId(2);
		sku.setColor("1");
		String s = JsonUtils.objectToJson(sku);
		logger.info(JsonUtils.objectToJson(sku));
		logger.info(JsonUtils.jsonToPojo(s, Sku.class)+"");
		
	}
}

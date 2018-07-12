package cn.itcast.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;

public class TestService {

	@Test
	public void TestService() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-Service.xml");
		ProductService productService = ac.getBean(ProductService.class);
		/*List<Product> findAll = productService.findAll();
		for (Product product : findAll) {
			System.out.println(product);
		}*/
	}
}

package cn.itcast.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.SysUserDao;
import cn.itcast.domain.SysUser;

public class TestDao {
	
	@Test
	public void TestDao() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-Dao.xml");
		/*ProductDao productDao = ac.getBean(ProductDao.class);
		List<Product> findAll = productDao.findAll();
		for (Product product : findAll) {
			System.out.println(product);
		}*/
		SysUserDao userDao = ac.getBean(SysUserDao.class);
		SysUser findSysUserByName = userDao.findSysUserByName("user");
		System.out.println(findSysUserByName);
	}
}

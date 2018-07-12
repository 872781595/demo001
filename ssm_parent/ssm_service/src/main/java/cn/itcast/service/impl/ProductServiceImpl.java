package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.utils.PageBean;
 
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Transactional(readOnly=true)
	public PageBean<Product> findAll(int pageNum, int pageSize) throws Exception {
		//现在有访问页数和每页条数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPageNum(pageNum);
		pageBean.setPageSize(pageSize);
		//添加总的商品数,需要查找
		int totalCount = productDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		//判断总的商品数是否能够整除每页条数,如果整除,得到整除的值,如果不整除,得到的余数+1
		int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		pageBean.setTotalPage(totalPage);
		//得到分页查找的商品信息
		//oracle分页查询语句
		//select roenum r ,product.* from product--得到分页的值
		//select * from(select roenum r ,product.* from product) t where t.r<11 ande t.r>5
		//因为oracle分页查询只能判断小与,在获得所有的分页参数后才可以判断大雨的值
		//select * from(select roenum r ,product.* from product where r<11 ) t where t.r>5
		//里面的5是开始的index,及startIndex
		//11是结束的index,及endIndex
		int startIndex = (pageNum-1) * pageSize;//
		int endIndex = pageNum*pageSize+1;
		List<Product> list = productDao.findProductByPage(startIndex,endIndex);
		pageBean.setList(list);
		return pageBean;
	}


	public void save(Product product) throws Exception {
		productDao.save(product);
	}

	@Override
	public void productAdd(Product product) throws Exception {
		productDao.save(product);
	}

	@Override
	public Product findProductById(int productId) throws Exception {
		return productDao.findProductById(productId);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int productId) throws Exception {
		productDao.deleteProduct(productId);
	}

}

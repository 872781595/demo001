package cn.itcast.service;

import java.util.List;  

import cn.itcast.domain.Product;
import cn.itcast.utils.PageBean;

public interface ProductService {

	public PageBean<Product> findAll(int pageNum, int pageSize) throws Exception;

	public void save(Product product) throws Exception;

	public void productAdd(Product product) throws Exception;

	public Product findProductById(int productId) throws Exception;

	public void updateProduct(Product product) throws Exception;

	public void deleteProduct(int productId) throws Exception;

}

package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.utils.PageBean;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	
	//查询所有的产品信息
	@RequestMapping("/findAllProduct")
	public String productService(Model model,
			@RequestParam(defaultValue="1")int pageNum,
			@RequestParam(defaultValue="5")int pageSize) throws Exception {
	 
//		model.addAttribute("productList", 	productService.findAll());
		PageBean<Product> pageBean = productService.findAll(pageNum,pageSize);
		model.addAttribute("pageBean", pageBean);
		System.out.println("pageBean"+pageBean);
	 return "product/productList";
	}
	
	@RequestMapping("/addPrductUI")
	public String addPrductUI() {
		return "product/productAdd";
	}
	
	@RequestMapping("/productAdd")
	public String productAdd(Product product) throws Exception {
		productService.productAdd(product);
		return "forward:/product/findAllProduct";
	}
	
	@RequestMapping("/updateProductUI")
	public String updateProductUI(int productId,Model model) throws Exception {
		Product product = productService.findProductById(productId);
		model.addAttribute("product", product);
		return "product/productEdit";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(Product product) throws Exception {
		productService.updateProduct(product);
		return "redirect:/product/findAllProduct";
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct(int productId) throws Exception {
		productService.deleteProduct(productId);
		return "redirect:/product/findAllProduct";
	}

}

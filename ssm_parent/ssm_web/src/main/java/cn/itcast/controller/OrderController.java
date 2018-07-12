package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Order;
import cn.itcast.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/findAllOrder")
	public String findAllOrder(Model model,
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="3")Integer pageSize) throws Exception {
		/*List<Order> orders = orderService.findAllOrde();
		model.addAttribute("orderList", orders);
		PageHelper.startPage("");*/
		//创建pageInfo对象,使用service调用dao层分页查找订单
		PageInfo<Order> pageInfo = orderService.findAllOrde(pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		System.out.println("pageInfo+list"+pageInfo.getList().toString());
		return "order/orderList";
	}
	
}

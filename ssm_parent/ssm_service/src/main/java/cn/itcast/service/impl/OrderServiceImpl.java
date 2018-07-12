package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Order;
import cn.itcast.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public PageInfo<Order> findAllOrde(Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum,pageSize);
		List<Order> orders = orderDao.findAllOrde();
		PageInfo<Order> pageInfo = new PageInfo<Order>(orders);
		return pageInfo;
	}

	
	
}

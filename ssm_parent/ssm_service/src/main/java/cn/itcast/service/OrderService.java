package cn.itcast.service;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Order;

public interface OrderService {

	PageInfo<Order> findAllOrde(Integer pageNum, Integer pageSize) throws Exception;

}

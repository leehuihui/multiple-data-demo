package com.ldd.multipledatademo.service.order.impl;

import com.ldd.multipledatademo.dao.order.entity.OrderInfo;
import com.ldd.multipledatademo.dao.order.mapper.OrderMapper;
import com.ldd.multipledatademo.service.order.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lidada
 * @since 2022-11-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements IOrderService {

}

package com.ldd.multipledatademo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldd.multipledatademo.dao.order.entity.OrderInfo;
import com.ldd.multipledatademo.service.order.IOrderService;
import com.ldd.multipledatademo.vo.PageQuery;
import com.ldd.multipledatademo.vo.req.OrderPageReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lidada
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/sys/order")
public class OrderController {


    @Autowired
    private IOrderService orderService;

    @PostMapping(value = "queryPage")
    public Page<OrderInfo> queryPage(@RequestBody PageQuery<OrderPageReqVO> reqVO) {
        LambdaQueryWrapper<OrderInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StrUtil.isNotEmpty(reqVO.getCondition().getOrderNo()), OrderInfo::getOrderNo,reqVO.getCondition());
        return orderService.page(new Page<>(reqVO.getPageNum(),reqVO.getPageSize()), lambdaQueryWrapper);
    }


}

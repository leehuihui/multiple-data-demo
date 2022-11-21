package com.ldd.multipledatademo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldd.multipledatademo.dao.user.entity.User;
import com.ldd.multipledatademo.service.user.IUserService;
import com.ldd.multipledatademo.vo.PageQuery;
import com.ldd.multipledatademo.vo.req.UserPageReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/sys/user")
public class UserController {


    @Autowired
    private IUserService userService;


    @PostMapping(value = "queryPage")
    public Page<User> queryPage(@RequestBody PageQuery<UserPageReqVO> reqVO) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StrUtil.isNotEmpty(reqVO.getCondition().getName()), User::getName, reqVO.getCondition());
        return userService.page(new Page<>(reqVO.getPageNum(), reqVO.getPageSize()), lambdaQueryWrapper);
    }

}

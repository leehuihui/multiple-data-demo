package com.ldd.multipledatademo.service.user.impl;

import com.ldd.multipledatademo.dao.user.entity.User;
import com.ldd.multipledatademo.dao.user.mapper.UserMapper;
import com.ldd.multipledatademo.service.user.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

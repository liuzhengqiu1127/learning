package com.lzq.spring.cloud.user.service.impl;

import com.lzq.spring.cloud.user.entity.UserInfo;
import com.lzq.spring.cloud.user.dao.UserInfoDao;
import com.lzq.spring.cloud.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lzq
 * @since 2019-12-30
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements IUserInfoService {

}

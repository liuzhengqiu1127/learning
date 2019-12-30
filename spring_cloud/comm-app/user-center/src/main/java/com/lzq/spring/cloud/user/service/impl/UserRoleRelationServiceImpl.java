package com.lzq.spring.cloud.user.service.impl;

import com.lzq.spring.cloud.user.entity.UserRoleRelation;
import com.lzq.spring.cloud.user.dao.UserRoleRelationDao;
import com.lzq.spring.cloud.user.service.IUserRoleRelationService;
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
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationDao, UserRoleRelation> implements IUserRoleRelationService {

}

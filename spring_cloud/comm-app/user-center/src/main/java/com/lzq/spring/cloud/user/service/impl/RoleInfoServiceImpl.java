package com.lzq.spring.cloud.user.service.impl;

import com.lzq.spring.cloud.user.entity.RoleInfo;
import com.lzq.spring.cloud.user.dao.RoleInfoDao;
import com.lzq.spring.cloud.user.service.IRoleInfoService;
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
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoDao, RoleInfo> implements IRoleInfoService {

}

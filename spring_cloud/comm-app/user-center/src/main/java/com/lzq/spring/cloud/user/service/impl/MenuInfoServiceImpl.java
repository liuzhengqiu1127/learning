package com.lzq.spring.cloud.user.service.impl;

import com.lzq.spring.cloud.user.entity.MenuInfo;
import com.lzq.spring.cloud.user.dao.MenuInfoDao;
import com.lzq.spring.cloud.user.service.IMenuInfoService;
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
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoDao, MenuInfo> implements IMenuInfoService {

}

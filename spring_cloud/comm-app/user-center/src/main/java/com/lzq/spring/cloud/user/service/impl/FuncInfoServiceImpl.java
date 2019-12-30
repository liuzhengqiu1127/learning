package com.lzq.spring.cloud.user.service.impl;

import com.lzq.spring.cloud.user.entity.FuncInfo;
import com.lzq.spring.cloud.user.dao.FuncInfoDao;
import com.lzq.spring.cloud.user.service.IFuncInfoService;
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
public class FuncInfoServiceImpl extends ServiceImpl<FuncInfoDao, FuncInfo> implements IFuncInfoService {

}

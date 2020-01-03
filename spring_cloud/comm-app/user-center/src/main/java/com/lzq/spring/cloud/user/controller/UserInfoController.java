package com.lzq.spring.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzq.spring.cloud.user.controller.request.UserExtInfo;
import com.lzq.spring.cloud.user.entity.RoleInfo;
import com.lzq.spring.cloud.user.entity.UserInfo;
import com.lzq.spring.cloud.user.entity.UserRoleRelation;
import com.lzq.spring.cloud.user.service.IRoleInfoService;
import com.lzq.spring.cloud.user.service.IUserInfoService;
import com.lzq.spring.cloud.user.service.IUserRoleRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lzq
 * @since 2019-12-30
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private IUserRoleRelationService userRoleRelationService;

    @PostMapping("/add")
    @Transactional
    public void addUserInfo(@RequestBody UserExtInfo userExtInfo){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userExtInfo,userInfo);
        userInfoService.save(userInfo);
        RoleInfo roleInfo = roleInfoService.getOne(new QueryWrapper<>(new RoleInfo().setRoleName(userExtInfo.getRoleName())));
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<>(userInfo));
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setRoleId(roleInfo.getRoleId());
        userRoleRelation.setUserId(dbUserInfo.getUserId());
        userRoleRelationService.save(userRoleRelation);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "userId") Long userId){
        userInfoService.removeById(userId);
        userRoleRelationService.remove(new QueryWrapper<>(new UserRoleRelation().setUserId(userId)));
    }

    @PostMapping("/query")
    public List<UserInfo> query(@RequestBody UserInfo userInfo){
        return userInfoService.list(new QueryWrapper<>(userInfo));
    }

}


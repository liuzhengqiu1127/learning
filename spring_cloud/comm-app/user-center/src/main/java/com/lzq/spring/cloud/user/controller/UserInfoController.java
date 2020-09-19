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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        QueryWrapper<RoleInfo> roleInfoWrapper = new QueryWrapper<>();
        roleInfoWrapper.in("ROLE_NAME",userExtInfo.getRoleNames());
        List<RoleInfo> roleInfoList = roleInfoService.list(roleInfoWrapper);
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<>(userInfo));
        Long userId = dbUserInfo.getUserId();
        List<Long> roleIds = roleInfoList.stream().map(roleInfo -> roleInfo.getRoleId()).collect(Collectors.toList());
        List<UserRoleRelation> userRoleRelations = new ArrayList<>();
        for (Long roleId : roleIds){
            userRoleRelations.add(new UserRoleRelation(userId,roleId));
        }
        userRoleRelationService.saveBatch(userRoleRelations);
    }

    @PostMapping("/update")
    @Transactional
    public void update(@RequestBody UserExtInfo userExtInfo){
        delete(userExtInfo.getUserId());
        addUserInfo(userExtInfo);
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

    @GetMapping("/detail")
    public UserExtInfo detail(@RequestParam(value = "userId")Long userId){
        UserExtInfo userExtInfo = (UserExtInfo) userInfoService.getById(userId);
        List<UserRoleRelation> userRoleRelationList =
                userRoleRelationService.list(new QueryWrapper<>(new UserRoleRelation(userId)));
        List<Long> roleIds = userRoleRelationList.stream().map(userRoleRelation -> userRoleRelation.getRoleId()).collect(Collectors.toList());
        Collection<RoleInfo> roleInfoCollection = roleInfoService.listByIds(roleIds);
        List<String> roleNames = roleInfoCollection.stream().map(roleInfo -> roleInfo.getRoleName()).collect(Collectors.toList());
        userExtInfo.setRoleNames(roleNames);
        return userExtInfo;
    }

}


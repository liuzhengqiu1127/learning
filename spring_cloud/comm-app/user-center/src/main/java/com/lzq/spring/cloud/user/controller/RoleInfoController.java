package com.lzq.spring.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzq.spring.cloud.user.entity.RoleFuncRelation;
import com.lzq.spring.cloud.user.entity.RoleInfo;
import com.lzq.spring.cloud.user.entity.UserRoleRelation;
import com.lzq.spring.cloud.user.service.IRoleFuncRelationService;
import com.lzq.spring.cloud.user.service.IRoleInfoService;
import com.lzq.spring.cloud.user.service.IUserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/roleInfo")
@Slf4j
public class RoleInfoController {
    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private IUserRoleRelationService userRoleRelationService;
    @Autowired
    private IRoleFuncRelationService roleFuncRelationService;

    @PostMapping("/add")
    public boolean addRoleInfo(@RequestBody RoleInfo roleInfo){
        return roleInfoService.save(roleInfo);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody RoleInfo roleInfo){
        return roleInfoService.updateById(roleInfo);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam(value = "roleId") Long roleId){
        List<UserRoleRelation> userRoleRelations = userRoleRelationService
                .list(new QueryWrapper<>(new UserRoleRelation().setRoleId(roleId)));
        if (!CollectionUtils.isEmpty(userRoleRelations)){
            log.error("Current role can not delete, because some user bind it. you should first delete user.");
            return false;
        }
        List<RoleFuncRelation> roleFuncRelationList = roleFuncRelationService
                .list(new QueryWrapper<>(new RoleFuncRelation().setRoleId(roleId)));
        List<Long> ids = roleFuncRelationList.stream().map(roleFuncRelation -> roleFuncRelation.getId()).collect(Collectors.toList());
        roleFuncRelationService.removeByIds(ids);
        return roleInfoService.removeById(roleId);
    }

    @PostMapping("/query")
    public List<RoleInfo> query(@RequestBody RoleInfo roleInfo){
        return roleInfoService.list(new QueryWrapper<>(roleInfo));
    }

    @GetMapping("/detail")
    public RoleInfo detail(@RequestParam(value = "roleId") Long roleId){
        return roleInfoService.getById(roleId);
    }
}


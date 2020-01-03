package com.lzq.spring.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzq.spring.cloud.user.entity.RoleInfo;
import com.lzq.spring.cloud.user.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/roleInfo")
public class RoleInfoController {
    @Autowired
    private IRoleInfoService roleInfoService;

    @PostMapping("/add")
    public boolean addRoleInfo(@RequestBody RoleInfo roleInfo){
        return roleInfoService.save(roleInfo);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam(value = "roleId") Long roleId){
        return roleInfoService.removeById(roleId);
    }

    @PostMapping("/query")
    public List<RoleInfo> query(@RequestBody RoleInfo roleInfo){
        return roleInfoService.list(new QueryWrapper<>(roleInfo));
    }

}


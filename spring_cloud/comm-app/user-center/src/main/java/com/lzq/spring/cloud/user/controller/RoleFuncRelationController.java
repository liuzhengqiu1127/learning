package com.lzq.spring.cloud.user.controller;


import com.lzq.spring.cloud.user.controller.request.RoleFuncExtInfo;
import com.lzq.spring.cloud.user.entity.RoleFuncRelation;
import com.lzq.spring.cloud.user.service.IRoleFuncRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/roleFuncRelation")
public class RoleFuncRelationController {
    @Autowired
    private IRoleFuncRelationService roleFuncRelationService;

    @PostMapping("/add")
    public boolean add(@RequestBody RoleFuncExtInfo roleFuncExtInfo){
        List<RoleFuncRelation> roleFuncRelationList = new ArrayList<>();
        Long roleId = roleFuncExtInfo.getRoleId();
        for (Long funcId : roleFuncExtInfo.getFuncIds()){
            roleFuncRelationList.add(new RoleFuncRelation(roleId,funcId));
        }
        return roleFuncRelationService.saveBatch(roleFuncRelationList);
    }
}


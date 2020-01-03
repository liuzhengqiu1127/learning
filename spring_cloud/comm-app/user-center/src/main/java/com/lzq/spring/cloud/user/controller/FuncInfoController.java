package com.lzq.spring.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzq.spring.cloud.user.entity.FuncInfo;
import com.lzq.spring.cloud.user.entity.RoleFuncRelation;
import com.lzq.spring.cloud.user.service.IFuncInfoService;
import com.lzq.spring.cloud.user.service.IRoleFuncRelationService;
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
@RequestMapping("/funcInfo")
public class FuncInfoController {
    @Autowired
    private IFuncInfoService funcInfoService;
    @Autowired
    private IRoleFuncRelationService roleFuncRelationService;

    @PostMapping("/add")
    public boolean add(@RequestBody FuncInfo funcInfo)
    {
        return funcInfoService.save(funcInfo);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam(value = "funcId") Long funcId){
        List<RoleFuncRelation> roleFuncRelationList =
                roleFuncRelationService.list(new QueryWrapper<>(new RoleFuncRelation().setFuncId(funcId)));
        if (!CollectionUtils.isEmpty(roleFuncRelationList)){
            List<Long> ids = roleFuncRelationList.stream().map(roleFuncRelation -> roleFuncRelation.getId()).collect(Collectors.toList());
            roleFuncRelationService.removeByIds(ids);
        }
        return funcInfoService.removeById(funcId);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody FuncInfo funcInfo)
    {
        return funcInfoService.updateById(funcInfo);
    }

    @GetMapping("/queryByMenuId")
    public List<FuncInfo> queryByMenuId(@RequestParam(value = "menuId") Long menuId){
        return funcInfoService.list(new QueryWrapper<>(new FuncInfo().setMenuId(menuId)));
    }

    @GetMapping("/detail")
    public FuncInfo detail(@RequestParam(value = "funcId") Long funcId){
        return funcInfoService.getById(funcId);
    }
}


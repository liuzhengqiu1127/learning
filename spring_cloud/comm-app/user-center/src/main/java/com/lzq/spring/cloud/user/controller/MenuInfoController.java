package com.lzq.spring.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzq.spring.cloud.user.entity.FuncInfo;
import com.lzq.spring.cloud.user.entity.MenuInfo;
import com.lzq.spring.cloud.user.service.IFuncInfoService;
import com.lzq.spring.cloud.user.service.IMenuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
@Slf4j
@RequestMapping("/menuInfo")
public class MenuInfoController {
    @Autowired
    private IMenuInfoService menuInfoService;
    @Autowired
    private IFuncInfoService funcInfoService;

    @PostMapping("/add")
    public boolean addMenuInfo(@RequestBody MenuInfo menuInfo)
    {
        return menuInfoService.save(menuInfo);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody MenuInfo menuInfo)
    {
        return menuInfoService.updateById(menuInfo);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam(value = "menuId")String menuId){
        List<MenuInfo> childMenus = menuInfoService.list(new QueryWrapper<>(new MenuInfo().setParentId(Long.valueOf(menuId))));
        if (!CollectionUtils.isEmpty(childMenus)){
            log.error("Current menu has child menu, please delete its child menu first.");
            return false;
        }
        List<FuncInfo> funcInfoList = funcInfoService.list(new QueryWrapper<>(new FuncInfo().setMenuId(Long.valueOf(menuId))));
        if (!CollectionUtils.isEmpty(funcInfoList)){
            log.error("Current menu has some functions, please delete its functions first.");
            return false;
        }
        return menuInfoService.removeById(menuId);
    }

    @GetMapping("/queryAllFather")
    public List<MenuInfo> queryAllFather(){
        QueryWrapper<MenuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PARENT_ID",null);
        return menuInfoService.list(queryWrapper);
    }

    @GetMapping("/queryChildMenu")
    public List<MenuInfo> queryChildMenu(@RequestParam(value = "parentId")Long parentId)
    {
        return menuInfoService.list(new QueryWrapper<>(new MenuInfo().setParentId(parentId)));
    }

    @GetMapping("/detail")
    public MenuInfo detail(@RequestParam(value = "menuId") Long menuId){
        return menuInfoService.getById(menuId);
    }

}


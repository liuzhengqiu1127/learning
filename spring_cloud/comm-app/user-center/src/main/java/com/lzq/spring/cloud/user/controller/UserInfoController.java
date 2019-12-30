package com.lzq.spring.cloud.user.controller;


import com.lzq.spring.cloud.user.entity.UserInfo;
import com.lzq.spring.cloud.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public boolean addUserInfo(UserInfo userInfo){
        return userInfoService.save(userInfo);
    }

    
}


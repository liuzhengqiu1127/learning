package com.lzq.spring.cloud.user.controller.request;

import com.lzq.spring.cloud.user.entity.UserInfo;
import lombok.Data;

@Data
public class UserExtInfo extends UserInfo {
    private String roleName;
}

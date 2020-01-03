package com.lzq.spring.cloud.user.controller.request;

import com.lzq.spring.cloud.user.entity.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserExtInfo extends UserInfo {
    private List<String> roleNames;
}

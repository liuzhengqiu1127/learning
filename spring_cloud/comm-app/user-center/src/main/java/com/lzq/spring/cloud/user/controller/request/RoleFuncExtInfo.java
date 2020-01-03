package com.lzq.spring.cloud.user.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class RoleFuncExtInfo {
    private Long roleId;
    private List<Long> funcIds;
}

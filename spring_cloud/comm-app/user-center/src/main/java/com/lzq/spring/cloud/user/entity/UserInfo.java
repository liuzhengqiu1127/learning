package com.lzq.spring.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lzq
 * @since 2019-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("DEPARTMENT")
    private String department;

    @TableField("PASSWORD")
    private String password;

    @TableField("ACCOUNT_NAME")
    private String accountName;

    @TableField("PHONE")
    private String phone;

    @TableField("EMAIL")
    private String email;

    @TableField("REMARK")
    private String remark;


    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String PASSWORD = "PASSWORD";

    public static final String ACCOUNT_NAME = "ACCOUNT_NAME";

    public static final String PHONE = "PHONE";

    public static final String EMAIL = "EMAIL";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}

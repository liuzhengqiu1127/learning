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
public class RoleInfo extends Model<RoleInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @TableField("ROLE_NAME")
    private String roleName;

    @TableField("REMARK")
    private String remark;


    public static final String ROLE_ID = "ROLE_ID";

    public static final String ROLE_NAME = "ROLE_NAME";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}

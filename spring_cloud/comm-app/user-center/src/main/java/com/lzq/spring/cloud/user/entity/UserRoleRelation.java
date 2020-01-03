package com.lzq.spring.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Builder
@NoArgsConstructor
public class UserRoleRelation extends Model<UserRoleRelation> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("USER_ID")
    private Long userId;

    @TableField("ROLE_ID")
    private Long roleId;


    public static final String ID = "ID";

    public static final String USER_ID = "USER_ID";

    public static final String ROLE_ID = "ROLE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

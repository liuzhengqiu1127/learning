package com.lzq.spring.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
@NoArgsConstructor
public class UserRoleRelation extends Model<UserRoleRelation> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("USER_ID")
    private Long userId;

    @TableField("ROLE_ID")
    private Long roleId;

    public UserRoleRelation(Long userId, Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleRelation(Long userId){
        this.userId = userId;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

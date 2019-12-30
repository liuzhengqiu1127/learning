package com.lzq.spring.cloud.user.entity;

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
public class RoleFuncRelation extends Model<RoleFuncRelation> {

    private static final long serialVersionUID=1L;

    @TableId("ID")
    private Long id;

    @TableField("ROLE_ID")
    private Long roleId;

    @TableField("FUNC_ID")
    private Long funcId;


    public static final String ID = "ID";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String FUNC_ID = "FUNC_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

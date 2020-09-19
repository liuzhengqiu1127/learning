package com.lzq.spring.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class FuncInfo extends Model<FuncInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "FUNC_ID", type = IdType.AUTO)
    private Long funcId;

    @TableField("FUNC_NAME")
    private String funcName;

    @TableField("FUNC_PATH")
    private String funcPath;

    @TableField("ORDER_ID")
    private Integer orderId;

    @TableField("MENU_ID")
    private Long menuId;


    public static final String FUNC_ID = "FUNC_ID";

    public static final String FUNC_NAME = "FUNC_NAME";

    public static final String FUNC_PATH = "FUNC_PATH";

    public static final String ORDER_ID = "ORDER_ID";

    public static final String MENU_ID = "MENU_ID";

    @Override
    protected Serializable pkVal() {
        return this.funcId;
    }

}

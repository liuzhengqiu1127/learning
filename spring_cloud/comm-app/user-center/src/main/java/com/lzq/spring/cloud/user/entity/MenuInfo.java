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
public class MenuInfo extends Model<MenuInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_PATH")
    private String menuPath;

    @TableField("ORDER_ID")
    private Integer orderId;

    @TableField("PARENT_ID")
    private Long parentId;

    @TableField("REMARK")
    private String remark;


    public static final String MENU_ID = "MENU_ID";

    public static final String MENU_NAME = "MENU_NAME";

    public static final String MENU_PATH = "MENU_PATH";

    public static final String ORDER_ID = "ORDER_ID";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}

package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SellerCategory implements Serializable {



    /**
     * 商家分类ID
     */
    @TableId(value = "seller_category_id", type = IdType.AUTO)
    private Integer sellerCategoryId;

    /**
     * 商家分类名称
     */

    @TableField("seller_category_name")
    private String sellerCategoryName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;


}

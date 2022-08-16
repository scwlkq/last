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
public class SkuCategory implements Serializable {


    /**
     * 商品分类ID
     */
    @TableId(value = "sku_category_id", type = IdType.AUTO)
    private Integer skuCategoryId;

    /**
     * 商品分类名称
     */
    @TableField("sku_category_name")
    private String skuCategoryName;

    /**
     * 商家id
     */
    @TableField("seller_id")
    private Integer sellerId;

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

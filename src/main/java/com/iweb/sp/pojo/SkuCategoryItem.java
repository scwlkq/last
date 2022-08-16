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
public class SkuCategoryItem implements Serializable {


    /**
     * 商品分类详情ID
     */
    @TableId(value = "sku_category_item_id", type = IdType.AUTO)
    private Integer skuCategoryItemId;

    /**
     * 商品ID
     */
    @TableField("sku_id")
    private Integer skuId;

    /**
     * 商品分类ID
     */
    @TableField("sku_category_id")
    private Integer skuCategoryId;

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

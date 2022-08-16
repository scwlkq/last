package com.iweb.sp.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Zxy
 * @date 2022/8/16 15:38
 * @description
 */
@Data
public class SkuAndCategory {

    /**
     * 商品名称
     */
    @TableField("sku_name")
    private String skuName;


    /**
     * 商品状态
     */
    @TableField("sku_status")
    private String skuStatus;

    /**
     * 商品价格
     */
    @TableField("sku_price")
    private Double skuPrice;

    /**
     * 库存
     */
    @TableField("sku_stock")
    private Integer skuStock;

    /**
     * 商品详情
     */
    @TableField("sku_detail")
    private String skuDetail;

    /**
     * 商品分类名称
     */
    @TableField("sku_category_name")
    private String skuCategoryName;
}

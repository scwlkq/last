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
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
      @TableId(value = "sku_id", type = IdType.AUTO)
    private Integer skuId;

    /**
     * 商家ID
     */
    @TableField("seller_id")
    private Integer sellerId;

    /**
     * 商品名称
     */
    @TableField("sku_name")
    private String skuName;

    /**
     * 商品图片
     */
    @TableField("sku_image")
    private String skuImage;

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
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 总销售量
     */
    @TableField("sku_all")
    private Integer skuAll;


}

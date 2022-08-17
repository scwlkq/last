package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.checker.units.qual.C;

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
public class CartItem implements Serializable {


    /**
     * 购物车详情ID
     */
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Integer cartItemId;

    /**
     * 商品ID
     */
    @TableField("sku_id")
    private Integer skuId;

    /**
     * 商品数量
     */
    @TableField("sku_count")
    private Integer skuCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 购物车id
     */
    @TableField("cart_id")
    private Integer cartId;

}

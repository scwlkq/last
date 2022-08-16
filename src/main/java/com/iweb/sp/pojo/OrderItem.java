package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class OrderItem implements Serializable {


    /**
     * 订单详情ID
     */
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Integer orderItemId;

    /**
     * 商品ID
     */
    private Integer skuId;

    /**
     * 商品数量
     */
    private Integer skuCount;

    /**
     * 商品价格
     */
    private double skuPrice;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 订单id
     */
    private Integer orderId;




}

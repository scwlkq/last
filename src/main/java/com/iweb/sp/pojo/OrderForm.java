package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class OrderForm implements Serializable {


    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 商家ID
     */
    @TableField("seller_id")
    private Integer sellerId;


    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 地址ID
     */
    @TableField("address_id")
    private Integer addressId;

    /**
     * 订单编号
     */
    @TableField("order_code")
    private String orderCode;

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
     * 评分
     */
    @TableField("order_mark")
    private  double Ordermark;


}

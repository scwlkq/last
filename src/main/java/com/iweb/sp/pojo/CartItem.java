package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车详情ID
     */
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Integer cartItemId;

    /**
     * 商品ID
     */
    private Integer skuId;

    /**
     * 商品数量
     */
    private Integer skuCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


}

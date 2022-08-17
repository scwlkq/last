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
public class SellerCategoryItem implements Serializable {


    /**
     * 商家分类详情ID
     */
    @TableId(value = "seller_category_item_id", type = IdType.AUTO)
    private Integer sellerCategoryItemId;

    /**
     * 商家分类ID
     */
    private Integer sellerCategoryId;

    /**
     * 商家ID
     */
    private Integer sellerId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}

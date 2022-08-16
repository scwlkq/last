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
public class SkuCategoryItem implements Serializable {


    /**
     * 商品分类详情ID
     */
      @TableId(value = "sku_category_item_id", type = IdType.AUTO)
    private Integer skuCategoryItemId;

    /**
     * 商品ID
     */
    private Integer skuId;

    /**
     * 商品分类ID
     */
    private Integer skuCategoryId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}

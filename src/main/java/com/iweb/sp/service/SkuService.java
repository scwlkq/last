package com.iweb.sp.service;



import com.iweb.sp.pojo.Sku;

import java.util.List;

/**
 * @author Lenovo
 * @date 2022/8/14 0:22
 */
public interface SkuService {

    /**
     * 根据id查商品
     * @param id 商品id
     * @return 商品对象
     */
    Sku selectById(Integer id);


    /**
     * 根据商家id查询所有商品
     * @param sellerId 商家id
     * @return 商家商品集合
     */
    List<Sku> selectBySellerId(Integer sellerId);
}

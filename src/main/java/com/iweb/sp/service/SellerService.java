package com.iweb.sp.service;


import com.iweb.sp.pojo.*;

import java.util.List;

/**
 * @author Lenovo
 * @date 2022/8/13 23:52
 */
public interface SellerService {

    /**
     * 商家注册
     * @param sellerInfo 商家视图对象
     * @return 注册结果布尔值
     */
    boolean register(SellerInfo sellerInfo);

    /**
     * 商家登录
     * @param phone 商家登录电话
     * @param password 密码
     * @return 商家对象 登录失败为空
     */
    SellerInfo loginByPassword(String phone, String password);

    /**
     * 短信登录
     * @return 商家对象 登录失败为空
     */
    String loginByMessage(String phone);

    /**商家增加商品
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     * 用户添加商品后，
     */
    void insertSkuBySeller(Sku sku, SkuCategory skuCategory, SkuCategoryItem skuCategoryItem);


    /**商家增加分类
     * @param skuCategory 分类对象
     */


    void insertSkuCategoryBySeller(SkuCategory skuCategory);

    /**
     *商家从后台查看商品
     * @param serllerId 商家id
     * @return
     */
    List<Sku>selectSkuBySeller(Integer serllerId);

    /**商家删除指定商品
     * @param skuId 商品id
     */
    void deleteSkuBySeller(Integer skuId, Integer sellerId);


    /**商家更改商品信息
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     */
    void updateSkuBySeller(Sku sku, SkuCategory skuCategory);
}

package com.iweb.sp.service;


import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.pojo.Sku;
import com.iweb.sp.pojo.SkuCategory;
import com.iweb.sp.pojo.SkuCategoryItem;
import com.iweb.sp.pojo.vo.SkuAndCategory;
import org.springframework.web.multipart.MultipartFile;

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
    boolean register(SellerInfo sellerInfo, MultipartFile multipartFile);

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
     * @param skuCategory 商品分类
     */
    void insertSkuCategoryBySeller(SkuCategory skuCategory);

    /**
     *商家从后台根据名称查看商品(模糊查询)
     * @param skuName 商品名称
     * @return 商品集合
     */
    List<SkuAndCategory>selectSkuBySeller(String skuName);

    /**商家删除指定商品
     * @param skuId 商品id
     */
    void deleteSkuBySeller(Integer skuId,Integer sellerId);


    /**商家更改商品信息
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     * @param skucategoryId 修改之前的商品分类id
     */
    void updateSkuBySeller(Sku sku,SkuCategory skuCategory,String skucategoryId);


     /**商家点开全部商品，展示全部商品
     * @return 商品家分类的信息集合
     */
    List<SkuAndCategory> selectAllSku(Integer sellerId,Integer pageNum);


    /**按升序查商品
     * @param sellerId 商家id
     * @return 商品加分类的信息集合
     */
    List<SkuAndCategory> selectAllSkuASC(Integer sellerId,Integer pageNum);

     /**按降序查商品
     * @param sellerId 商家id
     * @return 商品加分类的信息集合
     */
    List<SkuAndCategory> selectAllSkuDESC(Integer sellerId,Integer pageNum);



    /**
     *
     * @param pageNum 页码
     * @param skuAndCategories 数据源
     * @return
     */
    List<SkuAndCategory> selectAllSkuPage(Integer pageNum,List<SkuAndCategory> skuAndCategories);




}

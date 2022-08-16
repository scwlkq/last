package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.*;
import com.iweb.sp.pojo.*;
import com.iweb.sp.service.SellerService;
import com.iweb.sp.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerInfoDao sellerInfoDao;

    @Resource
    private SkuDao skuDao;


    @Resource
    private SkuCategoryDao skuCategoryDao;

    @Resource
    private SkuCategoryItemDao skuCategoryItemDao;

    /**
     *
     * @param sellerInfo 商家视图对象
     * @return boolean 判断是否注册成功
     */
    @Override
    public boolean register(SellerInfo sellerInfo) {
        int count = sellerInfoDao.insert(sellerInfo);
        return (count==1)?true:false;
    }

    @Override
    public SellerInfo loginByPassword(String phone, String password) {
        LambdaQueryWrapper<SellerInfo> lwq = new LambdaQueryWrapper<>();
        lwq.eq(SellerInfo::getPhone,phone).eq(SellerInfo::getPassword,password);
        SellerInfo sellerInfo = sellerInfoDao.selectOne(lwq);
        return sellerInfo;
    }

    @Override
    public String loginByMessage(String phone) {
        //生成四位数验证码
        String utilcode = "0123456789";
        StringBuilder code  = new StringBuilder();
        //生成随机数
        for(int i=1;i<=4;i++){
            int num = new Random().nextInt(10);
            code.append(utilcode.charAt(num));
        }
        String usercode = code.toString();
        try {
            SendMessage.SendMessageByali(phone,usercode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usercode;
    }

    /**
     *
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     * @param skuCategoryItem
     */
    @Override
    public void insertSkuBySeller(Sku sku, SkuCategory skuCategory, SkuCategoryItem skuCategoryItem) {
        //添加商品
        skuDao.insert(sku);
        //查询商品分类表得到 商品分类id
        LambdaQueryWrapper<SkuCategory> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SkuCategory::getSkuCategoryName,skuCategory.getSkuCategoryName());
        SkuCategory skuCateGory = skuCategoryDao.selectOne(lqw);
        //添加到商品分类详情表  -- 封装对象
        skuCategoryItem.setSkuCategoryId(skuCategory.getSkuCategoryId());
        skuCategoryItemDao.insert(skuCategoryItem);
    }


    /**
     *
     * @param skuCategory 商品分类对象
     */
    @Override
    public void insertSkuCategoryBySeller(SkuCategory skuCategory) {
        skuCategoryDao.insert(skuCategory);
    }

    /**
     * 商家查看商品
     * @param sellerId
     * @return
     */
    @Override
    public List<Sku> selectSkuBySeller(Integer sellerId) {
        LambdaQueryWrapper<Sku> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Sku::getSellerId,sellerId);
        List<Sku> skus = skuDao.selectList(lqw);
        return skus;
    }

    /**
     * 商家删除指定商品
     * @param skuId 商品id
     * @param sellerId 商家id
     */
    @Override
    public void deleteSkuBySeller(Integer skuId,Integer sellerId) {
        LambdaQueryWrapper<Sku> lwq = new LambdaQueryWrapper<>();
        lwq.eq(Sku::getSellerId,sellerId).eq(Sku::getSkuId,skuId);
        skuDao.delete(lwq);
    }

    @Override
    public void updateSkuBySeller(Sku sku, SkuCategory skuCategory) {

    }
}

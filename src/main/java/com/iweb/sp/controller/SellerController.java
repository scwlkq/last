package com.iweb.sp.controller;

import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.pojo.Sku;
import com.iweb.sp.pojo.SkuCategory;
import com.iweb.sp.service.SellerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SUN
 * @date 2022/8/16 9:55
 * @description 类的描述和介绍
 */
@CrossOrigin
@RestController
public class SellerController {
    @Resource
    SellerService sellerService;

    //商家注册
//    @PostMapping("")
//    public Boolean register(SellerInfo sellerInfo){
//        Boolean isRegister=sellerService.register(sellerInfo);
//        return isRegister;
//    }

    //商家密码登录
    //传入参数 商家登录电话 密码
    @GetMapping("/test/staff_confrim")
    public SellerInfo loginByPassword(String phone,String password){
        SellerInfo sellerInfo=sellerService.loginByPassword(phone,password);
        return sellerInfo;
    }

    //短信登录
    //传入手机号
//    @GetMapping("")
//    public String loginByMessage(String phone){
//        String code=sellerService.loginByMessage(phone);
//        return code;
//    }

    //商家添加商品
    //传入参数


    //商家添加分类
    //传入参数 分类对象
//    @PostMapping("")
//    public void insertSkuCategoryBySeller(SkuCategory skuCategory){
//        sellerService.insertSkuCategoryBySeller(skuCategory);
//    }

    //商家从后台查看商品
    //传入参数 商家id
//    @GetMapping("")
//    public List<Sku> selectSkuBySeller(int sellerId){
//        List<Sku> skus=sellerService.selectSkuBySeller(sellerId);
//        return skus;
//    }

    //商家删除指定数据
    //传入参数 商品id 商家id
//    @DeleteMapping("")
//    public void deleteSkuBySeller(int skuId,int sellerId){
//        sellerService.deleteSkuBySeller(skuId,sellerId);
//    }

    //商家更改商品信息(未实现)
    //传入参数 商品对象 分类对象
//    @PutMapping("")
//    public void updateSkuBySeller(Sku sku,SkuCategory skuCategory){
//        sellerService.updateSkuBySeller(sku,skuCategory);
//    }
}

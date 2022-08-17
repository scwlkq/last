package com.iweb.sp.controller;

import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.pojo.vo.SellerRegisterVO;
import com.iweb.sp.service.SellerService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author SUN
 * @date 2022/8/16 9:55
 * @description 类的描述和介绍
 */

@Api(tags = "商家接口")
@RestController
public class SellerController {
    @Resource
    SellerService sellerService;


    //商家注册
    @RequestMapping("/test/register")
    @ResponseBody
    public Boolean register(SellerRegisterVO sellerRegisterVO, @RequestParam("file") MultipartFile file) throws IOException {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setAvatarImage("null");
        sellerInfo.setNickName(sellerRegisterVO.getStorename());
        sellerInfo.setPhone(sellerRegisterVO.getNumber());
        sellerInfo.setPassword(sellerRegisterVO.getPassword());
        sellerInfo.setProvince(sellerRegisterVO.getProvince());
        sellerInfo.setCity(sellerRegisterVO.getCity());
        sellerInfo.setCounty(sellerRegisterVO.getArea());
        sellerInfo.setLastDetail(sellerRegisterVO.getDetailaddress());
        sellerInfo.setLinkMan(sellerRegisterVO.getConnectpeople());
        sellerInfo.setGender(sellerRegisterVO.getSex());
        sellerInfo.setSellerIntroduction("测试");
        sellerInfo.setSellerStatus("未审核");
        sellerInfo.setAdminId(-1);
        sellerInfo.setCreateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
        sellerInfo.setUpdateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
        sellerInfo.setSellerAllprice(0.0);
        sellerInfo.setSellerAll(0);
        return sellerService.register(sellerInfo,file,sellerRegisterVO.getCategory(),"123");
    }


    //商家注册
//    @PostMapping("")
//    public Boolean register(SellerInfo sellerInfo){
//        Boolean isRegister=sellerService.register(sellerInfo);
//        return isRegister;
//    }

    //商家密码登录
    //传入参数 商家登录电话 密码
//    @GetMapping("/test/staff_confrim1")
//    public SellerInfo loginByPassword(String phone,String password){
//        SellerInfo sellerInfo=sellerService.loginByPassword(phone,password);
//        return sellerInfo;
//    }

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

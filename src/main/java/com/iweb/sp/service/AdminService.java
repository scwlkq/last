package com.iweb.sp.service;

import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.pojo.SellerInfo;

import java.util.List;

/**
 * @author Lenovo
 * @date 2022/8/13 23:48
 */



public interface AdminService {
    /**
     * 管理员登录
     * @param jobNumber 工号
     * @param password 密码
     * @return 登录结果布尔值
     */
    AdminInfo login(String jobNumber, String password);



    /**
     * 管理员注册
     * @param adminInfo 管理员对象
     * @return 注册结果布尔值
     */
    boolean register(AdminInfo adminInfo);

    /**下架商家
     * @param sellerInfo 商家id
     */
    void updateSellerStatusclose(SellerInfo sellerInfo);

    /**管理员查看申请的商家
     * @return 申请商家的集合
     */
    List<SellerInfo> selectSellerApplyByAdmin();

     /**更改商家状态为正常
     * @param sellerInfo 需要通过申请的商家对象
     */
    void updateSellerStatusTrue(SellerInfo sellerInfo);

    /**更改商家状态为为审核
     * @param sellerInfo 需要通过申请的商家对象
     */
    void updateSellerStatusFalse(SellerInfo sellerInfo);



}

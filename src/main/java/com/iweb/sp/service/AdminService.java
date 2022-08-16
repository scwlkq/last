package com.iweb.sp.service;

import com.iweb.sp.pojo.AdminInfo;
import org.springframework.stereotype.Service;

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
     * 登出
     */
    void logout();

    /**
     * 管理员注册
     * @param adminInfo 管理员对象
     * @return 注册结果布尔值
     */
    boolean register(AdminInfo adminInfo);

    /**下架商家
     * @param sellerId 商家id
     */
    void shopClose(Integer sellerId);


}

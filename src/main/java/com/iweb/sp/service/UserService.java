package com.iweb.sp.service;


import com.iweb.sp.pojo.Cart;
import com.iweb.sp.pojo.UserInfo;

/**
 * @author Lenovo
 * @date 2022/8/13 23:16
 */
public interface UserService {



    /**
     * 用户注册
     * @param userInfo 用户对象
     * @return 注册结果布尔值
     */
    boolean register(UserInfo userInfo);

    /**
     * 用户账号密码登录
     * @param phone 用户登录手机号
     * @param password 用户输入的明文密码
     * @return 登录成功返回用户 失败返回null
     */
    UserInfo loginByPassword(String phone, String password);


    public UserInfo loginByCode(String phone,String code);

    /**
     * 短信验证码登录
     * @return 最后实现
     */
    String loginByMessage(String phone);




    /**
     * 用户退出该商家时更新购物车内容
     * @param cart 购物车对象
     */
    void UpdateCart(Cart cart);



}



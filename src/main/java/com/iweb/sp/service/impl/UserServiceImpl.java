package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.CartDao;
import com.iweb.sp.dao.UserInfoDao;
import com.iweb.sp.pojo.Cart;
import com.iweb.sp.pojo.UserInfo;
import com.iweb.sp.service.UserService;
import com.iweb.sp.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class UserServiceImpl implements UserService {

    //验证码
    private String usercode;

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private CartDao cartDao;


    @Override
    public boolean register(UserInfo userInfo) {
        LambdaQueryWrapper<UserInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserInfo::getPhone,userInfo.getPhone());
        UserInfo user = userInfoDao.selectOne(lqw);
        if(user!=null){   //用户已存在
            return false;
        }
        int count = userInfoDao.insert(userInfo);
        return (count==1)?true:false;
    }

    @Override
    public UserInfo loginByPassword(String phone, String password) {
        LambdaQueryWrapper<UserInfo> lwq = new LambdaQueryWrapper<>();
        lwq.eq(UserInfo::getPhone,phone).eq(UserInfo::getPassword,password);
        UserInfo userInfo = userInfoDao.selectOne(lwq);
        return userInfo;
    }


    public UserInfo loginByCode(String phone,String code){
        LambdaQueryWrapper<UserInfo> lwq = new LambdaQueryWrapper<>();
        lwq.eq(UserInfo::getPhone,phone);
        UserInfo user = userInfoDao.selectOne(lwq);
        if(user!=null && code == usercode){
            return user;
        }else
            return null;
    }


    /**
     * 给用户发送验证码
     * @param phone
     * @return 返回验证码
     */
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
        String newcode = code.toString();
        try {
            SendMessage.SendMessageByali(phone,usercode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        usercode = newcode;
        return newcode;
    }


    @Override
    public void UpdateCart(Cart cart) {
        cartDao.update(cart,null);
    }
}

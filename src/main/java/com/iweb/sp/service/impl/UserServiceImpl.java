package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.CartDao;

import com.iweb.sp.dao.SkuDao;
import com.iweb.sp.dao.UserInfoDao;
import com.iweb.sp.pojo.Cart;
import com.iweb.sp.pojo.Sku;

import com.iweb.sp.pojo.UserInfo;
import com.iweb.sp.service.UserService;
import com.iweb.sp.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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

    @Resource
    private SkuDao skuDao;


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


    /**
     * 更新购物车
     * @param cart 购物车对象
     */
    @Override
    public void UpdateCart(Cart cart) {
        cartDao.update(cart,null);
    }


    /**
     * 反向模糊查询
     * @param sellerId 商家id
     * @param name
     * @param allCount
     * @return
     */

    @Override
    public ConcurrentHashMap searchByLike(int sellerId, String name, int allCount) {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        char[] allSubstring = getAllSubstring(name);
        for (char s : allSubstring) {
            char key = s;
            LambdaQueryWrapper<Sku> lwq = new LambdaQueryWrapper<>();
            lwq.eq(Sku::getSellerId,sellerId).like(Sku::getSkuName,key);
            List<Sku> skus = skuDao.selectList(lwq);   //模糊匹配到的结果
            for (Sku sku : skus) {
                //首先判断allCount有没有满
                if(hashMap.size()<=allCount){
                    //将数据添加到hashMap集合中
                    hashMap.put(sku.getSkuId(),sku);
                }else{
                    return hashMap;
                }
            }
        }
        return hashMap;
    }

    @Override
    public char[] getAllSubstring(String str) {
        List<String> stringskey = new ArrayList<>();
        char[] stringskeyChar = str.toCharArray();
        return stringskeyChar;
    }


}

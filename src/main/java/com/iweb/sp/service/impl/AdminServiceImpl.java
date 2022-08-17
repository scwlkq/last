package com.iweb.sp.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.AdminInfoDao;
import com.iweb.sp.dao.CartDao;
import com.iweb.sp.dao.SellerInfoDao;
import com.iweb.sp.dao.UserInfoDao;
import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.pojo.Cart;
import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.pojo.UserInfo;
import com.iweb.sp.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminInfoDao adminInfoDao;
    @Resource
    private SellerInfoDao sellerInfoDao;
    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private CartDao cartDao;


    @Override
    public AdminInfo login(String jobNumber, String password) {
        //密码加密
        password = DigestUtil.md5Hex(password);
        LambdaQueryWrapper<AdminInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AdminInfo::getJobNumber,jobNumber).eq(AdminInfo::getPassword,password);
        AdminInfo adminInfo = adminInfoDao.selectOne(lqw);
        return adminInfo;
    }


    /**
     *
     * @param adminInfo 管理员对象
     * @return 受影响得行数
     */
    @Override
    public boolean register(AdminInfo adminInfo) {
        //先查数据库
        LambdaQueryWrapper<AdminInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AdminInfo::getJobNumber,adminInfo.getJobNumber());
        AdminInfo admin = adminInfoDao.selectOne(lqw);
        if (admin!=null){   //用户已存在
            return false;
        }
        //MD5加密
        String password = DigestUtil.md5Hex(adminInfo.getPassword());
        adminInfo.setPassword(password);
        int count = adminInfoDao.insert(adminInfo);

        return (count==1)? true:false;
    }


    /**
     * 下架商家，更改商家的状态码
     * @param sellerInfo 商家id
     */
    @Override
    public void updateSellerStatusclose(SellerInfo sellerInfo) {
        sellerInfo.setSellerStatus("下架");
        //更新数据
        sellerInfoDao.update(sellerInfo,null);
    }

    /**
     * 查看未审核的商家
     * @return
     */
    @Override
    public List<SellerInfo> selectSellerApplyByAdmin() {
        LambdaQueryWrapper<SellerInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SellerInfo::getSellerStatus,"未审核");
        List<SellerInfo> sellerInfos = sellerInfoDao.selectList(lqw);
        return sellerInfos;
    }

    /**
     * 更改商家状态为正常
     * @param sellerId  商家id
     * @param adminId
     */
    @Override
    public void updateSellerStatusTrue(Integer sellerId,Integer adminId) {
        LambdaQueryWrapper<SellerInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SellerInfo::getSellerId,sellerId);
        SellerInfo sellerInfo = sellerInfoDao.selectOne(lqw);
        sellerInfo.setSellerStatus("正常");
        sellerInfo.setAdminId(adminId);
        sellerInfoDao.update(sellerInfo,null);
        //给每个用户添加该商家的购物车
        //1.查找表中所有用户
        List<UserInfo> users = userInfoDao.selectList(null);
        for (UserInfo user : users) {
            //2.添加用户购物车
            Cart cart = new Cart();
            cart.setSellerId(sellerId);
            cart.setUserId(user.getUserId());
            cart.setCreateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
            cart.setUpdateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
            cartDao.insert(cart);
        }
    }


    /**
     * 更改商家状态为未审核
     * @param sellerId
     * @param adminId
     */
    @Override
    public void updateSellerStatusFalse(Integer sellerId,Integer adminId) {
        LambdaQueryWrapper<SellerInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SellerInfo::getSellerId,sellerId);
        SellerInfo sellerInfo = sellerInfoDao.selectOne(lqw);
        sellerInfo.setSellerStatus("未审核");
        sellerInfo.setAdminId(adminId);
        sellerInfoDao.update(sellerInfo,null);
    }

    /**
     *
     */


}

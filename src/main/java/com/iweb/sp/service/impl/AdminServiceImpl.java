package com.iweb.sp.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iweb.sp.dao.AdminInfoDao;
import com.iweb.sp.dao.SellerInfoDao;
import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public AdminInfo login(String jobNumber, String password) {
        //密码加密
        password = DigestUtil.md5Hex(password);
        LambdaQueryWrapper<AdminInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AdminInfo::getJobNumber,jobNumber).eq(AdminInfo::getPassword,password);
        AdminInfo adminInfo = adminInfoDao.selectOne(lqw);
        return adminInfo;
    }

    @Override
    public void logout() {

    }

    /**
     *
     * @param adminInfo 管理员对象
     * @return 受影响得行数
     */
    @Override
    public boolean register(AdminInfo adminInfo) {
        //MD5加密
        String password = DigestUtil.md5Hex(adminInfo.getPassword());
        adminInfo.setPassword(password);
        int count = adminInfoDao.insert(adminInfo);
        return (count==1)? true:false;
    }


    /**
     * 下架商家，更改商家的状态码
     * @param sellerId 商家id
     */
    @Override
    public void shopClose(Integer sellerId) {
        //根据id查询商家数据
        SellerInfo sellerInfo = sellerInfoDao.selectById(sellerId);
        sellerInfo.setSellerStatus("打烊");
        //更新数据
        sellerInfoDao.updateById(sellerInfo);
    }
}

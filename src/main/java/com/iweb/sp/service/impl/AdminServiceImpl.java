package com.iweb.sp.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.AdminInfoDao;
import com.iweb.sp.dao.SellerInfoDao;
import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.service.AdminService;
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
     * @param sellerInfo 需要通过申请的商家对象
     */
    @Override
    public void updateSellerStatusTrue(SellerInfo sellerInfo) {
        sellerInfo.setSellerStatus("正常");
        sellerInfoDao.update(sellerInfo,null);
    }


    /**
     * 更改商家状态为未审核
     * @param sellerInfo 需要通过申请的商家对象
     */
    @Override
    public void updateSellerStatusFalse(SellerInfo sellerInfo) {
        sellerInfo.setSellerStatus("未审核");
        sellerInfoDao.update(sellerInfo,null);
    }


}

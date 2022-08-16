package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.SkuDao;
import com.iweb.sp.pojo.Sku;
import com.iweb.sp.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuDao skuDao;

    @Override
    public Sku selectById(Integer id) {
        LambdaQueryWrapper<Sku> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Sku::getSkuId,id);
        Sku sku = skuDao.selectOne(lqw);
        return sku;
    }

    @Override
    public List<Sku> selectBySellerId(Integer sellerId) {
        LambdaQueryWrapper<Sku> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Sku::getSellerId,sellerId);
        List<Sku> skus = skuDao.selectList(lqw);
        return skus;
    }
}

package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.CartDao;
import com.iweb.sp.pojo.Cart;
import com.iweb.sp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;


    /**根据商家id查询购物车所有商品
     * @param sellerId 商家id
     * @return 购物车集合
     */
    @Override
    public List<Cart> selectBySellerId(Integer sellerId) {
        LambdaQueryWrapper<Cart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Cart::getSellerId,sellerId);
        List<Cart> carts = cartDao.selectList(lqw);
        return carts;
    }
}

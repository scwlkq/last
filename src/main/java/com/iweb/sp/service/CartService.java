package com.iweb.sp.service;


import com.iweb.sp.pojo.Cart;

import java.util.List;

/**
 * @author Zxy
 * @date 2022/8/14 10:46
 */
public interface CartService {

    /**根据商家id查询购物车所有商品
     * @param sellerId 商家id
     * @return 购物车集合
     */
      List<Cart> selectBySellerId(Integer sellerId);



}

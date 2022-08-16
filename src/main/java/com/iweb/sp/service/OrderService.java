package com.iweb.sp.service;

import com.iweb.sp.pojo.OrderForm;
import com.iweb.sp.pojo.OrderItem;

import java.util.List;

/**
 * @author Zxy
 * @date 2022/8/14 11:36
 * @description
 */
public interface OrderService {

    /**
     *
     * @param sellerId 商家id
     * @return 订单集合
     */
    List<OrderForm> selectBySeller(Integer sellerId);


    /**
     * @param UserId 用户id
     * @return 订单集合
     */
    List<OrderForm> selectByUser(Integer UserId);



    /**
     * 用户从前台看订单详情
     * @param userId  用户id
     * @param orderId 订单id
     * @return
     */
    List<OrderItem> selectDetailByUser(Integer userId, Integer orderId);

    /**
     * 商家从后台看订单详情
     * @param sellerId  商家id
     * @param orderId   订单id
     * @return
     */
    List<OrderItem> selectDetailBySeller(Integer sellerId, Integer orderId);

}

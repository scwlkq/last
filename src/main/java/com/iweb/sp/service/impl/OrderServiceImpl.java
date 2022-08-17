package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.OrderFormDao;
import com.iweb.sp.dao.OrderItemDao;
import com.iweb.sp.pojo.OrderForm;
import com.iweb.sp.pojo.OrderItem;
import com.iweb.sp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderFormDao orderFormDao;

    @Resource
    private OrderItemDao orderItemDao;


    @Override
    public List<OrderForm> selectBySeller(Integer sellerId) {
        LambdaQueryWrapper<OrderForm> lwq = new LambdaQueryWrapper<>();
        lwq.eq(OrderForm::getSellerId,sellerId);
         List<OrderForm> orderForms = orderFormDao.selectList(lwq);
        return orderForms;

    }

    @Override
    public List<OrderForm> selectByUser(Integer UserId) {
        LambdaQueryWrapper<OrderForm> lwq = new LambdaQueryWrapper<>();
        lwq.eq(OrderForm::getSellerId,UserId);
        List<OrderForm> orderForms = orderFormDao.selectList(lwq);
        return orderForms;
    }


    /**
     * 用户从前台获取订单详情
     * @param userId  用户id
     * @param orderId 订单id
     * @return
     */

    @Override
    public List<OrderItem> selectDetailByUser(Integer userId, Integer orderId) {
        //查询用户的订单id
        LambdaQueryWrapper<OrderForm> lwq = new LambdaQueryWrapper<>();
        lwq.eq(OrderForm::getUserId,userId);
        List<OrderForm> userForm = orderFormDao.selectList(lwq);   //该用户的订单（不止一个）
        List<Integer> orderIdList = new ArrayList<>();
        for (OrderForm user: userForm) {
            orderIdList.add(user.getOrderId());
        }
        //根据订单id 查询对应的商品集合
        LambdaQueryWrapper<OrderItem> lwq2 = new LambdaQueryWrapper<>();
        lwq2.in(OrderItem::getOrderId,orderIdList);
        List<OrderItem> orderItems = orderItemDao.selectList(lwq2);
        return orderItems;


    }


    /**
     * 商家从后台看到订单详情
     * @param sellerId  商家id
     * @param orderId   订单id
     * @return
     */
    @Override
    public List<OrderItem> selectDetailBySeller(Integer sellerId, Integer orderId) {
        //查询用户的订单id
        LambdaQueryWrapper<OrderForm> lwq = new LambdaQueryWrapper<>();
        lwq.eq(OrderForm::getSellerId,sellerId);
        List<OrderForm> sellerForm = orderFormDao.selectList(lwq);   //该用户的订单（不止一个）
        List<Integer> orderIdList = new ArrayList<>();
        for (OrderForm seller: sellerForm) {
            orderIdList.add(seller.getOrderId());
        }
        //根据订单id 查询对应的商品集合
        LambdaQueryWrapper<OrderItem> lwq2 = new LambdaQueryWrapper<>();
        lwq2.in(OrderItem::getOrderId,orderIdList);
        List<OrderItem> orderItems = orderItemDao.selectList(lwq2);
        return orderItems;
    }
}

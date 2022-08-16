package com.iweb.sp.dao;

import com.iweb.sp.pojo.CartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@Mapper
public interface CartItemDao extends BaseMapper<CartItem> {

}

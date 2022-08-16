package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
      @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 省
     */
    private String provice;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 门牌号
     */
    private String lastDetail;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 性别
     */
    private String gender;

    /**
     * 联系电话
     */
    private Long linkPhone;

    /**
     * 排序序列号
     */
    private Integer seqNumber;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


}

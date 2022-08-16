package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class SellerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家ID
     */
    @TableId(value = "seller_id", type = IdType.AUTO)
    private Integer sellerId;

    /**
     * 商家昵称
     */
   @TableField("nick_name")
    private String nickName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 商家图片
     */
    @TableField("avatar_image")
    private String avatarImage;

    /**
     * 密码
     */
    private String password;

    /**
     * 省
     */

    private String province;

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
    @TableField("last_detail")
    private String lastDetail;

    /**
     * 联系人
     */
    @TableField("link_man")
    private String linkMan;

    /**
     * 性别
     */
    private String gender;

    /**
     * 联系电话
     */
    @TableField("link_phone")
    private Long linkPhone;

    /**
     * 商家介绍
     */
    @TableField("seller_introduction")
    private String sellerIntroduction;

    /**
     * 经纬度
     */
    private String coordinate;

    /**
     * 商家评分
     */
    private Integer score;

    /**
     * 商家状态
     */
    @TableField("seller_status")
    private String sellerStatus;

    /**
     * 审核操作员ID
     */
    @TableField("admin_id")
    private Integer adminId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 商家总销售量
     */
    @TableField("seller_all")
    private Integer sellerAll;

    /**
     * 总销售额（/天）
     */
    @TableField("seller_allprice")
    private double sellerAllprice;


}

package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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

public class UserInfo implements Serializable {


    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 头像图片
     */
    @TableField("avatar_image")
    private String avatarImage;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态
     */
    @TableField("user_status")
    private String userStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 用户坐标
     */
    @TableField("user_coordinate")
    private String userCoordinate;


}

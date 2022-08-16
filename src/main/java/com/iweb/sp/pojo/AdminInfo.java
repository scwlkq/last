package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@Data
@TableName("admin_info")
public class AdminInfo {

    /**
     * 管理员ID
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 职称
     */
    @TableField("job_title")
    private String jobTitle;

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


}

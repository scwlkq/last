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
public class UserComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
      @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 评论状态
     */
    private String commentStatus;

    /**
     * 评论内容
     */
    private String commentDetail;

    /**
     * 评论审核
     */
    private String commentAudit;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}

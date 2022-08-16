package com.iweb.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class SellerReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家回复id
     */
      @TableId(value = "reply_id", type = IdType.AUTO)
    private Integer replyId;

    /**
     * 商家id
     */
    private Integer sellerId;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复内容
     */
    private String replyDetail;

    /**
     * 回复时间
     */
    private String replyTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}

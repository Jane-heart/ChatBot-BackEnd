package online.tuanzi.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_friend_message
 */
@TableName(value ="t_friend_message")
@Data
public class FriendMessage implements Serializable {
    /**
     * 消息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 内容类型，文本0-图片1-视频2
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 发送者id
     */
    @TableField(value = "sender_id")
    private Integer senderId;

    /**
     * 接收者id
     */
    @TableField(value = "receiver_id")
    private Integer receiverId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 消息是否删除/撤回，未撤回0-撤回1
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 消息是否已读，未读0-已读1
     */
    @TableField(value = "is_read")
    private Integer isRead;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
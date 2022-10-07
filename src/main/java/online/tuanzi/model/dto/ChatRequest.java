package online.tuanzi.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ChatRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 14:25
 * @Description: 聊天实体，用于接收聊天信息
 */
@Data
public class ChatRequest implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 好友id
     */
    private Integer friendId;

    /**
     * 组id
     */
    private Integer groupId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息
     */
    private String message;
}

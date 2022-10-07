package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FriendMessageRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 16:42
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendMessageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容类型，文本0-图片1-视频2
     */
    private Integer type;

    /**
     * 发送者id
     */
    private Integer senderId;

    /**
     * 接收者id
     */
    private Integer receiverId;

    /**
     * 创建时间
     */
    private Date createTime;
}

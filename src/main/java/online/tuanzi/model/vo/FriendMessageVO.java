package online.tuanzi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FriendMessageVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 17:24
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendMessageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    private Integer id;

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

package online.tuanzi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SessionListVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 21:35
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class SessionListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 会话列表id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 会话目标id，群会话则是群id，否则是好友id
     */
    private Integer targetId;

    /**
     * 会话目标名称，群会话则是群名称，否则是好友名称
     */
    private String targetName;

    /**
     * 会话目标头像url
     */
    private String targetUrl;

    /**
     * 是否是群会话，是0，否1
     */
    private Integer isGroup;

    /**
     * 最后用户id
     */
    private Integer lastUserId;

    /**
     * 最后内容
     */
    private String content;

    /**
     * 对应的消息列表
     */
    private List<FriendMessageVO> message;

    /**
     * 最后时间
     */
    private Date lastTime;
}

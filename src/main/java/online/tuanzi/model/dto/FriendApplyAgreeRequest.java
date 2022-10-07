package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: FriendApplyAgreeRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 19:22
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendApplyAgreeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 好友关系id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 朋友id
     */
    private Integer friendId;
}

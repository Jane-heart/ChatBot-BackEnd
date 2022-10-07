package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: FriendDeleteRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 21:50
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendDeleteRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 好友id
     */
    private Integer friendId;
}

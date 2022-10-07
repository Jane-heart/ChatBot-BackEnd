package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FriendApplyRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 18:45
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendApplyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 朋友id
     */
    private Integer friendId;

    /**
     * 创建时间
     */
    private Date createTime;
}

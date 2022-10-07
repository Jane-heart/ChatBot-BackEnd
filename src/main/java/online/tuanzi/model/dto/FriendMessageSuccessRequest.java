package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: FriendMessageSuccessRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 17:54
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendMessageSuccessRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    private Integer id;
}

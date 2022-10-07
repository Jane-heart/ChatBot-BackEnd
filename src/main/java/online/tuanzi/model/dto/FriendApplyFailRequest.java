package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: FriendApplyFailRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 21:46
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendApplyFailRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 好友关系id
     */
    private Integer id;
}

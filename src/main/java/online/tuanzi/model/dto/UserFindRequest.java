package online.tuanzi.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserFindRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 14:46
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class UserFindRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 根据用户名搜索
     */
    private String userName;

}

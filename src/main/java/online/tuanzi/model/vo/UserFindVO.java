package online.tuanzi.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserFindVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/06 14:53
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class UserFindVO implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 头像url
     */
    private String avatar;
}

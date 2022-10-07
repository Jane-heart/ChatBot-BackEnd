package online.tuanzi.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FriendBasicVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/05 21:01
 * @Description: 想想此类的作用，写在这里吧。
 */
@Data
public class FriendBasicVO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 朋友id
     */
    private Integer friendId;

    /**
     * 朋友姓名
     */
    private String userName;

    /**
     * 朋友性别
     */
    private Integer gender;

    /**
     * 头像url
     */
    private String avatar;
}

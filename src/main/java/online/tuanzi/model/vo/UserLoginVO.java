package online.tuanzi.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserLoginVO
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 18:35
 * @Description: 登录成功后返回的用户信息
 */
@Data
public class UserLoginVO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Object id;

    /**
     * 姓名
     */
    private String userName;
}

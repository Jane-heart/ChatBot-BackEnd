package online.tuanzi.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserLoginRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 17:54
 * @Description: 用户登录信息
 */
@Data
public class UserLoginRequest implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verificationCode;
}

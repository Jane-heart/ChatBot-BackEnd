package online.tuanzi.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @ClassName: UserRegisterRequest
 * @Author: 团子tz
 * @CreateTime: 2022/10/04 19:19
 * @Description: 用户注册信息
 */
@Data
public class UserRegisterRequest {

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

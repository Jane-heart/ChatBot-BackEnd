package online.tuanzi.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 性别男0-女1
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 个性签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 注册时间
     */
    @TableField(value = "register_time")
    private Date registerTime;
}
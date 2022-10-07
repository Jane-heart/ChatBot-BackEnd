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
 * @TableName t_session_list
 */
@TableName(value ="t_session_list")
@Data
public class SessionList implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 会话列表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 会话目标id，群会话则是群id，否则是好友id
     */
    @TableField(value = "target_id")
    private Integer targetId;

    /**
     * 是否是群会话，是0，否1
     */
    @TableField(value = "is_group")
    private Integer isGroup;

    /**
     * 最后用户id
     */
    @TableField(value = "last_user_id")
    private Integer lastUserId;

    /**
     * 最后内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 最后时间
     */
    @TableField(value = "last_time")
    private Date lastTime;

    /**
     * 是否删除，未删除0-删除1
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

}
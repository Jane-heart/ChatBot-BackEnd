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
 * @TableName t_group_member
 */
@TableName(value ="t_group_member")
@Data
public class GroupMember implements Serializable {
    /**
     * 群成员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 群聊id
     */
    @TableField(value = "group_id")
    private Integer groupId;

    /**
     * 成员id
     */
    @TableField(value = "member_id")
    private Integer memberId;

    /**
     * 是否退群，未进群0-未退群1-退群2
     */
    @TableField(value = "is_quit")
    private Integer isQuit;

    /**
     * 邀请时间
     */
    @TableField(value = "invite_time")
    private Date inviteTime;

    /**
     * 加群时间
     */
    @TableField(value = "agree_time")
    private Date agreeTime;

    /**
     * 退群时间
     */
    @TableField(value = "quit_time")
    private Date quitTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GroupMember other = (GroupMember) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getIsQuit() == null ? other.getIsQuit() == null : this.getIsQuit().equals(other.getIsQuit()))
            && (this.getInviteTime() == null ? other.getInviteTime() == null : this.getInviteTime().equals(other.getInviteTime()))
            && (this.getAgreeTime() == null ? other.getAgreeTime() == null : this.getAgreeTime().equals(other.getAgreeTime()))
            && (this.getQuitTime() == null ? other.getQuitTime() == null : this.getQuitTime().equals(other.getQuitTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getIsQuit() == null) ? 0 : getIsQuit().hashCode());
        result = prime * result + ((getInviteTime() == null) ? 0 : getInviteTime().hashCode());
        result = prime * result + ((getAgreeTime() == null) ? 0 : getAgreeTime().hashCode());
        result = prime * result + ((getQuitTime() == null) ? 0 : getQuitTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", memberId=").append(memberId);
        sb.append(", isQuit=").append(isQuit);
        sb.append(", inviteTime=").append(inviteTime);
        sb.append(", agreeTime=").append(agreeTime);
        sb.append(", quitTime=").append(quitTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
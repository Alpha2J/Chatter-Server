package cn.sourcecodes.chatterServer.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ChatterGroup {
    private int id;
    private String account;
    private String headImage;
    private String groupName;
    private String notice;  //群通告
    private Date createTime;
    private int state;  //群状态, 禁言, 仅允许群主发言等
    private int groupOwnerId;//群主, 不做管理员了.
    private List<Chatter> groupMember;//群成员

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(int groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public List<Chatter> getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(List<Chatter> groupMember) {
        this.groupMember = groupMember;
    }
}

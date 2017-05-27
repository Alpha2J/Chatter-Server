package cn.sourcecodes.chatterServer.entity;

/**
 * 表示好友的类, 有些字段在chatter里面有的, 但是在好友里可以没有, 所以删掉
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class Contact {
    private int id;
    private String phone;
    private String headImage;
    private String nickName;
    private String gender;
    private String signature;//个性签名
    private String region;//地区
    private int contactGroupTypeId;//分组信息
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getContactGroupTypeId() {
        return contactGroupTypeId;
    }

    public void setContactGroupTypeId(int contactGroupTypeId) {
        this.contactGroupTypeId = contactGroupTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

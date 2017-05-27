package cn.sourcecodes.chatterServer.entity;

import java.util.Date;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class Message {
    private int id;
    private String uuid;//消息的唯一uuid, 类似chatter的账号account
    private int messageType;
    private int contentType;
    private Date sendTime;//消息发送时候的时间戳
    private int sendId;//发送者的id
    private int receiveId;//发送到的那个群的id
    private String content;

    public Message() {}

    public Message(int id, String uuid, int messageType, int contentType, Date sendTime, int sendId, int receiveId, String content) {
        this.id = id;
        this.uuid = uuid;
        this.messageType = messageType;
        this.contentType = contentType;
        this.sendTime = sendTime;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

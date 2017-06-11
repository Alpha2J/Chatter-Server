package cn.sourcecodes.chatterServer.servlet.appInit.data;

import cn.sourcecodes.chatterServer.entity.*;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class AppInitData {
    private Chatter chatter;//个人信息
    private List<ContactGroupType> contactGroupTypeList;//好友列表类型
    private List<Contact> contactList;//好友列表
    private List<Message> messageList;

    public Chatter getChatter() {
        return chatter;
    }

    public void setChatter(Chatter chatter) {
        this.chatter = chatter;
    }

    public List<ContactGroupType> getContactGroupTypeList() {
        return contactGroupTypeList;
    }

    public void setContactGroupTypeList(List<ContactGroupType> contactGroupTypeList) {
        this.contactGroupTypeList = contactGroupTypeList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}

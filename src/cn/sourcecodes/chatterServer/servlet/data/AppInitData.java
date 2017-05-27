package cn.sourcecodes.chatterServer.servlet.data;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class AppInitData {
    private Chatter chatter;//个人信息
    private List<ContactGroupType> contactGroupTypeList;//好友列表类型
    private List<Contact> contactList;//好友列表
    private List<ChatterGroup> chatterGroupList;//群组列表

    public AppInitData() {}

    public AppInitData(Chatter chatter, List<ContactGroupType> contactGroupTypeList, List<Contact> contactList, List<ChatterGroup> chatterGroupList) {
        this.chatter = chatter;
        this.contactGroupTypeList = contactGroupTypeList;
        this.contactList = contactList;
        this.chatterGroupList = chatterGroupList;
    }

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

    public List<ChatterGroup> getChatterGroupList() {
        return chatterGroupList;
    }

    public void setChatterGroupList(List<ChatterGroup> chatterGroupList) {
        this.chatterGroupList = chatterGroupList;
    }
}

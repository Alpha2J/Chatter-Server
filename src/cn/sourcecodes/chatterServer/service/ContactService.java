package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.Contact;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactService {
    /**
     * 获取该chatter的所有联系人
     * @param chatterId
     * @return
     */
    List<Contact> findContacts(int chatterId);

    /**
     * 获取获取指定联系人
     * @param chatterId
     * @param contactId
     * @return
     */
    Contact findContact(int chatterId, int contactId);

    /**
     * 更新备注
     * @param chatterId
     * @param contactId
     * @param newRemark
     * @return
     */
    boolean resetRemark(int chatterId, int contactId, String newRemark);

    /**
     * 更新分组
     * @param chatterId
     * @param contactId
     * @param contactGroupTypeId
     * @return
     */
    boolean resetGroup(int chatterId, int contactId, int contactGroupTypeId);
}

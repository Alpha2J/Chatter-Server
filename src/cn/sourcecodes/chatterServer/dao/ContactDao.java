package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Contact;

import java.util.List;
import java.util.Map;

/**
 * 联系人dao
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactDao {

    /**
     * 增加联系人
     * @param chatterId  谁增加的联系人, 就是这个谁
     * @param contactId  增加了谁, 这个谁
     * @return
     */
    boolean addContact(int chatterId, int contactId);

    /**
     * 增加联系人, 并指明联系人属于哪个分组
     * @param chatterId
     * @param contactId
     * @param contactGroupTypeId
     * @return
     */
    boolean addContact(int chatterId, int contactId, int contactGroupTypeId);

    /**
     * 删除联系人
     * @param chatterId
     * @param contactId
     * @return
     */
    boolean deleteContact(int chatterId, int contactId);

    /**
     * 获取指定的联系人
     * @param chatterId
     * @param contactId
     * @return
     */
    Contact getContact(int chatterId, int contactId);

    /**
     * 以列表形式获取所有联系人
     * @param chatterId
     * @return
     */
    List<Contact> getContactList(int chatterId);

    /**
     * 更新字段
     * @param chatterId
     * @param contactId
     * @param field
     * @param value
     * @return
     */
    boolean updateContact(int chatterId, int contactId, String field, Object value);

    /**
     * 同时更新多个字段
     * @param chatterId
     * @param contactId
     * @param fieldValueMap
     * @return
     */
    boolean updateContact(int chatterId, int contactId, Map<String, Object> fieldValueMap);
}

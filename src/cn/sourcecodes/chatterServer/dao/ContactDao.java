package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Contact;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 联系人dao
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactDao {

    /**
     * 增加联系人, 并指明联系人属于哪个分组
     * @param chatterId
     * @param contactId
     * @param contactGroupTypeId
     * @return
     */
    long addContact(int chatterId, int contactId, int contactGroupTypeId) throws SQLException;

    /**
     * 删除id为chatterId的用户的id为contactId的联系人
     * @param chatterId
     * @param contactId
     * @return
     */
    boolean deleteContact(int chatterId, int contactId) throws SQLException;

    /**
     * 获取指定的联系人
     * @param chatterId
     * @param contactId
     * @return
     */
    Contact getContact(int chatterId, int contactId) throws SQLException;

    /**
     * 以列表形式获取所有联系人
     * @param chatterId
     * @return
     */
    List<Contact> getContactList(int chatterId) throws SQLException;

    /**
     * 更新字段
     * @param chatterId
     * @param contactId
     * @param field
     * @param value
     * @return
     */
    boolean updateContact(int chatterId, int contactId, String field, Object value) throws SQLException;
}

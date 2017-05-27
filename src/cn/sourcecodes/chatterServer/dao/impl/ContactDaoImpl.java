package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ContactDao;
import cn.sourcecodes.chatterServer.entity.Contact;

import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactDaoImpl extends BaseDaoImpl<Contact> implements ContactDao {
    @Override
    public boolean addContact(int chatterId, int contactId) {
        return addContact(chatterId, contactId, 1);//这里不能写0, 数据库insert的时候不论id是不是写0, 设置了autoIncrement都是1开始
    }

    @Override
    public boolean addContact(int chatterId, int contactId, int contactGroupTypeId) {
        String sql = "INSERT INTO contactMapping(chatterId, contactId, contactGroupTypeId) VALUES( ?, ?, ?)";

        int updatedRow = update(sql, chatterId, contactId, contactGroupTypeId);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteContact(int chatterId, int contactId) {
        String sql = "DELETE FROM contactMapping WHERE chatterId = ? AND contactId = ?";

        int updatedRow = update(sql, chatterId, contactId);

        return updatedRow != 0;
    }

    @Override
    public Contact getContact(int chatterId, int contactId) {
        String sql = "SELECT " +
                "contact.id, contact.phone, contact.headImage, contact.nickname, contact.gender, contact.signature, contact.region, contactGroupTypeId, remark " +
                "FROM contactMapping " +
                "LEFT JOIN " +
                "chatter AS contact " +
                "ON contact.id = contactId " +
                "WHERE chatterId = ? AND contactId = ?";

        Contact contact = query(sql, chatterId, contactId);

        return contact;
    }

    @Override
    public List<Contact> getContactList(int chatterId) {
        String sql = "SELECT " +
                "contact.id, contact.phone, contact.headImage, contact.nickname, contact.gender, contact.signature, contact.region, contactGroupTypeId, remark " +
                "FROM contactMapping " +
                "LEFT JOIN " +
                "chatter AS contact " +
                "ON contact.id = contactId " +
                "WHERE chatterId = ?";

        List<Contact> contactList = queryForList(sql, chatterId);

        return contactList;
    }

    @Override
    public boolean updateContact(int chatterId, int contactId, String field, Object value) {
        String sql = "UPDATE contactMapping SET " + field + " = ? WHERE chatterId = ? AND contactId = ?";

        int updatedRow = update(sql, value, chatterId, contactId);

        return updatedRow != 0;
    }

    @Override
    public boolean updateContact(int chatterId, int contactId, Map<String, Object> fieldValueMap) {
        return false;
    }
}

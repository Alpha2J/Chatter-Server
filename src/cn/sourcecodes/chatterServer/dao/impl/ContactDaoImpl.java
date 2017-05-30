package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ContactDao;
import cn.sourcecodes.chatterServer.entity.Contact;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactDaoImpl extends BaseDaoImpl<Contact> implements ContactDao {

    private static ContactDaoImpl instance;

    private ContactDaoImpl() {}

    public static ContactDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ContactDaoImpl.class) {
                if(instance == null) {
                    instance = new ContactDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public long addContact(int chatterId, int contactId, int contactGroupTypeId) throws SQLException {
        String sql = "INSERT INTO contactMapping(chatterId, contactId, contactGroupTypeId) VALUES( ?, ?, ?)";

        return insert(sql, chatterId, contactId, contactGroupTypeId);
    }

    @Override
    public boolean deleteContact(int chatterId, int contactId) throws SQLException {
        String sql = "DELETE FROM contactMapping WHERE chatterId = ? AND contactId = ?";

        int updatedRow = update(sql, chatterId, contactId);

        return updatedRow != 0;
    }

    @Override
    public Contact getContact(int chatterId, int contactId) throws SQLException {
        String sql = "SELECT " +
                "contact.id, contact.phone, contact.headImage, contact.nickname, contact.gender, contact.signature, contact.region, contactGroupTypeId, remark " +
                "FROM contactMapping " +
                "LEFT JOIN " +
                "chatter AS contact " +
                "ON contact.id = contactId " +
                "WHERE chatterId = ? AND contactId = ?";

        return query(sql, chatterId, contactId);
    }

    @Override
    public List<Contact> getContactList(int chatterId) throws SQLException {
        String sql = "SELECT " +
                "contact.id, contact.phone, contact.headImage, contact.nickname, contact.gender, contact.signature, contact.region, contactGroupTypeId, remark " +
                "FROM contactMapping " +
                "LEFT JOIN " +
                "chatter AS contact " +
                "ON contact.id = contactId " +
                "WHERE chatterId = ?";

        return queryForList(sql, chatterId);
    }

    @Override
    public boolean updateContact(int chatterId, int contactId, String field, Object value) throws SQLException {
        String sql = "UPDATE contactMapping SET " + field + " = ? WHERE chatterId = ? AND contactId =  ?";

        return update(sql, value, chatterId, contactId) != 0;
    }
}

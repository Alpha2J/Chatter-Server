package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupTypeDaoImpl extends BaseDaoImpl<ContactGroupType> implements ContactGroupTypeDao {

    private static ContactGroupTypeDaoImpl instance;

    private ContactGroupTypeDaoImpl() {}

    public static ContactGroupTypeDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ContactGroupTypeDaoImpl.class) {
                if(instance == null) {
                    instance = new ContactGroupTypeDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public long addContactGroupType(int chatterId, String typeName) throws SQLException {
        String sql = "INSERT INTO contactGroupType(chatterId, typeName) VALUES( ?, ? )";

        return insert(sql, chatterId, typeName);
    }

    @Override
    public boolean deleteContactGroupType(int id) throws SQLException {
        String sql = "DELETE FROM contactGroupType WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public ContactGroupType getContactGroupType(int id) throws SQLException {
        String sql = "SELECT id, chatterId, typeName FROM contactGroupType WHERE id = ?";

        return query(sql, id);
    }

    @Override
    public List<ContactGroupType> getAllContactGroupType(int chatterId) throws SQLException {
        String sql = "SELECT id, chatterId, typeName FROM contactGroupType WHERE chatterId = ?";

        return queryForList(sql, chatterId);
    }

    @Override
    public boolean updateContactGroupType(int id, String field, Object value) throws SQLException {
        String sql = "UPDATE contactGroupType SET " + field + " = ? WHERE id = ?";

        return update(sql, value, id) != 0;
    }
}

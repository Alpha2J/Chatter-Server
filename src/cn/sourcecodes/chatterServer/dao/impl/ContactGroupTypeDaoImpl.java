package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupTypeDaoImpl extends BaseDaoImpl<ContactGroupType> implements ContactGroupTypeDao {

    @Override
    public boolean addContactGroupType(int chatterId, String typeName) {
        String sql = "INSERT INTO contactGroupType(chatterId, typeName) VALUES( ?, ? )";

        int updatedRow = update(sql, chatterId, typeName);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteContactGroupType(int id) {
        String sql = "DELETE FROM contactGroupType WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public ContactGroupType getContactGroupType(int id) {
        String sql = "SELECT * FROM contactGroupType WHERE id = ?";

        ContactGroupType contactGroupType = query(sql, id);

        return contactGroupType;
    }

    @Override
    public List<ContactGroupType> getAllContactGroupType(int chatterId) {
        String sql = "SELECT * FROM contactGroupType WHERE chatterId = ?";

        List<ContactGroupType> contactGroupTypeList = queryForList(sql, chatterId);

        return contactGroupTypeList;
    }

    @Override
    public boolean updateContactGroupType(int id, String field, Object value) {
        String sql = "UPDATE contactGroupType SET " + field + " = ? WHERE id = ?";

        int updatedRow = update(sql, value, id);

        return updatedRow != 0;
    }
}

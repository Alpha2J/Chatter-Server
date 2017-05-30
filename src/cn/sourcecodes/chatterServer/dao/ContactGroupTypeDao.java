package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.sql.SQLException;
import java.util.List;

/**
 * 群分组dao类
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactGroupTypeDao {

    long addContactGroupType(int chatterId, String typeName) throws SQLException;

    boolean deleteContactGroupType(int id) throws SQLException;

    ContactGroupType getContactGroupType(int id) throws SQLException;

    List<ContactGroupType> getAllContactGroupType(int chatterId) throws SQLException;

    boolean updateContactGroupType(int id, String field, Object value) throws SQLException;
}

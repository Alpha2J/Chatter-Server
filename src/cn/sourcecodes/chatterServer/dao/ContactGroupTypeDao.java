package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactGroupTypeDao {

    boolean addContactGroupType(int chatterId, String typeName);

    boolean deleteContactGroupType(int id);

    ContactGroupType getContactGroupType(int id);

    List<ContactGroupType> getAllContactGroupType(int chatterId);

    boolean updateContactGroupType(int id, String field, Object value);
}

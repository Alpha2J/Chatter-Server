package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ContactGroupTypeDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;
import cn.sourcecodes.chatterServer.service.ContactGroupTypeService;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupTypeServiceImpl implements ContactGroupTypeService {
    private ContactGroupTypeDao contactGroupTypeDao = ContactGroupTypeDaoImpl.getInstance();
    private ChatterDao chatterDao = ChatterDaoImpl.getInstance();

    @Override
    public boolean addContactGroupType(ContactGroupType contactGroupType) {
        if(contactGroupType == null) {
            return false;
        }

        String typeName = contactGroupType.getTypeName();
        if(typeName != null && typeName.length() > 50) {
            return false;
        }

        if(typeName == null) {
            typeName = "默认分组";
        }


        boolean isSuccess = false;
        try {
            int chatterId = contactGroupType.getChatterId();
            Chatter chatter = chatterDao.getChatterById(chatterId);
            //如果不存在这个chatter, 那么增加失败
            if(chatter == null) {
                return isSuccess;
            }

            contactGroupTypeDao.addContactGroupType(contactGroupType.getChatterId(), contactGroupType.getTypeName());
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteContactGroup(int contactGroupId) {
        boolean isSuccess = false;
        try {
            isSuccess = contactGroupTypeDao.deleteContactGroupType(contactGroupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public ContactGroupType getContactGroupType(int contactGroupId) {
        ContactGroupType contactGroupType = null;

        try {
            contactGroupType = contactGroupTypeDao.getContactGroupType(contactGroupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactGroupType;
    }

    @Override
    public List<ContactGroupType> getAllContactGroupType(int chatterId) {
        List<ContactGroupType> contactGroupTypeList = null;

        try {
            contactGroupTypeList = contactGroupTypeDao.getAllContactGroupType(chatterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactGroupTypeList;
    }

    @Override
    public boolean renameContactGroup(int contactGroupId, String typeName) {
        if(typeName == null || typeName.length() > 50) {
            return false;
        }

        boolean isSuccess = false;

        try {
            isSuccess = contactGroupTypeDao.updateContactGroupType(contactGroupId, "typeName", typeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}

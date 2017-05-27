package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ContactGroupTypeDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;
import cn.sourcecodes.chatterServer.service.ContactGroupTypeService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupTypeServiceImpl implements ContactGroupTypeService {
    private ContactGroupTypeDao contactGroupTypeDao = new ContactGroupTypeDaoImpl();
    private ChatterDao chatterDao = new ChatterDaoImpl();

    @Override
    public boolean addContactGroupType(ContactGroupType contactGroupType) {
        String typeName = contactGroupType.getTypeName();
        if(typeName == null) {
            typeName = "未命名分组";
        }

        //字节长度不能大于30不能大于10
        try {
            if(typeName.getBytes("utf-8").length > 30) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int chatterId = contactGroupType.getChatterId();
        Chatter chatter = chatterDao.getChatter(chatterId);

        //如果不存在这个chatter, 那么增加失败
        if(null == chatter) {
            return false;
        }

        return contactGroupTypeDao.addContactGroupType(contactGroupType.getChatterId(), contactGroupType.getTypeName());
    }

    @Override
    public boolean deleteContactGroup(int contactGroupId) {
        return contactGroupTypeDao.deleteContactGroupType(contactGroupId);
    }

    @Override
    public ContactGroupType getContactGroupType(int contactGroupId) {
        return contactGroupTypeDao.getContactGroupType(contactGroupId);
    }

    @Override
    public List<ContactGroupType> getAllContactGroupType(int chatterId) {
        return contactGroupTypeDao.getAllContactGroupType(chatterId);
    }

    @Override
    public boolean renameContactGroup(int contactGroupId, String typeName) {
        if(typeName == null) {
            return false;
        }

        try {
            if(typeName.getBytes("utf-8").length > 30) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return contactGroupTypeDao.updateContactGroupType(contactGroupId, "typeName", typeName);
    }
}

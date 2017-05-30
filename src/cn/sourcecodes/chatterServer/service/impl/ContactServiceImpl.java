package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.ContactDao;
import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ContactDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ContactGroupTypeDaoImpl;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;
import cn.sourcecodes.chatterServer.service.ContactService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactServiceImpl implements ContactService {

    private static ContactServiceImpl instance;

    private ContactServiceImpl() {}

    public static ContactServiceImpl getInstance() {
        if(instance == null) {
            synchronized (ContactServiceImpl.class) {
                if(instance == null) {
                    instance = new ContactServiceImpl();
                    return instance;
                }
            }
        }

        return instance;
    }

    private ContactDao contactDao = ContactDaoImpl.getInstance();
    private ChatterDao chatterDao = ChatterDaoImpl.getInstance();
    private ContactGroupTypeDao contactGroupTypeDao = ContactGroupTypeDaoImpl.getInstance();

    @Override
    public boolean addContact(int chatterId, Contact contact) {
        if(contact == null) {
            return false;
        }

        boolean isSuccess = false;
        try {
            //不存在这个用户, 返回false
            if(chatterDao.getChatterById(chatterId) == null) {
                return false;
            }

            //联系人用户也不存在
            if(chatterDao.getChatterById(contact.getId()) == null) {
                return false;
            }

            //如果群组不存在, 设置id为1, 即默认群组
            if(contactGroupTypeDao.getContactGroupType(contact.getContactGroupTypeId()) == null) {
                contact.setContactGroupTypeId(1);
            }

            contactDao.addContact(chatterId, contact.getId(), contact.getContactGroupTypeId());
            isSuccess= true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteContact(int chatterId, int contactId) {
        boolean isSuccess = false;
        try {
            isSuccess = contactDao.deleteContact(chatterId, contactId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public List<Contact> findContacts(int chatterId) {
        List<Contact> contactList = null;

        try {
            contactList = contactDao.getContactList(chatterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    @Override
    public Contact findContact(int chatterId, int contactId) {
        Contact contact = null;

        try {
            contact = contactDao.getContact(chatterId, contactId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public boolean resetRemark(int chatterId, int contactId, String newRemark) throws SQLException {
        if(newRemark == null || newRemark.length() > 50) {
            return false;
        }

        return contactDao.updateContact(chatterId, contactId, "remark", newRemark);
    }

    @Override
    public boolean resetGroup(int chatterId, int contactId, int contactGroupTypeId) {
        boolean isSuccess = false;

        try {
            //如果不存在这个联系人组, 返回false
            if(contactGroupTypeDao.getContactGroupType(contactGroupTypeId) == null) {
                return false;
            }

            isSuccess = contactDao.updateContact(chatterId, contactId, "contactGroupTypeId", contactGroupTypeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}

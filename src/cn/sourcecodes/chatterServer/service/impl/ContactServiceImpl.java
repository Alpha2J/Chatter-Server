package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ContactDao;
import cn.sourcecodes.chatterServer.dao.impl.ContactDaoImpl;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.service.ContactGroupTypeService;
import cn.sourcecodes.chatterServer.service.ContactService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao = new ContactDaoImpl();
    private ContactGroupTypeService contactGroupTypeService = new ContactGroupTypeServiceImpl();

    @Override
    public List<Contact> findContacts(int chatterId) {
        List<Contact> contactList = contactDao.getContactList(chatterId);
        return contactList;
    }

    @Override
    public Contact findContact(int chatterId, int contactId) {
        Contact contact = contactDao.getContact(chatterId, contactId);
        return contact;
    }

    @Override
    public boolean resetRemark(int chatterId, int contactId, String newRemark) {
        if(null == newRemark) {
            return false;
        }

        try {
            if(newRemark.getBytes("utf-8").length > 30) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return contactDao.updateContact(chatterId, contactId, "remark", newRemark);
    }

    @Override
    public boolean resetGroup(int chatterId, int contactId, int contactGroupTypeId) {
        if(contactGroupTypeService.getContactGroupType(contactGroupTypeId) == null) {
            return false;
        }

        return contactDao.updateContact(chatterId, contactId, "contactGroupTypeId", contactGroupTypeId);
    }
}

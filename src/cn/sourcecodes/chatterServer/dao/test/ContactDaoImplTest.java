package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.ContactDao;
import cn.sourcecodes.chatterServer.dao.impl.ContactDaoImpl;
import cn.sourcecodes.chatterServer.entity.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactDaoImplTest {
    private ContactDao contactDao;

    @Before
    public void init() {
        this.contactDao = new ContactDaoImpl();
    }

    @Test
    public void testGetContact() {
        int chatterId = 1;
        int contactId = 2;
        Contact contact = contactDao.getContact(chatterId, contactId);
        System.out.println(contact.getNickName());
        System.out.println(contact.getId());
    }

    @Test
    public void testAddContact() {

    }

    @Test
    public void testUpdateContact() {
        contactDao.updateContact(1, 2, "remark", "大傻逼");
    }


    @After
    public void destroy() {
        this.contactDao = null;
    }


}

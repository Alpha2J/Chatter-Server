package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.ContactGroupTypeDao;
import cn.sourcecodes.chatterServer.dao.impl.ContactDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ContactGroupTypeDaoImpl;
import cn.sourcecodes.chatterServer.entity.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupTypeDaoTest {

    private ContactGroupTypeDao contactGroupTypeDao;

    @Before
    public void init() {
        this.contactGroupTypeDao = new ContactGroupTypeDaoImpl();
    }

    @Test
    public void testDeleteContactGroup() {
        boolean row = contactGroupTypeDao.deleteContactGroupType(3);
        if(row) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }

    @After
    public void destroy() {
        this.contactGroupTypeDao = null;
    }

}

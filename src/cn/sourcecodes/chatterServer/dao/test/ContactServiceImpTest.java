package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.service.ContactService;
import cn.sourcecodes.chatterServer.service.impl.ContactServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactServiceImpTest {
    private ContactService contactService;

    @Before
    public void init() {
        contactService = new ContactServiceImpl();
    }

    @Test
    public void testResetRemark() {
        contactService.resetRemark(1, 2, "hello world");
    }

    @After
    public void destroy() {
        this.contactService = null;
    }
}

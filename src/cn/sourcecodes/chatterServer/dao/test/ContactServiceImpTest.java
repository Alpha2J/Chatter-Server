package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.ContactService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
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

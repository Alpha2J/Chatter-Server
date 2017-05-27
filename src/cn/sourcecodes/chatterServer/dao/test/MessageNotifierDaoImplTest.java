package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.MessageNotifierDao;
import cn.sourcecodes.chatterServer.dao.impl.MessageNotifierDaoImpl;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public class MessageNotifierDaoImplTest {
    private MessageNotifierDao messageNotifierDao = null;

    @Before
    public void init() {
        this.messageNotifierDao = new MessageNotifierDaoImpl();
    }

    @Test
    public void testAdd() {
        MessageNotifier messageNotifier = new MessageNotifier();
        messageNotifier.setLastAccessMessageId(3);
        messageNotifier.setLastNewMessageId(2);
        messageNotifier.setChatterId(75);

        System.out.println(messageNotifierDao.addMessageNotifier(messageNotifier));
    }

    @Test
    public void testGetByChatterId() {
        int chatterId = 22;
        MessageNotifier messageNotifier = messageNotifierDao.getMessageNotifierByChatterId(chatterId);
        System.out.println(messageNotifier.getLastAccessMessageId());
        System.out.println(messageNotifier.getLastNewMessageId());
    }

    @Test
    public void testDeleteById() {
        int id = 10;
        System.out.println(messageNotifierDao.deleteMessageNotifierById(id));
    }

    @Test
    public void testUpdate() {
        MessageNotifier messageNotifier = new MessageNotifier();
        messageNotifier.setId(3);
        messageNotifier.setLastAccessMessageId(888);
        messageNotifier.setLastNewMessageId(888);
        messageNotifierDao.updateMessageNotifier(messageNotifier);
    }

    @After
    public void destroy() {
        this.messageNotifierDao = null;
    }

}

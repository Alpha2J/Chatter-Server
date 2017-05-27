package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.MessageDao;
import cn.sourcecodes.chatterServer.dao.impl.MessageDaoImpl;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.util.UUIDGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class MessageDaoImplTest {
    private MessageDao messageDao = null;

    @Before
    public void init() {
        messageDao = new MessageDaoImpl();
    }

    @Test
    public void testAddMessage() {
        Message message = new Message();

        message.setUuid(UUIDGenerator.generateUUID());
        message.setContentType(1);
        message.setMessageType(2);
        message.setSendTime(new Date());
        message.setSendId(1);
        message.setReceiveId(8);
        message.setContent("");

        messageDao.addMessage(message);
    }

    @Test
    public void testDeleteMessageById() {
        int messageId = 1;

        messageDao.deleteMessageById(messageId);
    }

    @Test
    public void testDeleteMessageByUuid() {
        String uuid = "e3064ad7e034498d8bc852ccf1940b83";

        messageDao.deleteMessageByUuid(uuid);
    }

    @Test
    public void testGetMessageById() {
        int id = 1;

        Message message = messageDao.getMessageById(id);

        System.out.println(message.getId());
        System.out.println(message.getUuid());
        System.out.println(message.getMessageType());
        System.out.println(message.getContentType());
        System.out.println(message.getSendTime());
        System.out.println(message.getSendId());
        System.out.println(message.getReceiveId());

    }

    @Test
    public void testGetMessageByUuId() {
        String uuid = "6c04f7ada8ed4e6aa0fbbe2bcefd6ea1";

        Message message = messageDao.getMessageByUuid(uuid);

        System.out.println(message.getId());
        System.out.println(message.getUuid());
        System.out.println(message.getMessageType());
        System.out.println(message.getContentType());
        System.out.println(message.getSendTime());
        System.out.println(message.getSendId());
        System.out.println(message.getReceiveId());

    }

    @Test
    public void testUpdateMessageById() {
        int messageId = 1;
        messageDao.updateMessageById(messageId, "hello", "hello world");
        MessageDaoImpl messageDao = new MessageDaoImpl();
    }




    @After
    public void destroy() {
        this.messageDao = null;
    }
}

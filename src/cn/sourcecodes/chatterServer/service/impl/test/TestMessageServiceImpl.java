package cn.sourcecodes.chatterServer.service.impl.test;

import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/6/7.
 */
public class TestMessageServiceImpl {
    private MessageService messageService = null;

    @Before
    public void init() {
        messageService = MessageServiceImpl.getInstance();
    }

    @Test
    public void testAddMessage() {
        Message message = new Message();
        message.setContent("hello");
        message.setContentType(1);
        message.setMessageType(2);
        messageService.addMessage(message);
    }



}

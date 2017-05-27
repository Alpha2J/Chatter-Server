package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.MessageDao;
import cn.sourcecodes.chatterServer.dao.impl.MessageDaoImpl;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao = new MessageDaoImpl();

    @Override
    public long addMessage(Message message) {
        return messageDao.addMessage(message);
    }

    @Override
    public List<Message> getNewMessages(int beginId, int receiveId) {
        //String sql = ""

        return null;
    }

}

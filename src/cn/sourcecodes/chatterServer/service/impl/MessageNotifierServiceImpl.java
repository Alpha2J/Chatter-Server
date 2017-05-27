package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.MessageNotifierDao;
import cn.sourcecodes.chatterServer.dao.impl.MessageNotifierDaoImpl;
import cn.sourcecodes.chatterServer.service.MessageNotifierService;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

/**
 * Created by cn.sourcecodes on 2017/5/27.
 */
public class MessageNotifierServiceImpl implements MessageNotifierService {
    private MessageNotifierDao messageNotifierDao = new MessageNotifierDaoImpl();

    @Override
    public MessageNotifier getLastMessageAccessData(int chatterId) {
        return messageNotifierDao.getMessageNotifierByChatterId(chatterId);
    }

    @Override
    public boolean updateMessageAccessData(MessageNotifier messageNotifier) {
        return messageNotifierDao.updateMessageNotifier(messageNotifier);
    }
}

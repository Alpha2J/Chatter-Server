package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.MessageDao;
import cn.sourcecodes.chatterServer.dao.MessageNotifierDao;
import cn.sourcecodes.chatterServer.dao.impl.MessageDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.MessageNotifierDaoImpl;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.service.fieldValidator.FieldValidatorFactory;
import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class MessageServiceImpl implements MessageService {

    private static MessageServiceImpl instance;

    private MessageServiceImpl() {}

    public static MessageServiceImpl getInstance() {
        if(instance == null) {
            synchronized (MessageServiceImpl.class) {
                if(instance == null) {
                    instance = new MessageServiceImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    private MessageDao messageDao = MessageDaoImpl.getInstance();
    private MessageNotifierDao messageNotifierDao = MessageNotifierDaoImpl.getInstance();

    @Override
    public long addMessage(Message message) {
        //系统错误
        if(message == null) {
            return MessageConstant.MESSAGE__ADD_MESSAGE_FAIL_SYSTEM_ERROR;
        }

        if(!FieldValidatorFactory.getValidator("MessageFieldValidator").validate(message)) {
            return MessageConstant.MESSAGE__ADD_MESSAGE_FAIL_UNKNOWN_REASON;
        }

        FieldInitializerFactory.getInitializer("MessageFieldInitializer").initializeField(message);

        try {
            return messageDao.addMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return MessageConstant.MESSAGE__ADD_MESSAGE_FAIL_UNKNOWN_REASON;
    }

    @Override
    public List<Message> getUnReadMessages(int chatterId) {
        List<Message> messageList = null;

        try {
            MessageNotifier messageNotifier = messageNotifierDao.getMessageNotifierByChatterId(chatterId);
            if(messageNotifier == null) {
                return null;
            }

            int beginId = messageNotifier.getLastAccessMessageId();
            messageList = messageDao.getMessageByReceiveId(chatterId, beginId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageList;
    }

    @Override
    public List<Message> getUnReadGroupMessage(int chatterId) {
        List<Message> messageList = null;

        try {
            MessageNotifier messageNotifier = messageNotifierDao.getMessageNotifierByChatterId(chatterId);
            if(messageNotifier == null) {
                return null;
            }

            int beginId = messageNotifier.getLastAccessMessageId();
            messageList = messageDao.getGroupMessageByReceiveId(chatterId, beginId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageList;
    }

    @Override
    public List<Message> getUnReadPrivateMessage(int chatterId) {
        List<Message> messageList = null;

        try {
            MessageNotifier messageNotifier = messageNotifierDao.getMessageNotifierByChatterId(chatterId);
            if(messageNotifier == null) {
                return null;
            }

            int beginId = messageNotifier.getLastAccessMessageId();
            messageList = messageDao.getPrivateMessageByReceiveId(chatterId, beginId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageList;
    }

    @Override
    public MessageNotifier getLastMessageAccessData(int chatterId) {
        MessageNotifier messageNotifier = null;

        try {
            messageNotifier = messageNotifierDao.getMessageNotifierByChatterId(chatterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messageNotifier;
    }

    @Override
    public boolean updateMessageAccessData(MessageNotifier messageNotifier) {
        if(messageNotifier == null) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = messageNotifierDao.updateMessageNotifier(messageNotifier);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}

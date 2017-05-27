package cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.impl;

import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.entity.Message;

import java.util.Date;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class MessageFieldInitializer implements FieldInitializer<Message> {


    private static MessageFieldInitializer instance;

    private MessageFieldInitializer() {}


    public static MessageFieldInitializer getInstance() {
        if(instance == null) {
            synchronized (MessageFieldInitializer.class) {
                if(instance == null) {
                    instance = new MessageFieldInitializer();
                    return instance;
                }
            }
        }

        return instance;
    }

    /**
     * 这里初始化方法都是检测引用字段是否为空, 如果为空就设简单的初始值,
     * 还有值字段是否为0, 如果为0, 说明没有设值就传进来了, 为他们设值初始值,
     * 设置的这些值其实没什么多大的意义, 主要是防止写入数据库是出错, 防止测试时候出问题,
     * 真正的有用数据会在service层做好验证, 不合法的message不会传到dao层的
     *
     * 对于这些数据的话, 在数据库层面看起来是无用数据
     *
     * @param message
     * @return 如果message为null, 那么返回null, 否则将传入的message对象的未初始化的域初始化后返回
     */
    @Override
    public Message initializeField(Message message) {
        if(message == null) {
            return null;
        }

        String uuid = message.getUuid();
        if(uuid == null) {
            uuid = "";
            message.setUuid(uuid);
        }

        int messageType = message.getMessageType();
        if(messageType == 0) {
            messageType = -1;
            message.setMessageType(messageType);
        }

        int contentType = message.getContentType();
        if(contentType == 0) {
            contentType = -1;
            message.setContentType(contentType);
        }

        Date sendTime = message.getSendTime();
        if(sendTime == null) {
            sendTime = new Date();
            message.setSendTime(sendTime);
        }

        int sendId = message.getSendId();
        if(sendId == 0) {
            sendId = -1;
            message.setSendId(sendId);
        }

        int receiveId = message.getReceiveId();
        if(receiveId == 0) {
            receiveId = -1;
            message.setReceiveId(receiveId);
        }

        String content = message.getContent();
        if(content == null) {
            content = "";
            message.setContent(content);
        }

        return message;
    }
}

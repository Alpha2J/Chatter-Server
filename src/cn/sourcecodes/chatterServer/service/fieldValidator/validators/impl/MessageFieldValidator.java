package cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl;

import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.FieldValidator;

/**
 * 要检查的字段 uuid 和 content
 * 1. 做长度检查
 *
 * 其他int类型的字段不做检查, 如果是不存在的用户id或什么其他id, 数据会存进去而没法取出来, 影响不大
 * Created by cn.sourcecodes on 2017/5/29.
 */
public class MessageFieldValidator implements FieldValidator<Message> {
    private static MessageFieldValidator instance;

    private MessageFieldValidator() {}

    public static MessageFieldValidator getInstance() {
        if(instance == null) {
            synchronized (MessageFieldValidator.class) {
                if(instance == null) {
                    instance = new MessageFieldValidator();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public boolean validate(Message message) {
        if (message == null) {
            return false;
        }

        String uuid = message.getUuid();
        if(uuid != null && uuid.length() > 50) {
            return false;
        }

        String content = message.getContent();
        if(content != null && content.length() > 255) {
            return false;
        }

        return true;
    }
}

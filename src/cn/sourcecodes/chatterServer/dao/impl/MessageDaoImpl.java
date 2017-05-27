package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.MessageDao;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.entity.Message;

import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

    @Override
    public long addMessage(Message message) {
        String sql = "INSERT INTO message(" +
                "uuid, messageType, contentType, " +
                "sendTime, sendId, receiveId, content) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?)";

        FieldInitializerFactory.getInitializer("MessageFieldInitializer").initializeField(message);

        long addMessageId = insert(sql,
                message.getUuid(), message.getMessageType(), message.getContentType(),
                message.getSendTime(), message.getSendId(), message.getReceiveId(), message.getContent());

        return addMessageId;
    }

    @Override
    public boolean deleteMessageById(int messageId) {
        String sql = "DELETE FROM message WHERE id = ?";

        int updatedRow = update(sql, messageId);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteMessageByUuid(String uuid) {
        String sql = "DELETE FROM message WHERE uuid = ?";

        int updatedRow = update(sql, uuid);

        return updatedRow != 0;
    }

    @Override
    public Message getMessageById(int messageId) {
        String sql = "SELECT " +
                "id, uuid, messageType, contentType, sendTime, sendId, receiveId, content " +
                "FROM message WHERE id = ?";

        Message message = query(sql, messageId);

        return message;
    }

    @Override
    public Message getMessageByUuid(String uuid) {
        String sql = "SELECT " +
                "id, uuid, messageType, contentType, sendTime, sendId, receiveId, content " +
                "FROM message WHERE uuid = ?";

        Message message = query(sql, uuid);

        return message;
    }

    @Override
    public boolean updateMessageById(int messageId, String field, Object value) {
        String sql = "UPDATE message SET " + field + " = ? WHERE id = ?";

        return update(sql, value, messageId) != 0;
    }

    @Override
    public boolean updateMessageById(int messageId, Map<String, Object> fieldValueMap) {
        return false;
    }

    @Override
    public boolean updateMessageByUuid(String uuid, String field, Object value) {
        String sql = "UPDATE message SET " + field + " = ? WHERE uuid = ?";

        return update(sql, value, uuid) != 0;
    }

    @Override
    public boolean updateMessageByUuid(String uuid, Map<String, Object> fieldValueMap) {
        return false;
    }

}

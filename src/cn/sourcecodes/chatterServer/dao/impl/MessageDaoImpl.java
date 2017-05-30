package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.MessageDao;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

    private static MessageDaoImpl instance;

    private MessageDaoImpl() {}

    public static MessageDaoImpl getInstance() {
        if(instance == null) {
            synchronized (MessageDaoImpl.class) {
                if(instance == null) {
                    instance = new MessageDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public long addMessage(Message message) throws SQLException {
        String sql = "INSERT INTO message(" +
                "uuid, messageType, contentType, " +
                "sendTime, sendId, receiveId, content) " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?)";

        long addMessageId = insert(sql,
                message.getUuid(), message.getMessageType(), message.getContentType(),
                message.getSendTime(), message.getSendId(), message.getReceiveId(), message.getContent());

        return addMessageId;
    }

    @Override
    public boolean deleteMessageById(int messageId) throws SQLException {
        String sql = "DELETE FROM message WHERE id = ?";

        int updatedRow = update(sql, messageId);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteMessageByUuid(String uuid) throws SQLException {
        String sql = "DELETE FROM message WHERE uuid = ?";

        int updatedRow = update(sql, uuid);

        return updatedRow != 0;
    }

    @Override
    public Message getMessageById(int messageId) throws SQLException {
        String sql = "SELECT " +
                "id, uuid, messageType, contentType, sendTime, sendId, receiveId, content " +
                "FROM message WHERE id = ?";

        Message message = query(sql, messageId);

        return message;
    }

    @Override
    public Message getMessageByUuid(String uuid) throws SQLException {
        String sql = "SELECT " +
                "id, uuid, messageType, contentType, sendTime, sendId, receiveId, content " +
                "FROM message WHERE uuid = ?";

        Message message = query(sql, uuid);

        return message;
    }

    @Override
    public List<Message> getMessageByReceiveId(int receiveId, int beginId) throws SQLException {
        String sql = "SELECT m.id, m.uuid, m.messageType, m.contentType, m.sendTime, m.sendId, m.receiveId, m.content " +
                "FROM message AS m " +
                "LEFT JOIN chatterGroupMapping AS cgm " +
                "ON m.receiveId = cgm.chatterGroupId " +
                "WHERE " +
                "(m.messageType = ? AND m.id > ? AND cgm.chatterId = ?) OR (m.messageType = ? AND m.id > ? AND m.receiveId = ? )";

        return queryForList(sql,
                MessageConstant.MESSAGE__TYPE_GROUP, beginId, receiveId,
                MessageConstant.MESSAGE__TYPE_PRIVATE, beginId, receiveId
                );
    }

    @Override
    public List<Message> getGroupMessageByReceiveId(int receiveId, int beginId) throws SQLException {
        String sql = "SELECT " +
                "m.id, m.uuid, m.messageType, m.contentType, m.sendTime, m.sendId, m.receiveId, m.content " +
                "FROM message AS m " +
                "LEFT JOIN chatterGroupMapping AS cgm " +
                "ON m.receiveId = cgm.chatterGroupId " +
                "WHERE messageType = ? AND m.id > ? AND cgm.chatterId = ?";

        return queryForList(sql, MessageConstant.MESSAGE__TYPE_GROUP, beginId, receiveId);
    }

    @Override
    public List<Message> getPrivateMessageByReceiveId(int receiveId, int beginId) throws SQLException {
        String sql = "SELECT " +
                "id, uuid, messageType, contentType, sendTime, sendId, receiveId, content " +
                "FROM message WHERE messageType = ? AND id > ? AND receiveId = ?";

        return queryForList(sql, MessageConstant.MESSAGE__TYPE_PRIVATE, beginId, receiveId);
    }

    @Override
    public boolean updateMessageById(int messageId, String field, Object value) throws SQLException {
        String sql = "UPDATE message SET " + field + " = ? WHERE id = ?";

        return update(sql, value, messageId) != 0;
    }

    @Override
    public boolean updateMessageByUuid(String uuid, String field, Object value) throws SQLException {
        String sql = "UPDATE message SET " + field + " = ? WHERE uuid = ?";

        return update(sql, value, uuid) != 0;
    }
}

package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.MessageNotifierDao;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public class MessageNotifierDaoImpl extends BaseDaoImpl<MessageNotifier> implements MessageNotifierDao {
    @Override
    public long addMessageNotifier(MessageNotifier messageNotifier) {
        String sql = "INSERT INTO chatterNotifierMapping(" +
                "chatterId, lastAccessMessageId, lastNewMessageId) " +
                "VALUES(?, ?, ?)";

        long addedMessageNotifierId = insert(sql,
                messageNotifier.getChatterId(), messageNotifier.getLastAccessMessageId(),
                messageNotifier.getLastNewMessageId());

        return addedMessageNotifierId;
    }

    @Override
    public boolean deleteMessageNotifierById(int id) {
        String sql = "DELETE FROM chatterNotifierMapping WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteMessageNotifierByChatterId(int chatterId) {
        String sql = "DELETE FROM chatterNotifierMapping WHERE chatterId = ?";

        int updatedRow = update(sql, chatterId);

        return updatedRow != 0;
    }

    @Override
    public MessageNotifier getMessageNotifierById(int id) {
        String sql = "SELECT " +
                "id, chatterId, lastAccessMessageId, lastNewMessageId " +
                "FROM chatterNotifierMapping " +
                "WHERE id = ?";

        MessageNotifier messageNotifier = query(sql, id);

        return messageNotifier;
    }

    @Override
    public MessageNotifier getMessageNotifierByChatterId(int chatterId) {
        String sql = "SELECT " +
                "id, chatterId, lastAccessMessageId, lastNewMessageId " +
                "FROM chatterNotifierMapping " +
                "WHERE chatterId = ?";

        MessageNotifier messageNotifier = query(sql, chatterId);

        return messageNotifier;
    }

    @Override
    public boolean updateMessageNotifier(MessageNotifier messageNotifier) {
        String sql = "UPDATE chatterNotifierMapping " +
                "SET " +
                "lastAccessMessageId = ?, lastNewMessageId = ? " +
                "WHERE id = ?";

        int updatedRow = update(sql,
                messageNotifier.getLastAccessMessageId(),
                messageNotifier.getLastNewMessageId(),
                messageNotifier.getId());

        return updatedRow != 0;
    }

    @Override
    public boolean updateMessageNotifierById(int id, String field, Object value) {
        String sql = "UPDATE chatterNotifierMapping SET " + field + " = ? WHERE id = ?";

        return update(sql, value, id) != 0;
    }

    @Override
    public boolean updateMessageNotifierById(int id, Map<String, Object> fieldValueMap) {
        return false;
    }

    @Override
    public boolean updateMessageNotifierByChatterId(int chatterId, String field, Object value) {
        String sql = "UPDATE chatterNotifierMapping SET " + field + " = ? WHERE chatterId = ?";

        return update(sql, value, chatterId) != 0;
    }

    @Override
    public boolean updateMessageNotifierByChatterId(int id, Map<String, Object> fieldValueMap) {
        return false;
    }
}

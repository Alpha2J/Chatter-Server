package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public interface MessageNotifierDao {

    /**
     * 增加一个MessageNotifier
     * @param messageNotifier
     * @return 刚增加进去的id
     */
    long addMessageNotifier(MessageNotifier messageNotifier) throws SQLException;

    /**
     * 通过id删除
     * @param id
     * @return
     */
    boolean deleteMessageNotifierById(int id) throws SQLException;

    /**
     * 通过chatterId来删除
     * @param chatterId
     * @return
     */
    boolean deleteMessageNotifierByChatterId(int chatterId) throws SQLException;

    /**
     * 通过notifier的id来获取
     * @param id
     * @return
     */
    MessageNotifier getMessageNotifierById(int id) throws SQLException;

    /**
     * 通过用户id来获取
     * @param chatterId
     * @return
     */
    MessageNotifier getMessageNotifierByChatterId(int chatterId) throws SQLException;

    /**
     * 根据messageNotifier的id来更新  lastAccessMessageId 和 lastNewMessageId 域
     * @param messageNotifier
     * @return
     */
    boolean updateMessageNotifier(MessageNotifier messageNotifier) throws SQLException;

    /**
     * 通过id修改
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageNotifierById(int id, String field, Object value) throws SQLException;

    /**
     * 通过chatterId来修改
     * @param chatterId
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageNotifierByChatterId(int chatterId, String field, Object value) throws SQLException;
}

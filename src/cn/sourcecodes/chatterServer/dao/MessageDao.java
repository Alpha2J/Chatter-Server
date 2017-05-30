package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Message;

import java.sql.SQLException;
import java.util.List;


/**
 * 执行消息处理的底层dao
 * Created by cn.sourcecodes on 2017/5/23.
 */
public interface MessageDao {

    /**
     * 增加消息
     * @param message
     * @return
     */
    long addMessage(Message message) throws SQLException;

    /**
     * 通过id删除消息
     * @param messageId
     * @return
     */
    boolean deleteMessageById(int messageId) throws SQLException;

    /**
     * 通过uuid删除消息
     * @param uuid
     * @return
     */
    boolean deleteMessageByUuid(String uuid) throws SQLException;

    /**
     * 通过id获取消息
     * @param messageId
     * @return
     */
    Message getMessageById(int messageId) throws SQLException;

    /**
     * 通过uuid获取消息
     * @param uuid
     * @return
     */
    Message getMessageByUuid(String uuid) throws SQLException;

    /**
     * 通过接收者的id获取从beginId 开始的所有消息, 包括群聊消息和私聊消息
     * @param receiveId
     * @param beginId
     * @return
     */
    List<Message> getMessageByReceiveId(int receiveId, int beginId) throws SQLException;

    /**
     * 通过接收者id获取所有的从 beginId 开始的群聊消息
     * @param receiveId
     * @param beginId
     * @return
     * @throws SQLException
     */
    List<Message> getGroupMessageByReceiveId(int receiveId, int beginId) throws SQLException;

    /**
     * 通过接收者id获取所有的从 beginId 开始的私聊消息
     * @param receiveId
     * @param beginId
     * @return
     * @throws SQLException
     */
    List<Message> getPrivateMessageByReceiveId(int receiveId, int beginId) throws SQLException;

    /**
     * 通过id更新指定字段
     * @param messageId
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageById(int messageId, String field, Object value) throws SQLException;

    /**
     * 通过uuid更新指定字段
     * @param uuid
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageByUuid(String uuid, String field, Object value) throws SQLException;
}

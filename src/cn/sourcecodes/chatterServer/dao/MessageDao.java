package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Message;

import java.util.Map;

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
    long addMessage(Message message);

    /**
     * 通过id删除消息
     * @param messageId
     * @return
     */
    boolean deleteMessageById(int messageId);

    /**
     * 通过uuid删除消息
     * @param uuid
     * @return
     */
    boolean deleteMessageByUuid(String uuid);

    /**
     * 通过id获取消息
     * @param messageId
     * @return
     */
    Message getMessageById(int messageId);

    /**
     * 通过uuid获取消息
     * @param uuid
     * @return
     */
    Message getMessageByUuid(String uuid);

    /**
     * 通过id更新指定字段
     * @param messageId
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageById(int messageId, String field, Object value);

    /**
     * 通过id更新一组字段
     * @param messageId
     * @param fieldValueMap
     * @return
     */
    boolean updateMessageById(int messageId, Map<String, Object> fieldValueMap);

    /**
     * 通过uuid更新指定字段
     * @param uuid
     * @param field
     * @param value
     * @return
     */
    boolean updateMessageByUuid(String uuid, String field, Object value);

    /**
     * 通过uuid更新一组字段
     * @param uuid
     * @param fieldValueMap
     * @return
     */
    boolean updateMessageByUuid(String uuid, Map<String, Object> fieldValueMap);
}

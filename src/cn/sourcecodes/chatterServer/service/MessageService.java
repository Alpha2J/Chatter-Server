package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import java.util.List;

/**
 * 消息业务逻辑处理接口
 * Created by cn.sourcecodes on 2017/5/23.
 */
public interface MessageService {

    /**
     * 增加消息
     * @param message
     * @return 刚加入的消息的id
     */
    long addMessage(Message message);

    /**
     * 获取某个人的未读消息 包括群聊和私聊等所有消息
     * @param chatterId
     * @return
     */
    List<Message> getUnReadMessages(int chatterId);

    /**
     * 获取未读的群聊消息
     * @param chatterId
     * @return
     */
    List<Message> getUnReadGroupMessage(int chatterId);

    /**
     * 获取未读的私聊消息
     * @param chatterId
     * @return
     */
    List<Message> getUnReadPrivateMessage(int chatterId);

    /**
     * 获取上次的消息访问记录
     * @param chatterId
     * @return
     */
    MessageNotifier getLastMessageAccessData(int chatterId);

    /**
     * 更新消息的访问数据, 上次访问的最后一个消息id, 上次的新id
     * @param messageNotifier
     * @return
     */
    boolean updateMessageAccessData(MessageNotifier messageNotifier);
}

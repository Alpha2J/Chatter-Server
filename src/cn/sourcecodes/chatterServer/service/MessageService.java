package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.Message;

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
     * 获取新的消息 包括群聊和私聊等所有id
     * @param beginId  从哪个id开始消息是新的
     * @param receiveId  消息的接收者是谁
     * @return
     */
    List<Message> getNewMessages(int beginId, int receiveId);


}

package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

/**
 * Created by cn.sourcecodes on 2017/5/27.
 */
public interface MessageNotifierService {

    /**
     * 获取上次访问的数据信息(上次读到了哪些地方)
     * @param chatterId
     * @return
     */
    MessageNotifier getLastMessageAccessData(int chatterId);

    /**
     * 更新数据访问信息
     * @param messageNotifier
     * @return
     */
    boolean updateMessageAccessData(MessageNotifier messageNotifier);


}

package cn.sourcecodes.chatterServer.servlet.message;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 客户端访问这个servlet, 获取最新的消息, 从服务器的角度来看消息是这里发出去的, 所以叫 MessageSendServlet
 * Created by cn.sourcecodes on 2017/5/23.
 */
public class MessageSendServlet extends HttpServlet {
    private Map<Integer, MessageNotifier> chatterNotifierMap;
    private MessageService messageService = MessageServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        chatterNotifierMap = (Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chatter chatter = (Chatter) request.getSession().getAttribute("chatter");
        if(chatter == null) {
            return;
        }

        int chatterId = chatter.getId();
        MessageNotifier messageNotifier = chatterNotifierMap.get(chatterId);

        int lastAccessMessageId = messageNotifier.getLastAccessMessageId();
        List<Message> messageList = messageService.getUnReadMessages(chatterId, lastAccessMessageId);

        String resultJson;
        if(messageList == null) {
            resultJson = ServerResponseUtils.generateResultJson(MessageConstant.MESSAGE__GET_SUCCESS_NULL, "没有新消息!");
        } else {
            resultJson = ServerResponseUtils.generateResultJson(MessageConstant.MESSAGE__GET_SUCCESS_NOT_NULL, JsonUtils.toJson(messageList, List.class));
        }

        response.getWriter().println(resultJson);
    }
}

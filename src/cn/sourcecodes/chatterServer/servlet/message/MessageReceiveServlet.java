package cn.sourcecodes.chatterServer.servlet.message;

import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import cn.sourcecodes.chatterServer.servlet.entity.ServerResponse;
import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.util.JsonUtils;
import cn.sourcecodes.chatterServer.util.UUIDGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 在这个servlet接口中处理客户端发送的消息,
 * 客户端发送的所有私聊消息和群聊消息都是发送到这里处理,
 * 从服务器的角度看, 这个servlet接收消息, 所以叫 MessageReceiveServlet
 */
@WebServlet(name = "messageSendServlet", urlPatterns = "/message/sendMessage")
public class MessageReceiveServlet extends HttpServlet {
    private MessageService messageService = new MessageServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = parameterCheck(request);

        String resultJson;
        if(message == null) {
            resultJson = generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "参数解析错误: 请确保参数完整且类型正确!");

            response.getWriter().println(resultJson);
            return;
        }

        int messageType = message.getMessageType();
        switch (messageType) {
            case MessageConstant.MESSAGE__TYPE_PRIVATE:
                privateMessage(message, request, response);
                break;
            case MessageConstant.MESSAGE__TYPE_GROUP:
                groupMessage(message, request, response);
                break;
            default:
                //不存在的聊天类型直接返回json信息通知客户端出现错误
                resultJson = generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "参数解析错误: 请传入合法的messageType参数!");
                response.getWriter().println(resultJson);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 处理私聊消息
     * @param message
     * @param request
     * @param response
     * @throws IOException
     */
    private void privateMessage(Message message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //先为message生成uuid和时间, 把这个语句写这里更方便, 就不用在下面的每个case中写一遍了,
        // 尽管contentType可能不存在, 是无效消息包, 但是为无效消息包生成这两个也没多大影响
        generateUuidAndTime(message);

        int receiveId = message.getReceiveId();
        MessageNotifier messageNotifier = ((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).get(receiveId);
        if(messageNotifier == null) {

        }
        switch (message.getContentType()) {
            case MessageConstant.MESSAGE__CONTENT_TYPE_PLAIN_TEXT :
                //处理文本消息
                long messageId = messageService.addMessage(message);
                //messageNotifier.setLastNewMessage(messageId);
                break;

            case MessageConstant.MESSAGE__CONTENT_TYPE_PICTURE :
                //处理图片消息
                break;

            case MessageConstant.MESSAGE__CONTENT_TYPE_VOICE :
                //处理语音消息
                break;

            case MessageConstant.MESSAGE__CONTENT_TYPE_VEDIO :
                //处理视频消息
                break;

            case MessageConstant.MESSAGE__CONTENT_TYPE_FILE :
                //处理文件消息
                break;
            default:
        }
    }

    /**
     * 处理群聊消息
     * @param message
     * @param request
     * @param response
     */
    private void groupMessage(Message message, HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 处理文件的上传(包括图片, 音频, 视频, 文件)
     * @param request
     * @return 返回上传后的文件路径
     */
    private String handleUpload(HttpServletRequest request) {
        request.getParameter("");
        return null;
    }



    /**
     * 填充message的 sendTime字段和生成消息的 uuid
     * @param message
     * @return
     */
    private Message generateUuidAndTime(Message message) {
        //不做是否为空判断, 约定无论是uuid还是发送时间都是在服务器生成(虽然发送时间在服务器生成的话会有延迟, 不过忽略它算了)
        message.setUuid(UUIDGenerator.generateUUID());
        message.setSendTime(new Date());

        return message;
    }


    /**
     * 检测参数的合法性
     * @param request
     * @return
     */
    private Message parameterCheck(HttpServletRequest request) {
        String messageTypeStr = request.getParameter("messageType");
        String contentTypeStr = request.getParameter("contentType");
        String sendIdStr = request.getParameter("sendId");
        String receiveIdStr = request.getParameter("receiveId");

        int messageType;
        int contentType;
        int sendId;
        int receiveId;

        Message message;

        try {
            messageType = Integer.parseInt(messageTypeStr);
            contentType = Integer.parseInt(contentTypeStr);
            sendId = Integer.parseInt(sendIdStr);
            receiveId = Integer.parseInt(receiveIdStr);

            //参数解析正常才newMessage对象
            message = new Message();
            message.setMessageType(messageType);
            message.setContentType(contentType);
            message.setSendId(sendId);
            message.setReceiveId(receiveId);
        } catch (NumberFormatException e) {
            message = null;
        }

        return message;
    }

    /**
     * 获得返回数据的json格式字符串
     * @param action
     * @param message
     * @return
     */
    private String generateResultJson(int action, String message) {
        ServerResponse validationResponse = new ServerResponse(action, message);
        String resultJson = JsonUtils.toJson(validationResponse, ServerResponse.class);
        return resultJson;
    }
}

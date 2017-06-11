package cn.sourcecodes.chatterServer.servlet.message;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;
import cn.sourcecodes.chatterServer.servlet.message.entity.BinaryMessageHandler;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils;
import cn.sourcecodes.chatterServer.util.UUIDGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import static cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils.generateResultJson;

/**
 * Created by cn.sourcecodes on 2017/6/7.
 */
@WebServlet(name = "privateMessageServlet", urlPatterns = "/message/sendMessage/privateMessage")
@MultipartConfig(
        fileSizeThreshold = 5_242_880,//5mb 文件超过5mb就写入临时文件
        maxFileSize = 104_857_600L,//100mb  传输的文件最大不能超过100mb
        maxRequestSize = 125_829_120L//120mb  一个请求最大不能超过120mb
)
public class PrivateMessageServlet extends HttpServlet {
    private MessageService messageService = MessageServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultJson;
        if(!userCheck(request)) {
            resultJson = generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "发送者与sendId不一致!");

            response.getWriter().println(resultJson);
            return;
        }

        Message message = parameterCheck(request);
        if(message == null) {
            resultJson = generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "参数解析错误: 请确保参数完整(messageType, contentType, sendId, receiveId)且类型正确!");

            response.getWriter().println(resultJson);
            return;
        }

        //开始处理消息
        handleMessage(message, request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void handleMessage(Message message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //先为message生成uuid和时间, 把这个语句写这里更方便, 就不用在下面的每个case中写一遍了,
        //尽管contentType可能不存在, 是无效消息包, 但是为无效消息包生成这两个也没多大影响
        generateUuidAndTime(message);

        Part content = request.getPart("content");
        if(content == null) {
            response.getWriter().println(ServerResponseUtils.generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "content参数缺失!"));
            return;
        }

        int receiveId = message.getReceiveId();
        MessageNotifier messageNotifier = ((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).get(receiveId);

        long messageId;
        //如果是文本消息, 直接将内容写到数据库
        if (message.getContentType() == MessageConstant.MESSAGE__CONTENT_TYPE_PLAIN_TEXT) {
            InputStream inputStream = content.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] bytes = new byte[inputStream.available()];
            outputStream.write(bytes);

            message.setContent(outputStream.toString());
            messageId = messageService.addMessage(message);
            if (messageNotifier != null) {
                messageNotifier.setLastNewMessage((int) messageId);
            }
        } else {
            String location = null;
            //生成路径
            switch (message.getContentType()) {
                case MessageConstant.MESSAGE__CONTENT_TYPE_PICTURE:
                    location = generateFileLocation("privateChat", "image");
                    break;
                case MessageConstant.MESSAGE__CONTENT_TYPE_VOICE:
                    location = generateFileLocation("privateChat", "voice");
                    break;
                case MessageConstant.MESSAGE__CONTENT_TYPE_VEDIO:
                    location = generateFileLocation("privateChat", "vedio");
                    break;
                case MessageConstant.MESSAGE__CONTENT_TYPE_FILE:
                    location = generateFileLocation("privateChat", "file");
                    break;
                default:
            }

            String fileLocation = BinaryMessageHandler.saveAndReturnLocation(content, message.getUuid(), request.getServletContext().getRealPath("chatData"), location);
            //如果返回的相对文件路径为空, 说明没有内容可以存储, 而图片等二进制文件的内容字段不能为空, 所以直接返回
            if (fileLocation == null) {
                response.getWriter().println(generateResultJson(MessageConstant.MESSAGE__PARAMETER_RESOLVE_ERROR, "content内容不能为空值!"));
            }

            message.setContent(fileLocation);
            messageId = messageService.addMessage(message);

            if (messageNotifier != null) {
                messageNotifier.setLastNewMessage((int) messageId);
            }

            response.getWriter().println(generateResultJson(MessageConstant.MESSAGE__HANDLE_SUCCESS, "消息发送成功!"));
        }
    }

    /**
     * 生成文件路径
     * @param args
     * @return
     */
    private String generateFileLocation(String... args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(File.separator);
            stringBuilder.append(args[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * 检测发送信息的用户是否当前session中的用户
     * @param request
     * @return
     */
    private boolean userCheck(HttpServletRequest request) {
        Chatter chatter = (Chatter) request.getSession().getAttribute("chatter");
        String sendIdStr = request.getParameter("sendId");
        int sendId;
        try {
            sendId = Integer.parseInt(sendIdStr);
        } catch (NumberFormatException e) {
            return false;
        }

        if(chatter.getId() == sendId) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 填充message的 sendTime字段和生成消息的 uuid
     *
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
     *
     * @param request
     * @return
     */
    private Message parameterCheck(HttpServletRequest request) throws IOException, ServletException {
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
            //如果不是私聊消息, 返回null
            if(messageType != MessageConstant.MESSAGE__TYPE_PRIVATE) {
                return null;
            }

            contentType = Integer.parseInt(contentTypeStr);
            sendId = Integer.parseInt(sendIdStr);
            receiveId = Integer.parseInt(receiveIdStr);

            System.out.println(sendId);
            System.out.println(receiveId);
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

}

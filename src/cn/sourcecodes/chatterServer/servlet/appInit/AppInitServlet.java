package cn.sourcecodes.chatterServer.servlet.appInit;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;
import cn.sourcecodes.chatterServer.entity.Message;
import cn.sourcecodes.chatterServer.service.ContactGroupTypeService;
import cn.sourcecodes.chatterServer.service.ContactService;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.ContactGroupTypeServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.ContactServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import cn.sourcecodes.chatterServer.servlet.appInit.data.AppInitData;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class AppInitServlet extends HttpServlet {
    private ContactService contactService = ContactServiceImpl.getInstance();
    private ContactGroupTypeService contactGroupTypeService = ContactGroupTypeServiceImpl.getInstance();
    private MessageService messageService = MessageServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AppInitData appInitData = new AppInitData();

        Chatter chatter = (Chatter) request.getSession().getAttribute("chatter");
        List<Contact> contactList = contactService.findContacts(chatter.getId());
        List<ContactGroupType> contactGroupTypeList = contactGroupTypeService.getAllContactGroupType(chatter.getId());
        MessageNotifier messageNotifier =
                ((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).get(chatter.getId());
        int lastAccessMessageId = messageNotifier.getLastAccessMessageId();
        List<Message> messageList = messageService.getUnReadMessages(chatter.getId(), lastAccessMessageId);
        if(messageList != null && messageList.size() > 0) {
            int maxId = findMaxId(messageList);
            messageNotifier.setLastAccessMessage(maxId);
            //更新上次访问的值
            ((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).put(chatter.getId(), messageNotifier);
        }

        appInitData.setChatter(chatter);
        appInitData.setContactList(contactList);
        appInitData.setContactGroupTypeList(contactGroupTypeList);
        appInitData.setMessageList(messageList);

        response.getWriter().println(JsonUtils.toJson(appInitData, AppInitData.class));
    }

    private int findMaxId(List<Message> messageList) {
        int max = 0;
        for (int i = 0; i < messageList.size(); i++) {
            if(messageList.get(i).getId() > max) {
                max = messageList.get(i).getId();
            }
        }

        return max;
    }

}

package cn.sourcecodes.chatterServer.listener;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.service.MessageNotifierService;
import cn.sourcecodes.chatterServer.service.impl.MessageNotifierServiceImpl;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public class MyServletListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    //用来保存chatterId 和 已登录用户的MessageNotifier 的映射map
    private Map<Integer, MessageNotifier> chatterNotifierMap;
    private MessageNotifierService messageNotifierService;

    // Public constructor is required by servlet spec
    public MyServletListener() {
    }

    // -------------------------------------------------------
    // ServletContext
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        this.chatterNotifierMap = new HashMap<>();
        sce.getServletContext().setAttribute("chatterNotifierMap", chatterNotifierMap);

        this.messageNotifierService = new MessageNotifierServiceImpl();
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {

    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session invalidate");
        HttpSession session = se.getSession();
        Object object = session.getAttribute("chatter");

        //等于null说明没有登录, 不为null, 转换不了说明程序可能不消息将key为chatter的值覆盖了
        if(object == null || !(object instanceof Chatter)) {
            return;
        }

        Chatter chatter = (Chatter) object;
        int chatterId = chatter.getId();

        MessageNotifier messageNotifier = chatterNotifierMap.get(chatterId);
        messageNotifierService.updateMessageAccessData(messageNotifier);//更新数据

        chatterNotifierMap.remove(chatterId);
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}

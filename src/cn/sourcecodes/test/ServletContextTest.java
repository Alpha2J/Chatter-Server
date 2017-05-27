package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
@WebServlet(name = "ServletContextTest", urlPatterns = "/servletContext")
public class ServletContextTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, MessageNotifier> map = (Map<Integer, MessageNotifier>) getServletContext().getAttribute("notifier");
        System.out.println(map);

    }
}

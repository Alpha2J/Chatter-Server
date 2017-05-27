package cn.sourcecodes.chatterServer.servlet.message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 客户端访问这个servlet, 获取最新的消息, 从服务器的角度来看消息是这里发出去的, 所以叫 MessageSendServlet
 * Created by cn.sourcecodes on 2017/5/23.
 */
@WebServlet(name = "MessageSendServlet")
public class MessageSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

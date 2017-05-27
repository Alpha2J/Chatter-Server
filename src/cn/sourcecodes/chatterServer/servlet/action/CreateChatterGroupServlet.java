package cn.sourcecodes.chatterServer.servlet.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class CreateChatterGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("actionType");
        String fromUserId = request.getParameter("fromUserId");
        String userArray = request.getParameter("groupUserArray");
        //执行逻辑
        //如果成功.
        //如果不成功
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

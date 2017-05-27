package cn.sourcecodes.chatterServer.servlet.test;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cn.sourcecodes on 2017/5/23.
 */
@WebServlet(name = "TestUrlServlet", urlPatterns = "/testUrl")
public class TestUrlServlet extends HttpServlet {
    private ChatterService chatterService = new ChatterServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method != null) {
            System.out.println(method);
        } else {
            System.out.println("null");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("chatterId");

        if(idStr == null) {
            response.getWriter().println("parameter is null");
            return;
        }

        Integer id = Integer.valueOf(idStr);

        Chatter chatter = chatterService.getChatter(id);

        response.getWriter().println(JsonUtils.toJson(chatter, Chatter.class));
    }
}

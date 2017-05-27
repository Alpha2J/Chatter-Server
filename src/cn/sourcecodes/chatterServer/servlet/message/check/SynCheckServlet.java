package cn.sourcecodes.chatterServer.servlet.message.check;

import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cn.sourcecodes on 2017/5/22.
 */
@WebServlet(name = "synCheckServlet", urlPatterns = "/synCheck")
public class SynCheckServlet extends HttpServlet {
    ChatterService chatterService = new ChatterServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatterIdStr = request.getParameter("chatterId");

        Integer chatterId;
        try {
            chatterId = Integer.valueOf(chatterIdStr);
        } catch (Exception e) {
            return;
        }



        while(true) {
            request.getServletContext().getAttribute("");

        }

    }
}

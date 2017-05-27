package cn.sourcecodes.chatterServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
@WebServlet(name = "ThreadServlet", urlPatterns = "/thread")
public class ThreadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode.equals("2")) {
            try {
                System.out.println("开始睡觉");
                Thread.sleep(50000);
                System.out.println("睡完了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("根本没睡");
        }
    }
}

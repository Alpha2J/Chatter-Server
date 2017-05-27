package cn.sourcecodes.chatterServer.servlet.contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
@WebServlet(name = "ContactServlet", urlPatterns = "/contact")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 增加好友
     * @param request
     * @param response
     */
    private void addContact(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 删除好友
     * @param request
     * @param response
     */
    private void deleteContact(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 获取好友基本信息
     * @param request
     * @param response
     */
    private void getContact(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 更新自己好友的基本信息, 一般情况下只有备注是能改的
     * @param request
     * @param response
     */
    private void updateContact(HttpServletRequest request, HttpServletRequest response) {

    }


}

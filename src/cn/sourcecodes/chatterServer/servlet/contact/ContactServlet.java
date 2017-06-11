package cn.sourcecodes.chatterServer.servlet.contact;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.ContactService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.ContactServiceImpl;
import cn.sourcecodes.chatterServer.servlet.contact.constant.ContactConstant;
import cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ContactServlet extends HttpServlet {
    private ChatterService chatterService = ChatterServiceImpl.getInstance();
    private ContactService contactService = ContactServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultJson;
        if(request.getAttribute("chatter") == null) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 查找好友
     * @param request
     * @param response
     */
    private void findChatter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String findStr = request.getParameter("findStr");

        List<Chatter> resultList = chatterService.wideFindChatter(findStr);

        String resultJson;
        if(resultList == null) {
            resultJson = ServerResponseUtils.generateResultJson(ContactConstant.CONTACT__FIND_COMPLETE_NULL, "");
        } else {
            String resultListJson = JsonUtils.toJson(resultList, List.class);
            resultJson = ServerResponseUtils.generateResultJson(ContactConstant.CONTACT__FIND_COMPLETE_NOT_NULL, resultListJson);
        }

        response.getWriter().println(resultJson);
    }

    /**
     * 增加好友
     * @param request
     * @param response
     */
    private void addContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contactIdStr = request.getParameter("contactId");
        String contactGroupTypeIdStr = request.getParameter("contactGroupTypeId");

        int contactId = 0;
        int contactGroupTypeId = 0;

        String resultJson;
        try {
            //这里不用对chatterIdStr和contactIdStr做是否为 null 判断, null的话这里parseInt也是抛出 NumberFormatException
            contactId = Integer.parseInt(contactIdStr);
            if(contactGroupTypeIdStr == null) {
                contactGroupTypeId = 1;
            } else {
                contactGroupTypeId = Integer.parseInt(contactGroupTypeIdStr);
            }
        } catch (NumberFormatException e) {
            resultJson = ServerResponseUtils.generateResultJson(ContactConstant.CONTACT__PARAMETER_RESOLVE_ERROR, "参数解析错误: chatterId或contactId参数不合法!");
            response.getWriter().println(resultJson);
        }

        //从session中获得自己的chatter对象
        int chatterId = ((Chatter)request.getSession().getAttribute("chatter")).getId();



        //boolean isSuccess = contactService.addContact(chatterId, contact);

//        if(isSuccess) {
//            //resultJson = ServerResponseUtils.generateResultJson(ContactConstant.)
//        }
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

package cn.sourcecodes.chatterServer.servlet;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.entity.Contact;
import cn.sourcecodes.chatterServer.entity.ContactGroupType;
import cn.sourcecodes.chatterServer.service.ChatterGroupService;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.ContactGroupTypeService;
import cn.sourcecodes.chatterServer.service.ContactService;
import cn.sourcecodes.chatterServer.service.impl.ChatterGroupServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.ContactGroupTypeServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.ContactServiceImpl;
import cn.sourcecodes.chatterServer.servlet.data.AppInitData;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class AppInitServlet extends HttpServlet {
    private ChatterService chatterService = new ChatterServiceImpl();
    private ContactGroupTypeService contactGroupTypeService = new ContactGroupTypeServiceImpl();
    private ContactService contactService = new ContactServiceImpl();
    private ChatterGroupService chatterGroupService = new ChatterGroupServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 用户获取初始化消息, 包括个人信息, 好友分组信息, 好友列表, 群列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag; //flag 的数值表示异常代号, 1为id不是数字

        String chatterIdStr = request.getParameter("chatterId");
        Integer chatterId;

        response.setContentType("application/json;charset=utf-8");
        try {
            chatterId = Integer.valueOf(chatterIdStr);//如果参数无法转换为int类型, 抛异常, 结束访问
        } catch(Exception e) {
            flag = 1;
            response.getWriter().println();
            return;
        }

        Chatter chatter = chatterService.getChatter(chatterId);
        List<ContactGroupType> contactGroupTypeList = contactGroupTypeService.getAllContactGroupType(chatterId);
        List<Contact> contactList = contactService.findContacts(chatterId);
        List<ChatterGroup> chatterGroupList = chatterGroupService.getOwnChatterGroup(chatterId);

        AppInitData appInitData = new AppInitData(chatter, contactGroupTypeList, contactList, chatterGroupList);

        String json = JsonUtils.toJson(appInitData, AppInitData.class);

        response.getWriter().println(json);
    }

}

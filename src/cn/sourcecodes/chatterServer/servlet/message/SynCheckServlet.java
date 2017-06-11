package cn.sourcecodes.chatterServer.servlet.message;

import cn.sourcecodes.chatterServer.servlet.message.constant.MessageConstant;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class SynCheckServlet extends HttpServlet {
    private Map<Integer, MessageNotifier> chatterNotifierMap;

    @Override
    public void init() throws ServletException {
        super.init();
        chatterNotifierMap = (Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatterIdStr = request.getParameter("chatterId");

        Integer chatterId;
        try {
            chatterId = Integer.valueOf(chatterIdStr);
        } catch (NumberFormatException e) {
            return;
        }

        MessageNotifier messageNotifier = chatterNotifierMap.get(chatterId);

        int times = 0;
        while(true) {
            if(messageNotifier.checkHasNewMessage()) {
                response.getWriter().println(ServerResponseUtils.generateResultJson(MessageConstant.MESSAGE__NEW_TRUE, "有新的消息!"));
                return;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(++times == 20) {
                response.getWriter().println(ServerResponseUtils.generateResultJson(MessageConstant.MESSAGE__NEW_FALSE, "没有新的消息!"));
                return;
            }
        }

    }
}

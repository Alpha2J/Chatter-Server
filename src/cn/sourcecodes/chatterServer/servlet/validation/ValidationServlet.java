package cn.sourcecodes.chatterServer.servlet.validation;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.MessageNotifierService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.MessageNotifierServiceImpl;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.validation.constant.ValidationConstant;
import cn.sourcecodes.chatterServer.servlet.entity.ServerResponse;
import cn.sourcecodes.chatterServer.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ValidationServlet extends javax.servlet.http.HttpServlet {

    private ChatterService userService = new ChatterServiceImpl();
    private MessageNotifierService messageNotifierService = new MessageNotifierServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        if(methodName == null) {
            String resultJson = generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: method参数缺失!");
            response.getWriter().println(resultJson);
            return;
        }

        Method method;
        try {
            method = ValidationServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            String resultJson = generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: 非法参数!");
            response.getWriter().println(resultJson);
            return;
        }

        try {
            method.invoke(this, request, response);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 执行登录逻辑,
     * request里面的参数不正确(包括参数为空, 参数类型不符合) 都输出{"action" : "1001", "msg" : "参数解析错误: account或password参数缺失!"}
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        String resultJson;
        if(account == null || password == null) {
            resultJson = generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: account或password参数缺失!");
            response.getWriter().println(resultJson);
            return;
        }

        Chatter chatter = userService.loginByAccount(account, password);
        if(chatter != null) {//chatter不为null说明验证通过
            //一个用户只能在一个地方登录
            if(((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).get(chatter.getId()) != null) {
                resultJson = generateResultJson(ValidationConstant.VALIDATION__LOGIN_AREA_LIMIT, "当前账号已登录或上次没有正确退出, 请10分钟后重试!");
                response.getWriter().println(resultJson);
                return;
            }

            //如果没有登录过, 或者系统没有缓存
            request.getSession().setAttribute("chatter", chatter);//存入session
            MessageNotifier messageNotifier = messageNotifierService.getLastMessageAccessData(chatter.getId());
            //初始化消息同步需要的类
            ((Map<Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).put(chatter.getId(), messageNotifier);

            resultJson = generateResultJson(ValidationConstant.VALIDATION__LOGIN_SUCCESS, "登录成功!");
            response.getWriter().println(resultJson);
        } else {
            resultJson = generateResultJson(ValidationConstant.VALIDATION__LOGIN_FAIL, "登录失败, 请检查账号和密码!");
            response.getWriter().println(resultJson);
        }
    }

    /**
     * 执行注册逻辑
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 获得返回数据的json格式字符串
     * @param action
     * @param message
     * @return
     */
    private String generateResultJson(int action, String message) {
        ServerResponse validationResponse = new ServerResponse(action, message);
        String resultJson = JsonUtils.toJson(validationResponse, ServerResponse.class);
        return resultJson;
    }
}

package cn.sourcecodes.chatterServer.servlet.validation;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.MessageService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import cn.sourcecodes.chatterServer.service.impl.MessageServiceImpl;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.utils.ServerResponseUtils;
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

    private ChatterService chatterService = ChatterServiceImpl.getInstance();
    private MessageService messageService = MessageServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检测是否带有method参数
        String methodName = request.getParameter("method");
        if(methodName == null) {
            String resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: method参数缺失!");
            response.getWriter().println(resultJson);
            return;
        }

        //检测参数值是否合法, login, register, logOut 三者之一为合法
        Method method;
        try {
            method = ValidationServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            String resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: 非法参数!");
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

        //检测是否带有password参数或account参数
        String resultJson;
        if(account == null || password == null) {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: account或password参数缺失!");
            response.getWriter().println(resultJson);
            return;
        }

        Chatter chatter = chatterService.loginByAccount(account, password);

        if(chatter != null) {//chatter不为null说明验证通过
            //一个用户只能在一个地方登录
            if(((Map<java.lang.Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).get(chatter.getId()) != null) {
                resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__LOGIN_AREA_LIMIT, "当前账号已登录或上次没有正确退出, 请10分钟后重试!");
                response.getWriter().println(resultJson);
                return;
            }

            //如果没有登录过, 或者系统没有缓存, 则执行登录, 且初始化需要的数据
            request.getSession().setAttribute("chatter", chatter);//存入session
            MessageNotifier messageNotifier = messageService.getLastMessageAccessData(chatter.getId());//初始化消息同步需要的类
            //将该类放入servletContext中
            ((Map<java.lang.Integer, MessageNotifier>) getServletContext().getAttribute("chatterNotifierMap")).put(chatter.getId(), messageNotifier);

            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__LOGIN_SUCCESS, "登录成功!");
            response.getWriter().println(resultJson);
        } else {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__LOGIN_FAIL, "登录失败, 请检查账号和密码!");
            response.getWriter().println(resultJson);
        }
    }

    /**
     * 执行注册逻辑
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        //检测是否带有password参数或account参数
        String resultJson = null;
        if(account == null || password == null) {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: account和password为必需字段!");
            response.getWriter().println(resultJson);
            return;
        }

        String nickName = request.getParameter("nickName");
        String gender = request.getParameter("gender");
        String region = request.getParameter("region");
        String phone = request.getParameter("phone");

        Chatter chatter = new Chatter();
        chatter.setAccount(account);
        chatter.setNickName(nickName);
        chatter.setGender(gender);
        chatter.setRegion(region);
        chatter.setPhone(phone);

        ChatterPrivateInfo chatterPrivateInfo = new ChatterPrivateInfo();
        chatterPrivateInfo.setPassword(password);

        int result = chatterService.register(chatter, chatterPrivateInfo);
        switch (result) {
            case ValidationConstant.VALIDATION__REGISTER_SUCCESS :
                resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__REGISTER_SUCCESS, "注册成功!");
                break;
            case ValidationConstant.VALIDATION__REGISTER_FAIL_UNKNOWN_REASON :
                resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__REGISTER_FAIL_UNKNOWN_REASON, "注册失败, 未知原因!");
                break;
            case ValidationConstant.VALIDATION__REGISTER_FAIL_ACCOUNT_ALREADY_EXIST :
                resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__REGISTER_FAIL_ACCOUNT_ALREADY_EXIST, "注册失败, 账号已存在!");
                break;
            case ValidationConstant.VALIDATION__REGISTER_FAIL_PHONE_ALREADY_EXIST :
                resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__REGISTER_FAIL_PHONE_ALREADY_EXIST, "注册失败, 手机号已存在!");
                break;
            default:
        }

        response.getWriter().println(resultJson);
    }

    /**
     * 执行下线逻辑
     * @param request
     * @param response
     */
    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__LOG_OUT_SUCCESS, "下线成功!");
        response.getWriter().println(resultJson);
    }

    /**
     * 检测账号是否可用
     * @param request
     * @param response
     * @throws IOException
     */
    private void checkAccountAvailable(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resultJson;

        String account = request.getParameter("account");
        if(account == null) {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__PARAMETER_RESOLVE_ERROR, "参数解析错误: account参数缺失!");
            response.getWriter().println(resultJson);
            return;
        }

        //如果账号已经存在, 说明该账号不可用
        if(chatterService.checkAccountExist(account)) {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__REGISTER_FAIL_ACCOUNT_ALREADY_EXIST, "该账号已存在, 请输入其他账号!");
        } else {
            resultJson = ServerResponseUtils.generateResultJson(ValidationConstant.VALIDATION__CHECK_ACCOUNT_AVAILABLE, "账号可用!");
        }

        response.getWriter().println(resultJson);
    }
}

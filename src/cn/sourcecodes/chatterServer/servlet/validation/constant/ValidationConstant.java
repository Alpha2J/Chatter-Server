package cn.sourcecodes.chatterServer.servlet.validation.constant;

/**
 *
 * 系统入口验证的常量类: 前面的 VALIDATION 开头部分表示的是模块, 项目中所有常量类统一这样使用
 * 注册: 成功-1, 失败-2
 * 登录: 成功-101, 失败-102
 *
 * 参数解析错误-1001
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class ValidationConstant {
    //注册
    public static final int VALIDATION__REGISTER_SUCCESS = 1;//注册成功
    public static final int VALIDATION__REGISTER_FAIL = 2;//注册失败

    //成功
    public static final int VALIDATION__LOGIN_SUCCESS = 101;//登录成功
    public static final int VALIDATION__LOGIN_FAIL = 102;//登录失败
//    public static final int

    //参数解析错误, 比如int类型的chatterId传了文本进来, 或者找不到参数
    public static final int VALIDATION__PARAMETER_RESOLVE_ERROR = 1001;

    //多地登录限制
    public static final int VALIDATION__LOGIN_AREA_LIMIT = 2002;

}

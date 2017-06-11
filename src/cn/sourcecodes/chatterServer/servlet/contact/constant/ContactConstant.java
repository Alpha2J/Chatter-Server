package cn.sourcecodes.chatterServer.servlet.contact.constant;

/**
 * Created by cn.sourcecodes on 2017/5/30.
 */
public class ContactConstant {

    //添加好友
    public static final int CONTACT__ADD_SUCCESS = 1;//成功
    public static final int CONTACT__ADD_FAIL = 2;//失败

    //查找好友
    public static final int CONTACT__FIND_COMPLETE_NULL = 11;//查找完成, 没结果
    public static final int CONTACT__FIND_COMPLETE_NOT_NULL = 12;//查找完成, 有结果

    //添加好友消息已发送
    public static final int CONTACT__ADD_MESSAGE_SEND = 21;

    //参数解析错误
    public static final int CONTACT__PARAMETER_RESOLVE_ERROR = 1001;

}

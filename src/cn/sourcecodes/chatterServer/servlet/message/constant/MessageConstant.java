package cn.sourcecodes.chatterServer.servlet.message.constant;

/**
 * 关于消息的一些常量在这里定义: 前面的 MESSAGE__ 开头部分表示的是模块, 项目中所有常量类统一这样使用
 * message type: 私聊消息-1, 群聊消息-2
 * content type: 纯文本-101, 图片-102, 语音-103, 小视频-104, 文件-105, 文本合图片-106, 红包-107
 *               语音聊天-1001, 视频聊天-1002
 *
 * 消息发送后返回给客户端的通知类型:
 * message handle type: 消息处理成功-10001, 消息处理失败-10002
 *
 * 内容更改消息: 群资料更改-88888
 * 系统消息-99999
 *
 *
 * 注意contentType 最大长度约定为6位
 */
public class MessageConstant {
    //消息类型
    public static final int MESSAGE__TYPE_PRIVATE = 1;//私聊
    public static final int MESSAGE__TYPE_GROUP = 2;//群聊

    //消息内容类型
    //这些类型都是即时消息
    public static final int MESSAGE__CONTENT_TYPE_PLAIN_TEXT = 101;//纯文本
    public static final int MESSAGE__CONTENT_TYPE_PICTURE = 102;//图片
    public static final int MESSAGE__CONTENT_TYPE_VOICE = 103;//语音
    public static final int MESSAGE__CONTENT_TYPE_VEDIO = 104;//视频
    public static final int MESSAGE__CONTENT_TYPE_FILE = 105;//文件
    public static final int MESSAGE__CONTENT_TYPE_TEXT_PICTURE = 106;//文本和图片
    public static final int MESSAGE__CONTENT_TYPE_RED_PACKET = 107;//红包
    //这些类型的消息是通告性消息, 客户端收到后获取content里面的byte, 解析获得通信方的地址
    public static final int MESSAGE__CONTENT_TYPE_VOICE_CHAT = 1001;//要进行语音聊天
    public static final int MESSAGE__CONTENT_TYPE_VEDIO_CHAT = 1002;//要进行视频聊天

    //消息响应类型
    public static final int MESSAGE__HANDLE_SUCCESS = 10001;//处理成功
    public static final int MESSAGE__HANDLE_FAIL = 10002;//处理失败
    public static final int MESSAGE__NEW_TRUE = 10003;//表示有新的消息
    public static final int MESSAGE__NEW_FALSE = 10004;//表示没有新的消息
    public static final int MESSAGE__PARAMETER_RESOLVE_ERROR = 10005;//表示参数解析出错
    public static final int MESSAGE__GET_SUCCESS_NOT_NULL = 10006;//获取消息成功, 且有新消息
    public static final int MESSAGE__GET_SUCCESS_NULL = 10007;//获取消息成功, 没有新消息

    //内容改变通知消息
    public static final int MESSAGE__GROUP_INFO_CHANGE = 80000;//表示群资料更改, 比如群公告

    //系统消息
    public static final int MESSAGE__CONTENT_TYPE_SYSTEM = 90000;//表示内容来自系统, 而不是某个用户


    //好友模块的消息
    public static final int MESSAGE__CONTACT_ADD_VALIDATION = 90001; //表示有人想添加我为好友
    public static final int MESSAGE__CONTACT_ADD_RESPONSE = 90002;//表示回应别人的添加请求的消息

    //消息模块程序代码验证常量
    public static final int MESSAGE__ADD_MESSAGE_FAIL_UNKNOWN_REASON = 2001;//添加消息失败, 未知原因
    public static final int MESSAGE__ADD_MESSAGE_FAIL_SYSTEM_ERROR = -1;
}

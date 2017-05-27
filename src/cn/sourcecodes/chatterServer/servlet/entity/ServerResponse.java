package cn.sourcecodes.chatterServer.servlet.entity;

/**
 * 表示服务器返回给客户端的消息的实体
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class ServerResponse {
    private int action;
    private String msg;

    public ServerResponse() {}

    public ServerResponse(int action, String msg) {
        this.action = action;
        this.msg = msg;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

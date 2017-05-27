package cn.sourcecodes.chatterServer.data;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class Data {
    private int id;
    private String uuid;
    private long sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }
}

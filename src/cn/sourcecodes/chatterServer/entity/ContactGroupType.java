package cn.sourcecodes.chatterServer.entity;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public class ContactGroupType {
    private int id;
    private int chatterId;
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatterId() {
        return chatterId;
    }

    public void setChatterId(int chatterId) {
        this.chatterId = chatterId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

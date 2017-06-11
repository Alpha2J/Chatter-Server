package cn.sourcecodes.chatterServer.servlet.message.entity;

/**
 * 消息通知器类, 对线程安全问题进行了比较多的考虑, 并做了处理, 不知道还会不会有 bug
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class MessageNotifier {
    private int id;
    private int chatterId;
    private int lastAccessMessageId;
    private int lastNewMessageId;
    private boolean hasNewMessage;


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

    public int getLastAccessMessageId() {
        return lastAccessMessageId;
    }

    public void setLastAccessMessageId(int lastAccessMessageId) {
        this.lastAccessMessageId = lastAccessMessageId;
    }

    public int getLastNewMessageId() {
        return lastNewMessageId;
    }

    public void setLastNewMessageId(int lastNewMessageId) {
        this.lastNewMessageId = lastNewMessageId;
    }

    private boolean isHasNewMessage() {
        return hasNewMessage;
    }

    private void setHasNewMessage(boolean hasNewMessage) {
        this.hasNewMessage = hasNewMessage;
    }

    /**
     * 如果最新的一个message的id大于上次访问的最大的消息id, 这时候才认为有新消息
     */
    private synchronized void setHasNewMessage() {
        if(lastNewMessageId > lastAccessMessageId) {
            setHasNewMessage(true);
        } else {
            setHasNewMessage(false);
        }
    }

    /**
     * 设置最后一个到达的消息:
     * 比较当前的最大的最新消息的id, 如果大于他才设置, 否则不设置
     *
     * 这里用synchronize的解释:
     * a 线程插入一条message消息到数据库,得到messageid, 在执行这个方法前cpu时间片被b线程抢占了,
     * 然后b线程插入数据, 获取id, 这个id比上面一个大, 这时候执行这个方法, 插入比较大的id, 然后线程
     * 接着执行这个方法, 色通知lastNewMessageId为比较小的id, 这样后面会有问题
     *
     * @param lastNewMessageId
     */
    public synchronized void setLastNewMessage(int lastNewMessageId) {
        if(lastNewMessageId > this.lastNewMessageId) {
            setLastNewMessageId(lastNewMessageId);
            setHasNewMessage();
        }
    }

    /**
     * lastAccessMessage 是上一次访问的消息中的最后一个, 就是上次中id最大的那个.
     * @param lastAccessMessage
     */
    public synchronized void setLastAccessMessage(int lastAccessMessage) {
        if(lastAccessMessage > getLastAccessMessageId()) {
            setLastAccessMessageId(lastAccessMessage);
            setHasNewMessage();
        }
    }

    /**
     * 查看是否有新的消息, 如果有, 获取上次访问的最后一个id
     * @return
     */
    public boolean checkHasNewMessage() {
        return isHasNewMessage();
    }
}

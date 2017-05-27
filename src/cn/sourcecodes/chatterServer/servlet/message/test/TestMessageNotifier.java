package cn.sourcecodes.chatterServer.servlet.message.test;

import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;

/**
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class TestMessageNotifier {

}

class ThreadA extends Thread {
    private MessageNotifier messageNotifier;

    public ThreadA(MessageNotifier messageNotifier) {
        this.messageNotifier = messageNotifier;
    }

    @Override
    public void run() {

    }
}

class ThreadB extends Thread {
    private MessageNotifier messageNotifier;

    public ThreadB(MessageNotifier messageNotifier) {
        this.messageNotifier = messageNotifier;
    }

    @Override
    public void run() {

    }
}
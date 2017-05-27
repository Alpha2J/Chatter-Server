package cn.sourcecodes.url;

/**
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadData threadData = new ThreadData();
        Thread threadA = new ThreadA(threadData);
        Thread threadB = new ThreadB(threadData);

        threadA.start();
        threadB.start();
    }
}


class ThreadA extends Thread {
    private ThreadData threadData;

     public ThreadA(ThreadData threadData) {
         this.threadData = threadData;
     }

    @Override
    public void run() {
        threadData.sayHello();
    }
}

class ThreadB extends Thread {
    private ThreadData threadData;

    public ThreadB(ThreadData threadData) {
        this.threadData = threadData;
    }

    @Override
    public void run() {
        threadData.sayGoodBye();
    }
}
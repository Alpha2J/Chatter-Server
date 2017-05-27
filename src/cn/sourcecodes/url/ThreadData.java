package cn.sourcecodes.url;

/**
 * Created by cn.sourcecodes on 2017/5/25.
 */
public class ThreadData {
    private String name;
    private boolean isTrue;

    public ThreadData() {}

    public ThreadData(String name, boolean isTrue) {
        this.name = name;
        this.isTrue = isTrue;
    }

    public synchronized void sayHello() {
        System.out.println("hello");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sayGoodBye() {
        System.out.println("good bye");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

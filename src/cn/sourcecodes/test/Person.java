package cn.sourcecodes.test;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class Person {
    private String name;
    private String nickname;

    public Person() {}

    public Person(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

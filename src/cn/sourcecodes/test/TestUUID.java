package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.util.AccountGenerator;
import cn.sourcecodes.chatterServer.util.UUIDGenerator;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class TestUUID {
    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(uuid.toString());
    }

    @Test
    public void testAccount() {
        System.out.println(AccountGenerator.generateRandomAccount());
    }

    @Test
    public void testUUIDGenerator() {
        System.out.println(UUIDGenerator.generateUUID().length());
    }

}

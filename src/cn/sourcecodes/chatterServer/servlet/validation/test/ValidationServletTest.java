package cn.sourcecodes.chatterServer.servlet.validation.test;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cn.sourcecodes on 2017/5/23.
 */
public class ValidationServletTest {
    @Test
    public void testLogin() {
        try {
            URL url = new URL("http://localhost:8080/chatter/validation");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

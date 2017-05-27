package cn.sourcecodes.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class URLConnectionTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        URLConnection uc = url.openConnection();
        InputStream inputStream = uc.getInputStream();

        int length = 0;
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        String resultStr = new String(buffer);
        System.out.println(resultStr);


    }
}

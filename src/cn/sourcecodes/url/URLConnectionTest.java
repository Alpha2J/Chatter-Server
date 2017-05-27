package cn.sourcecodes.url;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class URLConnectionTest {
    @Test
    public void testReadHeader() {
        try {
            URL url = new URL("http://localhost:8080/chatter/");
            URLConnection urlConnection = url.openConnection();
            System.out.println(urlConnection.getContentType());
            String cookie = urlConnection.getHeaderField("cookie");
            InputStream inputStream = urlConnection.getInputStream();
            System.out.println("cookie" + cookie);
            //urlConnection.setRequestProperty("Cookie", cookie);
            System.out.println(urlConnection.getDoInput());
           // System.out.println(urlConnection.getContentType());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

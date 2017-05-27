package cn.sourcecodes.url;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by cn.sourcecodes on 2017/5/23.
 */
public class URLTest {
    @Test
    public void firstTest() {
        try {
            URL url = new URL("jdbc:mysql://localhost:3306/chatter");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void secondTest() {
        try {
            URL url = new URL("http", "www.eff.org", "blueribbon.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //指定端口
    @Test
    public void thirdTest() {
        try {
            URL url = new URL("http", "fourier.dur.ac.uk", 8080, "/index");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //相对url
    @Test
    public void fourthTest() {
        try {
            URL url = new URL("http://www.baidu.com");
            URL url1 = new URL(url, "index.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testOpenStream() {
        try {
            URL url = new URL("http://www.baidu.com");
            try(InputStream inputStream = url.openStream()) {
                int c;
                while ((c = inputStream.read()) != -1) {
                    System.out.write(c);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testURLConnection() {
        try {
            URL url = new URL("http://www.baidu.com");

            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            int length;
            while((length = inputStream.read()) != -1) {
                System.out.write(length);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetContent() {
        //URL url = new URL("http://localhost:8080/chatter");
    }

    @Test
    public void testGetXXX() {
        try {
            URL url = new URL("http://localhost:8080/chatter?method=hello#abc");
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getDefaultPort());
            System.out.println(url.getFile());
            System.out.println(url.getPath());
            System.out.println(url.getRef());
            System.out.println(url.getQuery());
            System.out.println(url.getUserInfo());

            System.out.println(url.toExternalForm());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUri() {
        try {
            URI uri = new URI("hello:q");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testURIDecode() {

        try {
            URI uri = new URI("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E4%BD%A0%E5%A5%BD&rsv_pq=f8a05c280000770e&rsv_t=beff4fRo2T7%2FSE0mt7UjNYO969rUEDUBfgVLdj4X9Or5GBmpr%2FcRfIEJd5o&rqlang=cn&rsv_enter=1&rsv_sug3=7&rsv_sug1=5&rsv_sug7=100&rsv_sug2=0&inputT=1028&rsv_sug4=1706");
            System.out.println(uri.getRawQuery());
            System.out.println(uri.getQuery());
            System.out.println(uri.isAbsolute());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testURIEncode() {
        try {
            URI uri = new URI("http://www.baidu.com?method=你好");
            System.out.println(uri.getRawQuery());
            System.out.println(uri.getQuery());
            System.out.println(uri.isAbsolute());
            System.out.println(uri.getPath());
            System.out.println(uri.toASCIIString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL("http://www.baidu.com/newfile?method=你好");
            System.out.println(url.getPath());
            System.out.println(url.toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            String encode = URLEncoder.encode("%hello _/大家好啊", "UTF-8");
            System.out.println(encode);
            System.out.println(URLDecoder.decode(encode, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMethod() {
        try {
            URL url = new URL("http://localhost:8080/chatter/testUrl?chatterId=1");
            try(InputStream inputStream = url.openStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int c;
                while((c = inputStreamReader.read()) != -1) {
                    System.out.print((char)c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}

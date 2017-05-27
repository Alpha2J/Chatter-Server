package cn.sourcecodes.test;

import java.net.Authenticator;

/**
 * Created by cn.sourcecodes on 2017/5/23.
 */
public class SecureSourceViewer {
    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());

        for (int i = 0; i < 10; i++) {

        }
    }
}

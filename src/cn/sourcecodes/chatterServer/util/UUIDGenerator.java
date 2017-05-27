package cn.sourcecodes.chatterServer.util;

import java.util.UUID;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class UUIDGenerator {

    /**
     * 生成随机的uuid
     * @return
     */
    public static String generateUUID() {
        String rawUUID = UUID.randomUUID().toString();

        String uuid = rawUUID.substring(0, 8) +
                      rawUUID.substring(9, 13) +
                      rawUUID.substring(14, 18) +
                      rawUUID.substring(19, 23) +
                      rawUUID.substring(24);

        return uuid;
    }
}

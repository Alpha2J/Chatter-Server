package cn.sourcecodes.chatterServer.servlet.utils;

import cn.sourcecodes.chatterServer.servlet.entity.ServerResponse;
import cn.sourcecodes.chatterServer.util.JsonUtils;

/**
 * Created by cn.sourcecodes on 2017/5/30.
 */
public class ServerResponseUtils {

    /**
     * 获得返回数据的json格式字符串
     * @param action
     * @param message
     * @return
     */
    public static String generateResultJson(int action, String message) {
        ServerResponse validationResponse = new ServerResponse(action, message);
        String resultJson = JsonUtils.toJson(validationResponse, ServerResponse.class);
        return resultJson;
    }
}

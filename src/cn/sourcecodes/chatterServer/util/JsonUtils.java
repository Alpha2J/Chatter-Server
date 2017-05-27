package cn.sourcecodes.chatterServer.util;

import com.google.gson.Gson;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJson(Object object, Class<?> clazz) {
        String json = gson.toJson(object, clazz);
        return json;
    }
}

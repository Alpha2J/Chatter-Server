package cn.sourcecodes.chatterServer.dao.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/29.
 */
public class DaoUtils {

    /**
     * 将map中的key合成 a= ?, b= ? 这种形式后, 然后将key对应的值放到object[]中(位置和前面的key对应) 后, 用合成的String作为key, 值数组作为Value返回
     * @param fieldValueMap
     * @return
     */
    public static Map<String, Object[]> generateQueryCondition(Map<String, Object> fieldValueMap) {
        if(fieldValueMap == null || fieldValueMap.size() == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Object[] params = new Object[fieldValueMap.size()];

        int i = 0;
        for (Map.Entry<String, Object> entry : fieldValueMap.entrySet()) {
            if(stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" = ?");

            params[i] = entry.getValue();

            i++;
        }

        Map<String, Object[]> map = new HashMap<>();
        map.put(stringBuilder.toString(), params);

        return map;
    }
}

package cn.sourcecodes.chatterServer.service.fieldInitializer;

import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.impl.ChatterFieldInitializer;
import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.impl.ChatterGroupFieldInitializer;
import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.impl.MessageFieldInitializer;

/**
 * 获取各种各样的域初始化器, 比如用户域初始化器 UserFieldInitializer
 * Created by cn.sourcecodes on 2017/5/16.
 */
public class FieldInitializerFactory {
    public static FieldInitializer getInitializer(String initializerName) {
        switch (initializerName) {
            case "ChatterFieldInitializer" :
                return ChatterFieldInitializer.getInstance();
            case "MessageFieldInitializer" :
                return MessageFieldInitializer.getInstance();
            case "ChatterGroupFieldInitializer" :
                return ChatterGroupFieldInitializer.getInstance();
            default:
                return null;
        }
    }
}

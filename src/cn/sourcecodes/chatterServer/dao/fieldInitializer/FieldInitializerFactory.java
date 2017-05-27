package cn.sourcecodes.chatterServer.dao.fieldInitializer;

import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.impl.ChatterFieldInitializer;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.impl.ChatterPrivateFieldInitializer;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.impl.MessageFieldInitializer;

/**
 * 获取各种各样的域初始化器, 比如用户域初始化器 UserFieldInitializer
 * Created by cn.sourcecodes on 2017/5/16.
 */
public class FieldInitializerFactory {
    public static FieldInitializer getInitializer(String initializerName) {
        switch (initializerName) {
            case "ChatterFieldInitializer" :
                return ChatterFieldInitializer.getInstance();
            case "ChatterPrivateFieldInitializer" :
                return ChatterPrivateFieldInitializer.getInstance();
            case "MessageFieldInitializer" :
                return MessageFieldInitializer.getInstance();
            default:
                return null;
        }
    }
}

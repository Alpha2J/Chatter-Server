package cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.impl;

import cn.sourcecodes.chatterServer.dao.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ChatterPrivateFieldInitializer implements FieldInitializer<ChatterPrivate> {

    private static ChatterPrivateFieldInitializer instance;

    private ChatterPrivateFieldInitializer() {}

    public static ChatterPrivateFieldInitializer getInstance() {
        if(instance == null) {
            synchronized (ChatterPrivateFieldInitializer.class) {
                if(instance == null) {
                    instance = new ChatterPrivateFieldInitializer();
                    return instance;
                }
            }
        }

        return instance;
    }
    /**
     * 设置完成后可以确保所有字段都不为null, 存数据库时不会抛NullPointer异常
     * @param chatterPrivate
     * @return
     */
    @Override
    public ChatterPrivate initializeField(ChatterPrivate chatterPrivate) {
        if(chatterPrivate == null) {
            return null;
        }

        String password = chatterPrivate.getPassword();
        if(password == null) {
            password = "";
            chatterPrivate.setPassword(password);
        }

        return chatterPrivate;
    }
}

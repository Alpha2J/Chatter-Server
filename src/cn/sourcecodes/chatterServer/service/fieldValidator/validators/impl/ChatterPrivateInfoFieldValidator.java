package cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl;

import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.FieldValidator;

import java.util.regex.Pattern;

/**
 * Created by cn.sourcecodes on 2017/5/29.
 */
public class ChatterPrivateInfoFieldValidator implements FieldValidator<ChatterPrivateInfo> {
    private static ChatterPrivateInfoFieldValidator instance;

    private ChatterPrivateInfoFieldValidator() {}

    public static ChatterPrivateInfoFieldValidator getInstance() {
        if(instance == null) {
            synchronized (ChatterPrivateInfoFieldValidator.class) {
                if(instance == null) {
                    instance = new ChatterPrivateInfoFieldValidator();
                    return instance;
                }
            }
        }

        return instance;
    }

    @Override
    public boolean validate(ChatterPrivateInfo chatterPrivateInfo) {
        if(chatterPrivateInfo == null) {
            return false;
        }

        String password = chatterPrivateInfo.getPassword();
        if(password == null) {
            return false;
        }

        //大小写字母, 符号@#$%^*   6到40个字符
        if(!Pattern.matches("^[a-zA-Z0-9@#$%!\\^*]{6,40}$", password)) {
            return false;
        }

        return true;
    }
}

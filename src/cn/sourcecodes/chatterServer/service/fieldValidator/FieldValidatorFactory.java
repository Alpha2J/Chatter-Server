package cn.sourcecodes.chatterServer.service.fieldValidator;

import cn.sourcecodes.chatterServer.service.fieldValidator.validators.FieldValidator;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl.ChatterFieldValidator;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl.ChatterGroupFieldValidator;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl.ChatterPrivateInfoFieldValidator;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl.MessageFieldValidator;

/**
 * Created by cn.sourcecodes on 2017/5/28.
 */
public class FieldValidatorFactory {
    public static FieldValidator getValidator(String validatorName) {
        switch (validatorName) {
            case "ChatterFieldValidator" :
                return ChatterFieldValidator.getInstance();
            case "ChatterPrivateInfoFieldValidator" :
                return ChatterPrivateInfoFieldValidator.getInstance();
            case "MessageFieldValidator" :
                return MessageFieldValidator.getInstance();
            case "ChatterGroupFieldValidator" :
                return ChatterGroupFieldValidator.getInstance();
            default:
                return null;
        }
    }
}

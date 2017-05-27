package cn.sourcecodes.chatterServer.service.fieldValidator.validators;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public interface FieldValidator {
    /**
     * 输入的域是否合法
     * @param params
     * @return
     */
    boolean validate(Object...params);
}

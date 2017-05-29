package cn.sourcecodes.chatterServer.service.fieldValidator.validators;

/**
 * 检测对象各个域是否合法, Service层做数据验证, 合法的数据才存进数据库
 * Created by cn.sourcecodes on 2017/5/19.
 */
public interface FieldValidator<T> {
    /**
     * 输入的域是否合法
     * @param object
     * @return
     */
    boolean validate(T object);
}

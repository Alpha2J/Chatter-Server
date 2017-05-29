package cn.sourcecodes.chatterServer.service.fieldInitializer.initializers;

/**
 * 域初始化器, 因为在dao层中写的sql语句是每个域都当做一个预编译占位符的, 如果新建的类的域没有初始化, 那么有可能抛出
 * 空指针异常, 所以应该在初始化器中调用每个需要写入数据库的对象的对应初始化器将每个域初始化默认值, 尽管在数据库中已经
 * 设置default了, 但是那里设置跟这里没什么关系, 所以定义初始化器树
 * Created by cn.sourcecodes on 2017/5/16.
 */
public interface FieldInitializer<T> {
    /**
     * 用这个方法来初始化传入的对象中还没有值的字段
     * @param entity
     * @return
     */
    T initializeField(T entity);
}

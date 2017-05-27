package cn.sourcecodes.chatterServer.dao;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface BaseDao<T> {

    /**
     * 执行插入逻辑, 返回插入后的实体
     * @param sql
     * @param params
     * @return
     */
    long insert(String sql, Object...params);

    /**
     * 批量处理sql
     * @param sql
     * @param objects
     * @return 每条sql影响的行数
     */
    int[] batch(String sql, Object[][] objects);

    /**
     * 返回查询到的一个对象
     * @param sql
     * @param objects
     * @return
     */
    T query(String sql, Object... objects);

    /**
     * 返回查询到的一个对象的List
     * @param sql
     * @param objects
     * @return
     */
    List<T> queryForList(String sql, Object... objects);

    /**
     * 执行更新操作
     * @param sql
     * @param objects
     * @return 影响的行数
     */
    int update(String sql, Object... objects);
}

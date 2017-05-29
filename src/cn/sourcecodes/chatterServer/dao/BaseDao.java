package cn.sourcecodes.chatterServer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface BaseDao<T> {

    /**
     * 执行插入语句, 并返回插入后生成的id
     * @param sql
     * @param params
     * @return
     */
    long insert(String sql, Object...params) throws SQLException;

    /**
     * 执行一组插入语句, 并返回这组插入语句中每条语句插入后的id
     * @param sql
     * @param params
     * @return
     */
    List<Long> insertBatch(String sql, Object[][] params) throws SQLException;

    /**
     * 返回查询到的一个对象
     * @param sql
     * @param params
     * @return
     */
    T query(String sql, Object... params) throws SQLException;

    /**
     * 返回查询到的一个对象的List
     * @param sql
     * @param params
     * @return
     */
    List<T> queryForList(String sql, Object... params) throws SQLException;

    /**
     * 执行更新操作
     * @param sql
     * @param params
     * @return 影响的行数
     */
    int update(String sql, Object... params) throws SQLException;

    /**
     * 批量处理sql
     * @param sql
     * @param params
     * @return 每条sql影响的行数, 如果执行sql出异常, 回滚事务, 返回null
     */
    int[] batch(String sql, Object[][] params) throws SQLException;
}

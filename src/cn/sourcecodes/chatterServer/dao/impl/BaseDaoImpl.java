package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.BaseDao;
import cn.sourcecodes.chatterServer.util.DatabaseUtils;
import cn.sourcecodes.chatterServer.util.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/14.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    private QueryRunner queryRunner;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        queryRunner = new QueryRunner();
        clazz = (Class<T>) ReflectionUtils.getSuperGenericType(getClass());
    }


    @Override
    public long insert(String sql, Object... objects) {
        Connection connection = DatabaseUtils.getConnection();
        long addId = -1;

        try {
            //这里不能用integer的, resultSet.getObject(1) 返回的主键类型为long
            addId = queryRunner.insert(connection, sql, new ScalarHandler<Long>(), objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addId;
    }

    /**
     * 这里批量处理设置了事务, 如果sql执行过程中有异常产生, 那么回滚事务
     * @param sql
     * @param objects
     * @return
     */
    @Override
    public int[] batch(String sql, Object[][] objects) {
        Connection connection = DatabaseUtils.getConnection();
        int[] rows = null;

        try {
            connection.setAutoCommit(false);
            rows = queryRunner.batch(connection, sql, objects);
            connection.commit();//如果不设置提交, 那么无论是否出现异常, 都不会提交到数据库
        } catch (SQLException e) {
            try {
                //如果插入过程中有异常产生, 那么回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                //无论是否回滚事务, commit() 提交事务之后都要讲connection的autoCommit() 设置为true, 因为连接要放回到连接池中
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return rows;
    }

    @Override
    public T query(String sql, Object... objects) {
        Connection connection = DatabaseUtils.getConnection();

        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), objects);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("query 失败");
        }

        return null;
    }

    @Override
    public List<T> queryForList(String sql, Object... objects) {
        Connection connection = DatabaseUtils.getConnection();

        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), objects);
        } catch (SQLException e) {
            System.out.println("queryForList 失败");
        }

        return null;
    }

    @Override
    public int update(String sql, Object... objects) {
        Connection connection = DatabaseUtils.getConnection();

        int flag = 0;

        try {
            flag = queryRunner.update(connection, sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}

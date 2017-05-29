package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.util.DatabaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.*;

/**
 * Created by cn.sourcecodes on 2017/5/28.
 */
public class TestInsertBatch {
    public static void main(String[] args) throws SQLException {
//        QueryRunner queryRunner = new QueryRunner();
//        ArrayListHandler arrayListHandler = new ArrayListHandler();
//        ArrayHandler arrayHandler = new ArrayHandler();
        Connection connection = DatabaseUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into t(name) VALUES(?)", 1);

        preparedStatement.setObject(1, "batch1");
        preparedStatement.addBatch();

        preparedStatement.setObject(1, "batch2");
        preparedStatement.addBatch();

        preparedStatement.setObject(1, "batch3");
        preparedStatement.addBatch();

        preparedStatement.executeBatch();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        System.out.println("columnCount is " + columns);

        while(resultSet.next()) {
            for (int i = 0; i < columns; i++) {
                long num = (long) resultSet.getObject(i + 1);
                int a = (int) num;
                System.out.println("columnName : " + resultSetMetaData.getColumnName(i +1)
                        + "column value: " + resultSet.getObject(i+1));
            }
        }


    }
}

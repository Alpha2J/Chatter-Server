package cn.sourcecodes.chatterServer.servlet.test;

import cn.sourcecodes.chatterServer.util.DatabaseUtils;

import java.sql.*;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public class TestAutoId {
    public static void main(String[] args) throws SQLException {
        String sql = "INSERT INTO chatterNotifierMapping(" +
                "chatterId, lastAccessMessageId, lastNewMessageId) " +
                "VALUES(?, ?, ?)";

        Connection connection = DatabaseUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, 87);
        preparedStatement.setObject(2, 22);
        preparedStatement.setObject(3, 44);

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()) {
            Long integer = (Long) resultSet.getObject(1);
            System.out.println(integer);
        }


    }
}

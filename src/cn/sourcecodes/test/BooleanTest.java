package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by cn.sourcecodes on 2017/5/26.
 */
public class BooleanTest {
    public static void main(String[] arguments) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        PreparedStatement preparedStatement =
        connection.prepareStatement("insert into testBoolean(isTrue) VALUES(?)");
        preparedStatement.setObject(1, false);
        preparedStatement.executeUpdate();

    }
}

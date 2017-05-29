package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.impl.BaseDaoImpl;
import cn.sourcecodes.chatterServer.util.DatabaseUtils;
import org.junit.Test;

import java.sql.*;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class BaseDaoImplTest {

    @Test
    public void testBatch() throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into t(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, "hello");

        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        while(rs.next()) {
            //System.out.println(rs.getInt);
        }
    }
}

package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.util.DatabaseUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class DataUtil {
    @Test
    public void firstTest() {

    }

    public static void main(String[] args) throws SQLException {
//        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
//        try {
//            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/chatter");
//        comboPooledDataSource.setUser("root");
//        comboPooledDataSource.setPassword("123456");
//
//        comboPooledDataSource.setMinPoolSize(5);
//        comboPooledDataSource.setAcquireIncrement(5);
//        comboPooledDataSource.setMaxPoolSize(20);
//
//
//        try {
//            System.out.println(comboPooledDataSource.getConnection());
//            System.out.println(comboPooledDataSource.getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        DataSource dataSource = new ComboPooledDataSource("chatterServer");

        System.out.println(dataSource.getConnection());

        ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;
        System.out.println(comboPooledDataSource.getMaxPoolSize());
        System.out.println(comboPooledDataSource.getAcquireIncrement());
        System.out.println(comboPooledDataSource.getMinPoolSize());
        System.out.println(comboPooledDataSource.getInitialPoolSize());

    }

    @Test
    public void testDatabaseUtil() throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        System.out.println(connection);
        PreparedStatement preparedStatement = connection.prepareStatement("");
        DatabaseUtils.close(preparedStatement, connection);
        System.out.println(connection);
    }
}

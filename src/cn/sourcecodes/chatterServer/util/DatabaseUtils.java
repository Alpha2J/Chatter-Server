package cn.sourcecodes.chatterServer.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author sourcecodes.cn
 * @date 2016年10月7日下午7:14:42
 *
 */
public class DatabaseUtils {

	private static DataSource dataSource = null;

	static {
		dataSource = new ComboPooledDataSource("chatterServer");
	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接
	 * @param preparedStatement
	 * @param connection
	 */
	public static void close(PreparedStatement preparedStatement, Connection connection) {
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("关闭 preparedStatement 时出现异常!");
			}
		}
		
		if(connection != null) {
			try {
				//如果用的是连接池, 那么close 的话只是将连接归还数据库连接池, 而不是真正的关闭连接
				connection.close();
			} catch (SQLException e) {
				System.out.println("关闭 connection 连接时出异常!");
			}
		}
		
	}

	/**
	 * 关闭连接
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection
	 */
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("关闭 resultSet 时出异常!");
			}
		}
		
		close(preparedStatement, connection);
	}
}

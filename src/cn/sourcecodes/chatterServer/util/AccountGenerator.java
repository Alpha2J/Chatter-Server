package cn.sourcecodes.chatterServer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 * 这个类不是线程安全的, 以后再改变生成account的方法
 * Created by cn.sourcecodes on 2017/5/29.
 */
public class AccountGenerator {

    public static String generateRandomAccount() {
        String account = generateAccount();
        while(!checkIsAccountAvailable(account)) {
            try {
                //先睡一下, 不然程序运行太快, 获得的时间都一样, random的种子都一样, 生成的数字都一样, 不断循环, 很占资源
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account = generateAccount();
        }

        //如果数据库不存在那个账号, 那么将该账号存入, 返回
        recordAccount(account);

        return account;
    }

    private static String generateAccount() {
        Random random = new Random(System.currentTimeMillis());

        StringBuilder stringBuilder = new StringBuilder();

        //长度为8 到 11, 虽然数据库留的最大长度为20
        int length = random.nextInt(4) + 8;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }

    private static boolean recordAccount(String account) {
        String sql = "INSERT INTO accountCheck( account ) VALUES( ? )";

        Connection connection = DatabaseUtils.getConnection();

        boolean isSuccess = false;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            preparedStatement.setObject(1, account);
            preparedStatement.executeUpdate();
            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    private static boolean checkIsAccountAvailable(String account) {
        String sql = "SELECT account FROM accountCheck WHERE account = ?";

        Connection connection = DatabaseUtils.getConnection();
        boolean isAvailable = true;
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            //如果找到说明这个账号不可用
            if(resultSet.next()) {
                isAvailable = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAvailable;
    }
}

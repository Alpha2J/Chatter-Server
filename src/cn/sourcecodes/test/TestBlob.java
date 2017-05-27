package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.util.DatabaseUtils;

import java.io.*;
import java.sql.*;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class TestBlob {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = DatabaseUtils.getConnection();
//        String sql = "INSERT INTO byteTest(imgName, imgData) VALUES(?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setObject(1, "测试图片");
//        preparedStatement.setBinaryStream(2, new FileInputStream("d:\\test.gif"));
//
//        preparedStatement.executeUpdate();

        String sql = "SELECT id, imgName, imgData FROM byteTest WHERE id = 2";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Blob blob = resultSet.getBlob(3);
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\test.gif");
            InputStream inputStream = blob.getBinaryStream();

            int length = 0;
            byte[] buffer = new byte[1024];
            while((length = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }

        }

    }
}

//    CREATE TABLE byteTest (
//        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
//        imgName VARCHAR(50),
//    imgData MEDIUMBLOB #BLOB最多只能存16k数据, mediumblob最多16mb
//        );

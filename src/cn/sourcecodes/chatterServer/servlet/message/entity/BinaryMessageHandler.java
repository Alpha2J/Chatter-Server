package cn.sourcecodes.chatterServer.servlet.message.entity;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 处理文件, 图片, 视频, 语音这些类型的类, 主要用这个类将这些内容写到文件系统, 叫他Binary 主要是在数据库中用二进制类型存
 * Created by cn.sourcecodes on 2017/5/31.
 */
public class BinaryMessageHandler {

    /**
     *
     * @param part
     * @param messageUUID
     * @param absuloteLocation
     * @param relativeLocation
     * @return  如果content字段没有文件名, 默认用uuid作为文件名, 如果content字段没有数据, 返回null
     * @throws IOException
     */
    public static String saveAndReturnLocation(Part part, String messageUUID, String absuloteLocation, String relativeLocation) throws IOException {
        InputStream inputStream = part.getInputStream();
        if(inputStream.available() == 0) {
            return null;
        }

        String fileName = part.getSubmittedFileName();
        if(fileName == null || fileName.length() == 0) {
            fileName = messageUUID;
        } else {
            fileName = messageUUID + "-" + fileName;
        }

        //写出到文件系统
        File file = new File(absuloteLocation + relativeLocation + File.separator + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        int read;
        byte[] bytes = new byte[1024];
        while((read = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, read);
        }

        return relativeLocation + File.separator + fileName;
    }
}

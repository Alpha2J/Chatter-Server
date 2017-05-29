package cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.FieldValidator;

import java.util.regex.Pattern;


/**
 * service层在将数据写入数据库前调用这个类的方法进行验证, 验证成功才能写入数据库,
 * 否则不允许写入数据库:
 * 验证包括:
 * 1. 各字段是否为空验证(不进行验证会导致空指针异常)
 * 2. 长度验证
 * 各个字段的长度在cn.sourcecodes.chatterServer.servlet.validation包的"说明.txt"文件中列出
 *
 *
 * Created by cn.sourcecodes on 2017/5/28.
 */
public class ChatterFieldValidator implements FieldValidator<Chatter> {
    private static ChatterFieldValidator instance;

    private ChatterFieldValidator() {}

    public static ChatterFieldValidator getInstance() {
        if(instance == null) {
            synchronized (ChatterFieldValidator.class) {
                if(instance == null) {
                    instance = new ChatterFieldValidator();
                    return instance;
                }
            }
        }

        return instance;
    }

    @Override
    public boolean validate(Chatter chatter) {
        if(chatter == null) {
            return false;
        }

        String account = chatter.getAccount();
        if(account == null || account.length() >20) {
            return false;
        }

        //正则表达式判断account中字符是否合法(字母开头, 6到20个字符)
        if(!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]{5,19}$", account)) {
            return false;
        }

        String headImage = chatter.getHeadImage();
        if(headImage != null && headImage.length() > 255) {
            return false;
        }

        String nickName = chatter.getNickName();
        if(nickName != null && nickName.length() > 20) {
            return false;
        }

        String signature = chatter.getSignature();
        if(signature != null && signature.length() > 255) {
            return false;
        }

        String gender = chatter.getGender();
        if(gender != null && gender.length() > 10) {
            return false;
        }

        String region = chatter.getRegion();
        if(region != null && region.length() > 50) {
            return false;
        }

        String phone = chatter.getPhone();
        if(phone != null && phone.length() > 30) {
            return false;
        }

        return true;
    }
}

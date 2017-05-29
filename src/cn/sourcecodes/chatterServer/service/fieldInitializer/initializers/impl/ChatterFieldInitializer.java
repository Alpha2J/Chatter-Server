package cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.impl;

import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.entity.Chatter;

import java.util.Date;

/**
 * 初始化用户对象, 单例模式, 只能有产生一个对象
 * Created by cn.sourcecodes on 2017/5/16.
 */
public class ChatterFieldInitializer implements FieldInitializer<Chatter> {

    private static ChatterFieldInitializer instance;

    private ChatterFieldInitializer() {}

    public static ChatterFieldInitializer getInstance() {
        if(instance == null) {
            synchronized (ChatterFieldInitializer.class) {
                if(instance == null) {
                    instance = new ChatterFieldInitializer();
                    return instance;
                }
            }
        }

        return instance;
    }


    /**
     * 设置完成后可以确保所有字段都不为null, 存数据库时不会抛NullPointer异常
     * @param chatter
     * @return
     */
    @Override
    public Chatter initializeField(Chatter chatter) {
        if(chatter == null) {
            return null;
        }

        String account = chatter.getAccount();
        if(account == null) {
            account = "";
            chatter.setAccount(account);
        }

        String headImage = chatter.getHeadImage();
        if(headImage == null) {
            headImage = "/chatterData/default/image/default-head-image.png";
            chatter.setHeadImage(headImage);
        }

        String nickname = chatter.getNickName();
        if(nickname == null) {
            nickname = "chatter";
            chatter.setNickName(nickname);
        }

        String signature = chatter.getSignature();
        if(signature == null) {
            signature = "";
            chatter.setSignature(signature);
        }

        String gender = chatter.getGender();
        if(gender == null) {
            gender = "未知性别";
            chatter.setGender(gender);
        }

        String region = chatter.getRegion();
        if(region == null) {
            region = "未知地区";
            chatter.setRegion(region);
        }

        Date createTime = chatter.getCreateTime();
        if(createTime == null) {
            createTime = new Date();
            chatter.setCreateTime(createTime);
        }

        String phone = chatter.getPhone();
        if(phone == null) {
            phone = "";
            chatter.setPhone(phone);
        }

        return chatter;
    }
}

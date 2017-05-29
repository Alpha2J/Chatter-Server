package cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.impl;

import cn.sourcecodes.chatterServer.service.fieldInitializer.initializers.FieldInitializer;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.util.AccountGenerator;

import java.util.Date;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupFieldInitializer implements FieldInitializer<ChatterGroup> {
    private static ChatterGroupFieldInitializer instance;

    private ChatterGroupFieldInitializer() {}

    public static ChatterGroupFieldInitializer getInstance() {
        if(instance == null) {
            synchronized (ChatterGroupFieldInitializer.class) {
                if(instance == null) {
                    instance = new ChatterGroupFieldInitializer();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public ChatterGroup initializeField(ChatterGroup chatterGroup) {
        if(chatterGroup == null) {
            return null;
        }

        String account = chatterGroup.getAccount();
        if(account == null) {
            account = AccountGenerator.generateRandomAccount();
            chatterGroup.setAccount(account);
        }

        String headImage = chatterGroup.getHeadImage();
        if(headImage == null) {
            headImage = "/chatterData/default/image/default-head-image.png";
            chatterGroup.setHeadImage(headImage);
        }

        String groupName = chatterGroup.getGroupName();
        if(groupName == null) {
            groupName = "untitled group";
            chatterGroup.setGroupName(groupName);
        }

        String notice = chatterGroup.getNotice();
        if(notice == null) {
            notice = "";
            chatterGroup.setNotice(notice);
        }

        Date createTime = chatterGroup.getCreateTime();
        if(createTime == null) {
            createTime = new Date();
            chatterGroup.setCreateTime(createTime);
        }

        return chatterGroup;
    }
}

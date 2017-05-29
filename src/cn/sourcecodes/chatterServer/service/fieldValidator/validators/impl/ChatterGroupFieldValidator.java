package cn.sourcecodes.chatterServer.service.fieldValidator.validators.impl;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.service.fieldValidator.validators.FieldValidator;

/**
 * 检测必要字段
 * Created by cn.sourcecodes on 2017/5/29.
 */
public class ChatterGroupFieldValidator implements FieldValidator<ChatterGroup> {
    private static ChatterGroupFieldValidator instance;

    private ChatterGroupFieldValidator() {}

    public static ChatterGroupFieldValidator getInstance() {
        if(instance == null) {
            synchronized (ChatterGroupFieldValidator.class) {
                if(instance == null) {
                    instance = new ChatterGroupFieldValidator();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public boolean validate(ChatterGroup chatterGroup) {
        if (chatterGroup == null) {
            return false;
        }

        //群成员不能少于三个
        if(chatterGroup.getGroupMember() == null || chatterGroup.getGroupMember().size() < 3) {
            return false;
        }

        if(chatterGroup.getAccount() != null && chatterGroup.getAccount().length() > 20) {
            return false;
        }

        if(chatterGroup.getHeadImage() != null && chatterGroup.getHeadImage().length() > 50) {
            return false;
        }

        if (chatterGroup.getGroupName() != null && chatterGroup.getGroupName().length() > 50) {
            return false;
        }

        if(chatterGroup.getNotice() != null & chatterGroup.getNotice().length() > 255) {
            return false;
        }

        return true;
    }
}

package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface ChatterService {

    /**
     * @param chatter
     * @return   返回结果值, 如果小于0 注册失败, 失败原因未知, 如果为0 表示注册成功, 如果非0, 验证值, 1 账户重复, 2 phone重复等...
     */
    int register(Chatter chatter, ChatterPrivate chatterPrivate);  //这里返回int类型也可以用常量

    Chatter loginByAccount(String account, String password);

    Chatter loginByPhone(String phone, String password);

    /**
     * 根据id注销账户
     * @param id
     * @return
     */
    boolean deRegister(int id);

    /**
     * 获取chatter
     * @param id
     * @return
     */
    Chatter getChatter(int id);

    /*
    下面这些都是更新字段的
     */
    boolean resetPassword(int id, String newPassword);

    boolean resetHeadImage(int id, String newHeadImage);

    boolean resetNickName(int id, String newNickName);

    boolean resetSignature(int id, String signature);

    boolean resetGender(int id, String gender); //这里性别以后可以改为常量, 用来做判断

    boolean resetRegion(int id, String region); //地区先作为字符串存, 以后再拆分表映射

    boolean resetState(int id, int state);

    boolean resetPhone(int id, String phone);
}

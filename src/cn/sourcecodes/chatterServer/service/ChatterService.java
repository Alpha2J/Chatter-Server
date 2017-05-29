package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface ChatterService {

    /**
     * 注册
     * @param chatter
     * @param chatterPrivate
     * @return 返回注册后状态码, 成功或错误, 错误原因
     */
    int register(Chatter chatter, ChatterPrivateInfo chatterPrivate);  //这里返回int类型也可以用常量

    /**
     * 通过账号登录, 登录成功后返回封装该账号信息的对象
     * @param account
     * @param password
     * @return
     */
    Chatter loginByAccount(String account, String password);

    /**
     * 手机登录
     * @param phone
     * @param password
     * @return
     */
    Chatter loginByPhone(String phone, String password);

    /**
     * 获取chatter
     * @param id
     * @return
     */
    Chatter findChatterById(int id);

    /**
     * 查看账号是否已经被注册
     * @param account
     * @return
     */
    boolean checkAccountExist(String account);

    /**
     * 检测手机号是否存在
     * @param phone
     * @return
     */
    boolean checkPhoneExist(String phone);

    /**
     * 全部字段更新一遍(不包括账号, 手机号, 创建时间三个字段)
     * @param chatter
     * @return
     */
    boolean updateInfo(Chatter chatter);

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

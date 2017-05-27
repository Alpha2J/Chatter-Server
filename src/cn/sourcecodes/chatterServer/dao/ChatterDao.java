package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface ChatterDao {

    /**
     * 添加用户
     * @param chatter
     * @return
     */
    boolean addChatter(Chatter chatter);

    /**
     * 通过id删除用户
     * @param id
     * @return 删除成功返回true 反之false
     */
    boolean deleteChatter(int id);

    /**
     * 通过账号删除用户
     * @param account
     * @return
     */
    boolean deleteChatterByAccount(String account);

    /**
     * 通过手机号删除用户
     * @param phone
     * @return
     */
    boolean deleteChatterByPhone(String phone);

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    Chatter getChatter(int id);

    /**
     * 通过登录账号获取User
     * @param account
     * @return
     */
    Chatter getChatterByAccount(String account);

    /**
     * 通过手机号获取用户
     * @param phone
     * @return
     */
    Chatter getChatterByPhone(String phone);


    /**
     * 根据用户id更新字段(只更新一个字段)
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatter(int id, String field, Object value);

    /**
     * 根据id更新字段, 更新一组字段
     * @param id
     * @return
     */
    boolean updateChatter(int id, Map<String, Object> fieldValueMap);

    /**
     * 通过账号更改用户信息
     * @param account
     * @param chatter
     * @return
     */
    boolean updateChatterByAccount(String account, Chatter chatter);

    /**
     * 通过手机号码更新用户信息
     * @param phone
     * @param chatter
     * @return
     */
    boolean updateChatterByPhone(String phone, Chatter chatter);
}

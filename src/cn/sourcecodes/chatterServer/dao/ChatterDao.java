package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Chatter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public interface ChatterDao {

    /**
     * 添加用户
     * @param chatter
     * @return 刚添加的用户的id
     */
    long addChatter(Chatter chatter) throws SQLException;

    /**
     * 增加一组chatter(批量增加)
     * @param chatterList
     * @return
     */
    List<Long> addChatterList(List<Chatter> chatterList) throws SQLException;

    /**
     * 通过id删除用户
     * @param id
     * @return 删除成功返回true 反之false
     */
    boolean deleteChatterById(int id) throws SQLException;

    /**
     * 通过账号删除用户
     * @param account
     * @return
     */
    boolean deleteChatterByAccount(String account) throws SQLException;

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    Chatter getChatterById(int id) throws SQLException;

    /**
     * 通过登录账号获取Chatter
     * @param account
     * @return
     */
    Chatter getChatterByAccount(String account) throws SQLException;

    /**
     * 通过登录手机号获取Chatter
     * @param phone
     * @return
     * @throws SQLException
     */
    Chatter getChatterByPhone(String phone) throws SQLException;

    /**
     * 根据用户id更新字段(只更新一个字段)
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterById(int id, String field, Object value) throws SQLException;

    /**
     * 根据id更新字段, 更新一组字段
     * @param id
     * @return
     */
    boolean updateChatterById(int id, Map<String, Object> fieldValueMap) throws SQLException;
}

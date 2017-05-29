package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupDao {

    /**
     * 增加群组, 只增加群基本信息, 成员列表不在这操作
     * @param chatterGroup
     * @return
     */
    long addChatterGroup(ChatterGroup chatterGroup) throws SQLException;

    /**
     * 删除群组
     * @param id
     * @return
     */
    boolean deleteChatterGroupById(int id) throws SQLException;

    /**
     * 通过账号删除群组
     * @param account
     * @return
     */
    boolean deleteChatterGroupByAccount(String account) throws SQLException;

    /**
     * 获取群信息(群实体)
     * @param id
     * @return
     */
    ChatterGroup getChatterGroupById(int id) throws SQLException;

    /**
     * 获取群信息, 包括群成员(获取出来的群成员信息只有id字段)
     * @param account
     * @return
     */
    ChatterGroup getChatterGroupByAccount(String account) throws SQLException;

    /**
     * 更新群组的某一个字段信息
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterGroupById(int id, String field, Object value) throws SQLException;

    /**
     * 根据id更新字段, 更新一组字段
     * @param id
     * @return
     */
    boolean updateChatterGroupById(int id, Map<String, Object> fieldValueMap) throws SQLException;
}

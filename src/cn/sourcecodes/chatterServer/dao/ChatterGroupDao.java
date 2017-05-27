package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupDao {

    /**
     * 增加群组
     * @param chatterGroup
     * @return
     */
    boolean addChatterGroup(ChatterGroup chatterGroup);

    /**
     * 删除群组
     * @param id
     * @return
     */
    boolean deleteChatterGroup(int id);

    /**
     * 通过账号删除群组
     * @param account
     * @return
     */
    boolean deleteChatterGroupByAccount(String account);

    /**
     * 获取群信息, 包括群成员(获取出来的群成员信息只有id字段)
     * @param id
     * @return
     */
    ChatterGroup getChatterGroup(int id);

    /**
     * 通过id获取指定的群组信息, 不获取群成员
     * @param id
     * @return
     */
    ChatterGroup getBasicChatterGroup(int id);

    /**
     * 获取群信息, 包括群成员(获取出来的群成员信息只有id字段)
     * @param account
     * @return
     */
    ChatterGroup getChatterGroupByAccount(String account);

    /**
     * 通过id获取指定的群组信息, 不获取群成员
     * @param account
     * @return
     */
    ChatterGroup getBasicChatterGroupByAccount(String account);

    /**
     * 更新群组的某一个字段信息
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterGroup(int id, String field, Object value);

    /**
     * 根据id更新字段, 更新一组字段
     * @param id
     * @return
     */
    boolean updateChatterGroup(int id, Map<String, Object> fieldValueMap);

    /**
     * 通过账号更改群组信息
     * @param account
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterGroupByAccount(String account, String field, Object value);

    /**
     * 通过账号更改一组信息
     * @param account
     * @param fieldValueMap
     * @return
     */
    boolean updateChatterGroupByAccount(String account, Map<String, Object> fieldValueMap);
}

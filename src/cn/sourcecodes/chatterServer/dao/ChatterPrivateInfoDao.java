package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;

import java.sql.SQLException;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public interface ChatterPrivateInfoDao {

    /**
     * 增
     * @param chatterPrivateInfo
     * @return 增加的id
     */
    long addChatterPrivateInfo(ChatterPrivateInfo chatterPrivateInfo) throws SQLException;

    /**
     * 通过id删
     * @param id
     * @return
     */
    boolean deleteChatterPrivateInfoById(int id) throws SQLException;

    /**
     * 通过chatterId进行删
     * @param chatterId
     * @return
     */
    boolean deleteChatterPrivateInfoByChatterId(int chatterId) throws SQLException;

    /**
     * 通过chatterPrivate 的id来获取
     * @param id
     * @return
     */
    ChatterPrivateInfo getChatterPrivateInfoById(int id) throws SQLException;

    /**
     * 通过chatter 的id来获取对应的ChatterPrivate
     * @param chatterId
     * @return
     */
    ChatterPrivateInfo getChatterPrivateInfoByChatterId(int chatterId) throws SQLException;

    /**
     * 通过id进行修改
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterPrivateInfoById(int id, String field, Object value) throws SQLException;

    /**
     * 通过chatterId进行修改
     * @param chatterId
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterPrivateInfoByChatterId(int chatterId, String field, Object value) throws SQLException;

}

package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public interface ChatterPrivateDao {

    /**
     * 增
     * @param chatterPrivate
     * @return
     */
    boolean addChatterPrivate(ChatterPrivate chatterPrivate);

    /**
     * 通过id删
     * @param id
     * @return
     */
    boolean deleteChatterPrivateById(int id);

    /**
     * 通过chatterId进行删
     * @param chatterId
     * @return
     */
    boolean deleteChatterPrivateByChatterId(int chatterId);

    /**
     * 通过chatterPrivate 的id来获取
     * @param id
     * @return
     */
    ChatterPrivate getChatterPrivateById(int id);

    /**
     * 通过chatter 的id来获取对应的ChatterPrivate
     * @param chatterId
     * @return
     */
    ChatterPrivate getChatterPrivateByChatterId(int chatterId);

    /**
     * 通过id进行修改
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterPrivateById(int id, String field, Object value);

    /**
     * 通过chatterId进行修改
     * @param id
     * @param field
     * @param value
     * @return
     */
    boolean updateChatterPrivateByChatterId(int id, String field, Object value);

}

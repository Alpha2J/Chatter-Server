package cn.sourcecodes.chatterServer.dao;

import cn.sourcecodes.chatterServer.entity.Chatter;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupMappingDao {
    /**
     * 获取指定群组的所有chatter
     * @param chatterGroupId
     * @return
     */
    List<Chatter> getChatter(int chatterGroupId);
}

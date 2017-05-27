package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterGroupMappingDao;
import cn.sourcecodes.chatterServer.entity.Chatter;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupMappingDaoImpl extends BaseDaoImpl<Chatter> implements ChatterGroupMappingDao {

    @Override
    public List<Chatter> getChatter(int chatterGroupId) {
        String sql = "SELECT " +
                "c.id, c.account, c.headImage, c.nickname, c.signature, " +
                "c.gender, c.region, c.createTime, c.phone " +
                "FROM chatterGroupMapping AS cgm " +
                "LEFT JOIN chatter AS c " +
                "ON cgm.chatterId = c.id" +
                "WHERE chatterGroupId = ?";

        List<Chatter> chatterList = queryForList(sql, chatterGroupId);

        return chatterList;
    }
}

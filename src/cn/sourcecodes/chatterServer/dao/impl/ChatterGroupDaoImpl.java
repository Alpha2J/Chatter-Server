package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterGroupDao;
import cn.sourcecodes.chatterServer.dao.ChatterGroupMappingDao;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupDaoImpl extends BaseDaoImpl<ChatterGroup> implements ChatterGroupDao {
    private ChatterGroupMappingDao chatterGroupMappingDao = new ChatterGroupMappingDaoImpl();

    @Override
    public boolean addChatterGroup(ChatterGroup chatterGroup) {
        String sql = "INSERT INTO chatterGroup(" +
                "account, headImage," +
                "groupName, notice," +
                "createTime, state, groupOwnerId) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        //先将群组基本信息插入群组表
        int updateRow = update(sql,
                chatterGroup.getAccount(), chatterGroup.getHeadImage(),
                chatterGroup.getGroupName(), chatterGroup.getNotice(),
                chatterGroup.getCreateTime(), chatterGroup.getState(),
                chatterGroup.getGroupOwnerId()
                );

        if(updateRow ==0) {
            return false;
        }

        //插入群组表后用账户获得刚插入的群组的id
        int chatterGroupId = getChatterGroupByAccount(chatterGroup.getAccount()).getId();

        //开始插入群成员
        List<Chatter> memberList = chatterGroup.getGroupMember();

        String sql1 = "INSERT INTO chatterGroupMapping(chatterGroupId, chatterId) VALUES(?, ?)";
        Object[][] params = new Object[memberList.size()][2];

        for (int i = 0; i < memberList.size(); i++) {
            params[i][0] = chatterGroupId;
            params[i][1] = memberList.get(i).getId();
        }

        //这个数组是每条sql语句影响的行数
        int[] updatedRows = batch(sql1, params);

        for (int i = 0; i < updatedRows.length; i++) {
            if(updatedRows[i] != 1) {
                //这里直接return false有问题, 如果这个数值等于0(不会大于1), 那么说明有一条sql语句执行失败,
                // 那么需要回滚事务, BaseDao层没有写回滚事务, 以后再写, 先不管他
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean deleteChatterGroup(int id) {
        String sql = "DELETE FROM chatterGroup WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteChatterGroupByAccount(String account) {
        String sql = "DELETE FROM chatterGroup WHERE account = ?";

        int updatedRow = update(sql, account);

        return updatedRow != 0;
    }
//    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
//    account VARCHAR(20) NOT NULL,
//    headImage VARCHAR(60) NOT NULL DEFAULT 'D:\\chatter\\image',
//    groupName VARCHAR(30) NOT NULL DEFAULT '未命名群组',
//    notice VARCHAR(255),
//    createTime DATETIME NOT NULL DEFAULT NOW(),
//    state TINYINT DEFAULT 0 COMMENT '0表示正常使用, 1表示群被封掉了',
//    groupOwnerId INT NOT NULL COMMENT '群主的id'
    @Override
    public ChatterGroup getChatterGroup(int id) {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE id = ?";

        ChatterGroup chatterGroup = query(sql, id);

        //如果不存在这个群组, 那么就直接返回了, 群成员信息就不在获取
        if(chatterGroup == null) {
            return null;
        }

        List<Chatter> groupMembers = chatterGroupMappingDao.getChatter(chatterGroup.getId());

        chatterGroup.setGroupMember(groupMembers);

        return chatterGroup;
    }

    @Override
    public ChatterGroup getBasicChatterGroup(int id) {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE id = ?";

        ChatterGroup chatterGroup = query(sql, id);

        return chatterGroup;
    }

    @Override
    public ChatterGroup getChatterGroupByAccount(String account) {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE account = ?";

        ChatterGroup chatterGroup = query(sql, account);

        //如果不存在这个群组, 那么就直接返回了, 群成员信息就不在获取
        if(chatterGroup == null) {
            return null;
        }

        List<Chatter> groupMembers = chatterGroupMappingDao.getChatter(chatterGroup.getId());

        chatterGroup.setGroupMember(groupMembers);

        return chatterGroup;
    }

    @Override
    public ChatterGroup getBasicChatterGroupByAccount(String account) {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE account = ?";

        ChatterGroup chatterGroup = query(sql, account);

        return chatterGroup;
    }

    @Override
    public boolean updateChatterGroup(int id, String field, Object value) {
        return false;
    }

    @Override
    public boolean updateChatterGroup(int id, Map<String, Object> fieldValueMap) {
        return false;
    }

    @Override
    public boolean updateChatterGroupByAccount(String account, String field, Object value) {
        return false;
    }

    @Override
    public boolean updateChatterGroupByAccount(String account, Map<String, Object> fieldValueMap) {
        return false;
    }
}

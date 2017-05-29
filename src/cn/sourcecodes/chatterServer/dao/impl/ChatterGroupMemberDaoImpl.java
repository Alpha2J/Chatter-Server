package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterGroupMemberDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupMemberDaoImpl extends BaseDaoImpl<Integer> implements ChatterGroupMemberDao {

    private static ChatterGroupMemberDaoImpl instance;

    private ChatterGroupMemberDaoImpl() {}

    public static ChatterGroupMemberDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ChatterGroupMemberDaoImpl.class) {
                if(instance == null) {
                    instance = new ChatterGroupMemberDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public long addGroupMember(int groupId, int memberId) throws SQLException {
        String sql = "INSERT INTO chatterGroupMapping( " +
                "chatterGroupId, chatterId) " +
                "VALUES ( ?, ? )";

        return insert(sql, groupId, memberId);
    }

    @Override
    public List<Long> addGroupMembers(int groupId, List<Integer> memberIds) throws SQLException {
        String sql = "INSERT INTO chatterGroupMapping( " +
                "chatterGroupId, chatterId) " +
                "VALUES ( ?, ? )";

        Object[][] params = new Object[memberIds.size()][2];
        for (int i = 0; i < memberIds.size(); i++) {
            params[i][0] = groupId;
            params[i][1] = memberIds.get(i);
        }

        return insertBatch(sql, params);
    }

    @Override
    public boolean deleteGroupMember(int groupId, int memberId) throws SQLException {
        String sql = "DELETE FROM chatterGroupMapping WHERE chatterGroupId = ? AND chatterId = ?";

        return update(sql, groupId, memberId) != 0;
    }

    @Override
    public boolean deleteGroupMembers(int groupId, List<Integer> memberIds) throws SQLException {
        String sql = "DELETE FROM chatterGroupMapping WHERE chatterGroupId = ? AND chatterId = ?";

        Object[][] params = new Object[memberIds.size()][2];
        for (int i = 0; i < memberIds.size(); i++) {
            params[i][0] = groupId;
            params[i][1] = memberIds.get(i);
        }

        //如果有异常抛出, 那么回滚事务后那个数组还是为null, 所以这里判断是否为null
        return batch(sql, params) != null;
    }

    @Override
    public boolean deleteGroup(int groupId) throws SQLException {
        String sql = "DELETE FROM chatterGroupMapping WHERE chatterGroupId = ?";

        return update(sql, groupId) != 0;
    }

    @Override
    public List<Integer> getGroupMember(int groupId) throws SQLException {
        String sql = "SELECT chatterId FROM chatterGroupMapping WHERE chatterGroupId = ?";

        return queryForList(sql, groupId);
    }

    @Override
    public List<Integer> getOwnGroup(int memberId) throws SQLException {
        String sql = "SELECT chatterGroupId FROM chatterGroupMapping WHERE chatterId = ?";

        return queryForList(sql, memberId);
    }
}

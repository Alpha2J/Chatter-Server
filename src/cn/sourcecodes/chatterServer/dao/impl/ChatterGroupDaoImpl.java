package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterGroupDao;
import cn.sourcecodes.chatterServer.dao.utils.DaoUtils;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupDaoImpl extends BaseDaoImpl<ChatterGroup> implements ChatterGroupDao {

    private static ChatterGroupDaoImpl instance;

    private ChatterGroupDaoImpl() {}

    public static ChatterGroupDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ChatterGroupDaoImpl.class) {
                if(instance == null) {
                    instance = new ChatterGroupDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    @Override
    public long addChatterGroup(ChatterGroup chatterGroup) throws SQLException {
        String sql = "INSERT INTO chatterGroup( " +
                "account, headImage, " +
                "groupName, notice, " +
                "createTime, state, groupOwnerId) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        return insert(sql,
                chatterGroup.getAccount(), chatterGroup.getHeadImage(),
                chatterGroup.getGroupName(), chatterGroup.getNotice(),
                chatterGroup.getCreateTime(), chatterGroup.getState(),
                chatterGroup.getGroupOwnerId()
                );
    }

    @Override
    public boolean deleteChatterGroupById(int id) throws SQLException {
        String sql = "DELETE FROM chatterGroup WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteChatterGroupByAccount(String account) throws SQLException {
        String sql = "DELETE FROM chatterGroup WHERE account = ?";

        int updatedRow = update(sql, account);

        return updatedRow != 0;
    }

    @Override
    public ChatterGroup getChatterGroupById(int id) throws SQLException {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE id = ?";

        ChatterGroup chatterGroup = query(sql, id);

        return chatterGroup;
    }

    @Override
    public ChatterGroup getChatterGroupByAccount(String account) throws SQLException {
        String sql = "SELECT id, account, headImage, groupName, notice, createTime, state, groupOwnerId FROM chatterGroup WHERE account = ?";

        ChatterGroup chatterGroup = query(sql, account);

        return chatterGroup;
    }

    @Override
    public boolean updateChatterGroupById(int id, String field, Object value) throws SQLException {
        String sql = "UPDATE chatterGroup SET " + field + " = ? WHERE id = ?";

        return update(sql, value, id) != 0;
    }

    @Override
    public boolean updateChatterGroupById(int id, Map<String, Object> fieldValueMap) throws SQLException {
        String queryString = null;
        Object[] params = null;

        Map<String, Object[]> generateMap = DaoUtils.generateQueryCondition(fieldValueMap);
        if(generateMap == null) {
            return false;
        }

        for (Map.Entry<String, Object[]> map : generateMap.entrySet()) {
            queryString = map.getKey();
            params = map.getValue();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE chatterGroup SET ");
        stringBuilder.append(queryString);
        stringBuilder.append(" WHERE id = ?");

        String sql = stringBuilder.toString();

        return update(sql, params, id) != 0;
    }
}

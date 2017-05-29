package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.utils.DaoUtils;
import cn.sourcecodes.chatterServer.entity.Chatter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/16.
 */
public class ChatterDaoImpl extends BaseDaoImpl<Chatter> implements ChatterDao {

    private static ChatterDaoImpl instance;

    private ChatterDaoImpl() {}

    public static ChatterDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ChatterDaoImpl.class) {
                if(instance == null) {
                    instance = new ChatterDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }

    @Override
    public long addChatter(Chatter chatter) throws SQLException {
        String sql = "INSERT INTO chatter (" +
                " account, headImage," +
                " nickname, signature," +
                " gender, region," +
                " createTime, phone)" +
                " VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";

        return insert(sql,
                chatter.getAccount(), chatter.getHeadImage(),
                chatter.getNickName(), chatter.getSignature(),
                chatter.getGender(), chatter.getRegion(),
                chatter.getCreateTime(), chatter.getPhone()
        );
    }

    @Override
    public List<Long> addChatterList(List<Chatter> chatterList) throws SQLException {
        Object[][] params = new Object[chatterList.size()][8];

        for (int i = 0; i < chatterList.size(); i++) {
            params[i][0] = chatterList.get(i).getAccount();
            params[i][1] = chatterList.get(i).getHeadImage();
            params[i][2] = chatterList.get(i).getNickName();
            params[i][3] = chatterList.get(i).getSignature();
            params[i][4] = chatterList.get(i).getGender();
            params[i][5] = chatterList.get(i).getRegion();
            params[i][6] = chatterList.get(i).getCreateTime();
            params[i][7] = chatterList.get(i).getPhone();
        }

        String sql = "INSERT INTO chatter (" +
                " account, headImage," +
                " nickname, signature," +
                " gender, region," +
                " createTime, phone)" +
                " VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";

        return insertBatch(sql, params);
    }

    @Override
    public boolean deleteChatterById(int id) throws SQLException {
        String sql = "DELETE FROM chatter WHERE id = ?";

        int deletedRow = update(sql, id);

        return deletedRow != 0;
    }

    @Override
    public boolean deleteChatterByAccount(String account) throws SQLException {
        String sql = "DELETE FROM chatter WHERE account = ?";

        int deletedRow = update(sql, account);

        return deletedRow != 0;
    }

    @Override
    public Chatter getChatterById(int id) throws SQLException {
        String sql = "SELECT * FROM chatter WHERE id = ?";

        return query(sql, id);
    }

    @Override
    public Chatter getChatterByAccount(String account) throws SQLException {
        String sql = "SELECT * FROM chatter WHERE account = ?";

        return query(sql, account);
    }

    @Override
    public Chatter getChatterByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM chatter WHERE phone = ?";

        return query(sql, phone);
    }

    @Override
    public boolean updateChatterById(int id, String field, Object value) throws SQLException {
        String sql = "UPDATE chatter SET " + field + " = ? WHERE id = ?";

        return update(sql, value, id) != 0;
    }

    @Override
    public boolean updateChatterById(int id, Map<String, Object> fieldValueMap) throws SQLException {
        String queryString = null;
        Object[] params = null;

        Map<String, Object[]> generateMap = DaoUtils.generateQueryCondition(fieldValueMap);
        if(generateMap == null) {
            return false;
        }

        for (Map.Entry<String, Object[]> map :
                generateMap.entrySet()) {
            queryString = map.getKey();
            params = map.getValue();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE chatter SET ");
        stringBuilder.append(queryString);
        stringBuilder.append(" WHERE id = ?");

        String sql = stringBuilder.toString();


        return update(sql, params, id) != 0;
    }
}

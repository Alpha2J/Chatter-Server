package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

import java.util.List;
import java.util.Map;

/**
 * Created by cn.sourcecodes on 2017/5/16.
 */
public class ChatterDaoImpl extends BaseDaoImpl<Chatter> implements ChatterDao {

    @Override
    public boolean addChatter(Chatter chatter) {
        String sql = "INSERT INTO chatter(" +
                "account, headImage," +
                "nickname, signature," +
                "gender, region," +
                " createTime, phone)" +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";

        FieldInitializerFactory.getInitializer("ChatterFieldInitializer").initializeField(chatter);

        int updatedRow = update(sql,
                chatter.getAccount(), chatter.getHeadImage(),
                chatter.getNickName(), chatter.getSignature(),
                chatter.getGender(), chatter.getRegion(),
                chatter.getCreateTime(), chatter.getPhone()
        );

        return updatedRow != 0;
    }

    private int getChatterIdByAccount(String account) {
        String sql = "SELECT id FROM chatter WHERE account = ?";
        Chatter chatter = query(sql, account);
        return chatter.getId();
    }

    @Override
    public boolean deleteChatter(int id) {
        String sql = "DELETE FROM chatter WHERE id = ?";

        int deleteRow = update(sql, id);

        return deleteRow != 0;
    }

    @Override
    public boolean deleteChatterByAccount(String account) {
        String sql = "DELETE FROM chatter WHERE account = ?";

        int deleteRow = update(sql, account);

        return deleteRow != 0;
    }

    /**
     * 这里有个很严重的bug, 在UserFieldInitializer 域初始化器中将phone初始化为"" 空串的,
     * 所有没有设置phone域的用户的phone域都为"", 如果这里传入"" 这个参数, 那么数据库全部
     * 用户记录都被删掉了...数据库中又不能设置unique约束...
     * @param phone
     * @return
     */
    @Override
    public boolean deleteChatterByPhone(String phone) {
        String sql = "DELETE FROM chatter WHERE phone = ?";

        int deleteRow = update(sql, phone);

        return deleteRow != 0;
    }

    @Override
    public Chatter getChatter(int id) {
        String sql = "SELECT * FROM chatter WHERE id = ?";

        return query(sql, id);
    }

    @Override
    public Chatter getChatterByAccount(String account) {
        String sql = "SELECT * FROM chatter WHERE account = ?";

        return query(sql, account);
    }

    @Override
    public Chatter getChatterByPhone(String phone) {
        String sql = "SELECT * FROM chatter WHERE phone = ?";

        return query(sql, phone);
    }

    @Override
    public boolean updateChatter(int id, String field, Object value) {
        String sql = "UPDATE chatter SET " + field + " = ? WHERE id = ?";

        return update(sql, value, id) != 0;
    }

    @Override
    public boolean updateChatter(int id, Map<String, Object> fieldValueMap) {
        return false;
    }


    @Override
    public boolean updateChatterByAccount(String account, Chatter chatter) {
        return false;
    }

    @Override
    public boolean updateChatterByPhone(String phone, Chatter chatter) {
        return false;
    }
}

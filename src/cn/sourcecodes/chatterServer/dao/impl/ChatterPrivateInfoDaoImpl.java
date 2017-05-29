package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterPrivateInfoDao;
import cn.sourcecodes.chatterServer.service.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;

import java.sql.SQLException;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ChatterPrivateInfoDaoImpl extends BaseDaoImpl<ChatterPrivateInfo> implements ChatterPrivateInfoDao {

    private static ChatterPrivateInfoDaoImpl instance;

    private ChatterPrivateInfoDaoImpl() {}

    public static ChatterPrivateInfoDaoImpl getInstance() {
        if(instance == null) {
            synchronized (ChatterPrivateInfoDaoImpl.class) {
                if(instance == null) {
                    instance = new ChatterPrivateInfoDaoImpl();
                    return instance;
                }
            }
        }

        return instance;
    }

    @Override
    public long addChatterPrivateInfo(ChatterPrivateInfo chatterPrivateInfo) throws SQLException {
        String sql = "INSERT INTO chatterPrivateInfo(chatterId, password, state) VALUES ( ?, ?, ?)";

        FieldInitializerFactory.getInitializer("ChatterPrivateInfoFieldInitializer").initializeField(chatterPrivateInfo);

        return insert(sql,
                chatterPrivateInfo.getChatterId(), chatterPrivateInfo.getPassword(), chatterPrivateInfo.getState());
    }

    @Override
    public boolean deleteChatterPrivateInfoById(int id) throws SQLException {
        String sql = "DELETE FROM chatterPrivateInfo WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteChatterPrivateInfoByChatterId(int chatterId) throws SQLException {
        String sql = "DELETE FROM chatterPrivateInfo WHERE chatterId = ?";

        int updatedRow = update(sql, chatterId);

        return updatedRow != 0;
    }

    @Override
    public ChatterPrivateInfo getChatterPrivateInfoById(int id) throws SQLException {
        String sql = "SELECT * FROM chatterPrivateInfo WHERE id = ?";

        ChatterPrivateInfo chatterPrivate = query(sql, id);

        return chatterPrivate;
    }

    @Override
    public ChatterPrivateInfo getChatterPrivateInfoByChatterId(int chatterId) throws SQLException {
        String sql = "SELECT * FROM chatterPrivateInfo WHERE chatterId = ?";

        ChatterPrivateInfo chatterPrivateInfo = query(sql, chatterId);

        return chatterPrivateInfo;
    }

    @Override
    public boolean updateChatterPrivateInfoById(int id, String field, Object value) throws SQLException {
        String sql = "UPDATE chatterPrivateInfo SET " + field + " = ? WHERE id = ?";

        int updatedRow = update(sql, value, id);

        return updatedRow != 0;
    }

    @Override
    public boolean updateChatterPrivateInfoByChatterId(int chatterId, String field, Object value) throws SQLException {
        String sql = "UPDATE chatterPrivate SET " + field + " = ? WHERE chatterId = ?";

        int updatedRow = update(sql, value, chatterId);

        return updatedRow != 0;
    }
}

package cn.sourcecodes.chatterServer.dao.impl;

import cn.sourcecodes.chatterServer.dao.ChatterPrivateDao;
import cn.sourcecodes.chatterServer.dao.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ChatterPrivateDaoImpl extends BaseDaoImpl<ChatterPrivate> implements ChatterPrivateDao {

    @Override
    public boolean addChatterPrivate(ChatterPrivate chatterPrivate) {
        String sql = "INSERT INTO chatterPrivate(chatterId, password, state) VALUES ( ?, ?, ?)";

        FieldInitializerFactory.getInitializer("ChatterPrivateFieldInitializer").initializeField(chatterPrivate);

        int updatedRow = update(sql, chatterPrivate.getChatterId(), chatterPrivate.getPassword(), chatterPrivate.getState());

        return updatedRow != 0;
    }

    @Override
    public boolean deleteChatterPrivateById(int id) {
        String sql = "DELETE FROM chatterPrivate WHERE id = ?";

        int updatedRow = update(sql, id);

        return updatedRow != 0;
    }

    @Override
    public boolean deleteChatterPrivateByChatterId(int chatterId) {
        String sql = "DELETE FROM chatterPrivate WHERE chatterId = ?";

        int updatedRow = update(sql, chatterId);

        return updatedRow != 0;
    }

    @Override
    public ChatterPrivate getChatterPrivateById(int id) {
        String sql = "SELECT * FROM chatterPrivate WHERE id = ?";

        ChatterPrivate chatterPrivate = query(sql, id);

        return chatterPrivate;
    }

    @Override
    public ChatterPrivate getChatterPrivateByChatterId(int chatterId) {
        String sql = "SELECT * FROM chatterPrivate WHERE chatterId = ?";

        ChatterPrivate chatterPrivate = query(sql, chatterId);

        return chatterPrivate;
    }

    @Override
    public boolean updateChatterPrivateById(int id, String field, Object value) {
        String sql = "UPDATE chatterPrivate SET " + field + " = ? WHERE id = ?";

        int updatedRow = update(sql, value, id);

        return updatedRow != 0;
    }

    @Override
    public boolean updateChatterPrivateByChatterId(int chatterId, String field, Object value) {
        String sql = "UPDATE chatterPrivate SET " + field + " = ? WHERE chatterId = ?";

        int updatedRow = update(sql, value, chatterId);

        return updatedRow != 0;
    }
}

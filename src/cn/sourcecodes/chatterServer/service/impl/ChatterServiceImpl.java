package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.ChatterPrivateDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ChatterPrivateDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivate;
import cn.sourcecodes.chatterServer.service.ChatterService;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public class ChatterServiceImpl implements ChatterService {

    private ChatterDao chatterDao = new ChatterDaoImpl();
    private ChatterPrivateDao chatterPrivateDao = new ChatterPrivateDaoImpl();

    //返回结果值, 如果为0 表示注册成功, 如果非0, 验证值, 1 账户重复, 2 phone重复等...
    @Override
    public int register(Chatter chatter, ChatterPrivate chatterPrivate) {
        String account = chatter.getAccount();
        String phone = chatter.getPhone();
        if(chatterDao.getChatterByAccount(account) != null) {
            return 1;
        } else if(chatterDao.getChatterByPhone(phone) != null) {
            return 2;
        }

        boolean isSuccess = chatterDao.addChatter(chatter);
        if(!isSuccess) {
            return -1;
        }


        int id = chatterDao.getChatterByAccount(account).getId();
        chatterPrivate.setChatterId(id);

        return chatterPrivateDao.addChatterPrivate(chatterPrivate) == true ? 0 : -1;
    }

    @Override
    public Chatter loginByAccount(String account, String password) {
        Chatter chatter = chatterDao.getChatterByAccount(account);
        if(chatter == null) {
            return null;
        } else {
            int chatterId = chatter.getId();
            ChatterPrivate chatterPrivate = chatterPrivateDao.getChatterPrivateByChatterId(chatterId);
            return chatterPrivate.getPassword().equals(password) ? chatter : null;
        }
    }

    @Override
    public Chatter loginByPhone(String phone, String password) {
        Chatter chatter = chatterDao.getChatterByPhone(phone);
        if(chatter == null) {
            return null;
        } else {
            int chatterId = chatter.getId();
            ChatterPrivate chatterPrivate = chatterPrivateDao.getChatterPrivateByChatterId(chatterId);
            return chatterPrivate.getPassword().equals(password) ? chatter : null;
        }
    }

    @Override
    public boolean deRegister(int id) {
        Chatter chatter = chatterDao.getChatter(id);
        if(chatter == null) {
            return false;
        } else {
            int chatterId = chatter.getId();

            return chatterDao.deleteChatter(id) && chatterPrivateDao.deleteChatterPrivateByChatterId(chatterId);
        }
    }

    @Override
    public Chatter getChatter(int id) {
        return chatterDao.getChatter(id);
    }

    @Override
    public boolean resetPassword(int id, String newPassword) {
        chatterPrivateDao.updateChatterPrivateByChatterId(id, "password", newPassword);
        return false;
    }

    @Override
    public boolean resetHeadImage(int id, String newHeadImage) {
        return false;
    }

    @Override
    public boolean resetNickName(int id, String newNickName) {
        return false;
    }

    @Override
    public boolean resetSignature(int id, String signature) {
        return false;
    }

    @Override
    public boolean resetGender(int id, String gender) {
        return false;
    }

    @Override
    public boolean resetRegion(int id, String region) {
        return false;
    }

    @Override
    public boolean resetState(int id, int state) {
        return false;
    }

    @Override
    public boolean resetPhone(int id, String phone) {
        return false;
    }
}

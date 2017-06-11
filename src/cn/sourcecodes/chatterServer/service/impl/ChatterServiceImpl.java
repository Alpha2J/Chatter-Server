package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.ChatterPrivateInfoDao;
import cn.sourcecodes.chatterServer.dao.MessageNotifierDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ChatterPrivateInfoDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.MessageNotifierDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.service.fieldValidator.FieldValidatorFactory;
import cn.sourcecodes.chatterServer.servlet.message.entity.MessageNotifier;
import cn.sourcecodes.chatterServer.servlet.validation.constant.ValidationConstant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by cn.sourcecodes on 2017/5/11.
 */
public class ChatterServiceImpl implements ChatterService {

    private static ChatterService instance;

    private ChatterServiceImpl() {}

    public static ChatterService getInstance() {
        if(instance == null) {
            synchronized (ChatterServiceImpl.class) {
                if(instance == null) {
                    instance = new ChatterServiceImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    private ChatterDao chatterDao = ChatterDaoImpl.getInstance();
    private ChatterPrivateInfoDao chatterPrivateInfoDao = ChatterPrivateInfoDaoImpl.getInstance();
    private MessageNotifierDao messageNotifierDao = MessageNotifierDaoImpl.getInstance();

    /**
     * 注册的时候只需要输入基本信息, 只检测密码是否为空
     * @param chatter
     * @param chatterPrivateInfo
     * @return
     */
    @Override
    public int register(Chatter chatter, ChatterPrivateInfo chatterPrivateInfo) {
        //程序错误
        if(chatter == null || chatterPrivateInfo == null) {
            return -1;
        }

        //如果域检测不通过, 返回未知错误
        if(!FieldValidatorFactory.getValidator("ChatterFieldValidator").validate(chatter)) {
            return ValidationConstant.VALIDATION__REGISTER_FAIL_UNKNOWN_REASON;
        }
        //检测不通过, 说明密码域不符合规定, 返回未知错误
        if(!FieldValidatorFactory.getValidator("ChatterPrivateInfoFieldValidator").validate(chatterPrivateInfo)) {
            return ValidationConstant.VALIDATION__REGISTER_FAIL_UNKNOWN_REASON;
        }

        //检测账号是否已经存在
        if(checkAccountExist(chatter.getAccount())) {
            return ValidationConstant.VALIDATION__REGISTER_FAIL_ACCOUNT_ALREADY_EXIST;
        }

        //如果注册时填写了手机号, 检测手机号是否已经存在
        if(chatter.getPhone() != null) {
            if(checkPhoneExist(chatter.getPhone())) {
                return ValidationConstant.VALIDATION__REGISTER_FAIL_PHONE_ALREADY_EXIST;
            }
        }

        //检测通过, 初始化非必须域, 保证不会在插入时抛空指针异常
        FieldInitializerFactory.getInitializer("ChatterFieldInitializer").initializeField(chatter);

        boolean isSuccess = false;
        try {
            //这块其实还可以做事务的, 万一chatter插入了, chatterPrivateInfo插入失败呢? 那刚插入的chatter就没有对应的密码等私有字段了.
            long addedId = chatterDao.addChatter(chatter);
            chatterPrivateInfo.setChatterId((int)addedId);
            chatterPrivateInfoDao.addChatterPrivateInfo(chatterPrivateInfo);

            //注册成功后要往消息同步表写入一条记录, 否则以后消息无法同步
            MessageNotifier messageNotifier = new MessageNotifier();
            messageNotifier.setChatterId((int) addedId);

            messageNotifierDao.addMessageNotifier(messageNotifier);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //如果执行的时候抛异常, 说明插入失败, 返回未知错误
        return isSuccess ? ValidationConstant.VALIDATION__REGISTER_SUCCESS : ValidationConstant.VALIDATION__REGISTER_FAIL_UNKNOWN_REASON;
    }

    @Override
    public Chatter loginByAccount(String account, String password) {
        try {
            Chatter chatter = chatterDao.getChatterByAccount(account);
            if(chatter == null) {
                return null;
            }

            int chatterId = chatter.getId();
            ChatterPrivateInfo chatterPrivateInfo = chatterPrivateInfoDao.getChatterPrivateInfoByChatterId(chatterId);
            if(chatterPrivateInfo == null) {
                return null;
            }

            //如果密码符合, 返回chatter
            if(chatterPrivateInfo.getPassword().equals(password)) {
                return chatter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //如果执行出异常, 返回null
        return null;
    }

    @Override
    public Chatter loginByPhone(String phone, String password) {
        try {
            //在ChatterFieldInitializer里面, 如果phone为null, 那么设置为""空串, 所有注册时没有设置phone的用户的phone字段都为""
            //所有是"" 的话, 返回null, 不做处理
            if(phone.equals("")) {
                return null;
            }

            Chatter chatter = chatterDao.getChatterByPhone(phone);
            if(chatter == null) {
                return null;
            }

            int chatterId = chatter.getId();
            ChatterPrivateInfo chatterPrivateInfo = chatterPrivateInfoDao.getChatterPrivateInfoById(chatterId);
            if(chatterPrivateInfo == null) {
                return null;
            }

            //如果密码符合, 返回chatter
            if(chatterPrivateInfo.getPassword().equals(password)) {
                return chatter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //如果执行出异常, 返回null
        return null;
    }

    @Override
    public Chatter findChatterById(int id) {
        Chatter chatter = null;

        try {
            chatter = chatterDao.getChatterById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatter;
    }

    //只根据账号和手机号来搜
    @Override
    public List<Chatter> wideFindChatter(String chatterStr) {
        //为空或长度太长, 返回空
        if(chatterStr == null || chatterStr.length() > 255) {
            return null;
        }

        List<Chatter> chatterList = null;

        try {
            Chatter chatter = chatterDao.getChatterByAccount(chatterStr);
            Chatter chatter1 = chatterDao.getChatterByPhone(chatterStr);
            if(chatter != null || chatter1 != null) {
                chatterList = new ArrayList<>();
                if(chatter != null) {
                    chatterList.add(chatter);
                }
                if(chatter1 != null) {
                    chatterList.add(chatter1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatterList;
    }

    @Override
    public boolean checkAccountExist(String account) {
        if(account == null || account.length() > 20) {
            return false;
        }

        Chatter chatter = null;

        try {
            chatter = chatterDao.getChatterByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //存在返回true, 不存在返回false
        if(chatter == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        Chatter chatter = null;

        try {
            chatter = chatterDao.getChatterByPhone(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //存在返回true, 不存在返回false
        if(chatter == null) {
            return false;
        } else {
            return true;
        }
    }

    //这个方法重点检查
    @Override
    public boolean updateInfo(Chatter chatter) {
        //域没检查通过, 返回false
        if(!FieldValidatorFactory.getValidator("ChatterFieldValidator").validate(chatter)) {
            return false;
        }

        Map<String, Object> fieldValueMap = new HashMap<>();
        fieldValueMap.put("headImage", chatter.getHeadImage());
        fieldValueMap.put("nickName", chatter.getNickName());
        fieldValueMap.put("signature", chatter.getSignature());
        fieldValueMap.put("gender", chatter.getSignature());
        fieldValueMap.put("region", chatter.getSignature());

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(chatter.getId(), fieldValueMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetPassword(int id, String password) {
        if(password == null) {
            return false;
        }

        //大小写字母, 符号@#$%^*   6到40个字符
        if(!Pattern.matches("^[a-zA-Z0-9@#$%!\\^*]{6,40}$", password)) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterPrivateInfoDao.updateChatterPrivateInfoById(id, "password", password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetHeadImage(int id, String headImage) {
        if(headImage == null || headImage.length() > 255) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "headImage", headImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetNickName(int id, String nickName) {
        if(nickName == null || nickName.length() > 20) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "nickName", nickName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetSignature(int id, String signature) {
        if(signature == null || signature.length() > 255) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "signature", signature);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetGender(int id, String gender) {
        if(gender == null || gender.length() > 10) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "gender", gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetRegion(int id, String region) {
        if(region == null || region.length() > 50) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "region", region);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetState(int id, int state) {
        return false;
    }

    @Override
    public boolean resetPhone(int id, String phone) {
        if(phone == null || phone.length() > 30) {
            return false;
        }

        //如果该手机号已经存在, 不允许更改
        if(checkPhoneExist(phone)) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterDao.updateChatterById(id, "phone", phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}

package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.dao.ChatterGroupDao;
import cn.sourcecodes.chatterServer.dao.ChatterGroupMemberDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterGroupDaoImpl;
import cn.sourcecodes.chatterServer.dao.impl.ChatterGroupMemberDaoImpl;
import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.service.ChatterGroupService;
import cn.sourcecodes.chatterServer.service.fieldInitializer.FieldInitializerFactory;
import cn.sourcecodes.chatterServer.service.fieldValidator.FieldValidatorFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupServiceImpl implements ChatterGroupService {

    private static ChatterGroupServiceImpl instance;

    private ChatterGroupServiceImpl() {}

    public static ChatterGroupServiceImpl getInstance() {
        if(instance == null) {
            synchronized (ChatterGroupServiceImpl.class) {
                if(instance == null) {
                    instance = new ChatterGroupServiceImpl();
                    return instance;
                }
            }
        }

        return instance;
    }


    private ChatterGroupDao chatterGroupDao = ChatterGroupDaoImpl.getInstance();
    private ChatterGroupMemberDao chatterGroupMemberDao = ChatterGroupMemberDaoImpl.getInstance();

    @Override
    public boolean createChatterGroup(ChatterGroup chatterGroup) {
        if(chatterGroup == null) {
            return false;
        }

        //字段验证不通过
        if (!FieldValidatorFactory.getValidator("ChatterGroupFieldValidator").validate(chatterGroup)) {
            return false;
        }

        FieldInitializerFactory.getInitializer("ChatterGroupFieldInitializer").initializeField(chatterGroup);

        boolean isSuccess = false;
        try {
            long addId = chatterGroupDao.addChatterGroup(chatterGroup);
            chatterGroupMemberDao.addGroupMembers((int)addId, chatterGroup.getGroupMember());
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean addGroupMember(int groupId, int memberId) {
        boolean isSuccess = false;
        try {
            chatterGroupMemberDao.addGroupMember(groupId, memberId);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean addGroupMembers(int groupId, List<Integer> memberIds) {
        boolean isSuccess = false;
        try {
            chatterGroupMemberDao.addGroupMembers(groupId, memberIds);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteChatterGroup(ChatterGroup chatterGroup) {
        if(chatterGroup == null) {
            return false;
        }

        boolean isSuccess = false;
        try {
            chatterGroupDao.deleteChatterGroupById(chatterGroup.getId());
            chatterGroupMemberDao.deleteGroup(chatterGroup.getId());
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteGroupMember(int groupId, int memberId) {
        boolean isSuccess = false;

        try {
            isSuccess = chatterGroupMemberDao.deleteGroupMember(groupId, memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteGroupMembers(int groupId, List<Integer> memberIds) {
        boolean isSuccess = false;

        try {
            isSuccess = chatterGroupMemberDao.deleteGroupMembers(groupId, memberIds);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public ChatterGroup getBasicChatterGroup(int chatterGroupId) {
        ChatterGroup chatterGroup = null;

        try {
            chatterGroup = chatterGroupDao.getChatterGroupById(chatterGroupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatterGroup;
    }

    @Override
    public ChatterGroup getChatterGroup(int chatterGroupId) {
        ChatterGroup chatterGroup = null;

        try {
            chatterGroup = chatterGroupDao.getChatterGroupById(chatterGroupId);

            List<Integer> members = chatterGroupMemberDao.getGroupMember(chatterGroupId);
            if(members == null) {
                return null;
            }

            chatterGroup.setGroupMember(members);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatterGroup;
    }

    @Override
    public boolean resetHeadImage(ChatterGroup chatterGroup, String headImage) {
        if(chatterGroup == null) {
            return false;
        }

        if(!validate(headImage, 50)) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterGroupDao.updateChatterGroupById(chatterGroup.getId(), "headImage", headImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetGroupName(ChatterGroup chatterGroup, String groupName) {
        if(chatterGroup == null) {
            return false;
        }

        if(!validate(groupName, 50)) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterGroupDao.updateChatterGroupById(chatterGroup.getId(), "groupName", groupName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetNotice(ChatterGroup chatterGroup, String notice) {
        if(chatterGroup == null) {
            return false;
        }

        if(!validate(notice, 255)) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterGroupDao.updateChatterGroupById(chatterGroup.getId(), "notice", notice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetState(ChatterGroup chatterGroup, int state) {
        if(chatterGroup == null) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterGroupDao.updateChatterGroupById(chatterGroup.getId(), "state", state);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public boolean resetOwner(ChatterGroup chatterGroup, int executeChatterId, int newOwnerId) {
        if(chatterGroup == null) {
            return false;
        }

        //只有群主才能执行更换群主的命令
        if(chatterGroup.getGroupOwnerId() != executeChatterId) {
            return false;
        }

        boolean isSuccess = false;
        try {
            isSuccess = chatterGroupDao.updateChatterGroupById(chatterGroup.getId(), "groupOwnerId", newOwnerId);
            chatterGroup.setGroupOwnerId(newOwnerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    @Override
    public List<ChatterGroup> getOwnChatterGroup(int chatterId) {
        List<ChatterGroup> chatterGroupList = null;
        try {
            List<Integer> groupIdList = chatterGroupMemberDao.getOwnGroup(chatterId);

            //如果这个人没有群, 返回null
            if(groupIdList == null) {
                return chatterGroupList;
            }

            chatterGroupList = new ArrayList<>();
            for (int i = 0; i < groupIdList.size(); i++) {
                chatterGroupList.add(getChatterGroup(groupIdList.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatterGroupList;
    }

    private boolean validate(String field, int maxLength) {
        if(field == null || field.length() > maxLength) {
            return false;
        }

        return true;
    }

}

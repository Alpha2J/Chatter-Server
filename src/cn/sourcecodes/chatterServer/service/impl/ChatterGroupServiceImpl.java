package cn.sourcecodes.chatterServer.service.impl;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;
import cn.sourcecodes.chatterServer.service.ChatterGroupService;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public class ChatterGroupServiceImpl implements ChatterGroupService {
    @Override
    public boolean addChatterGroup(ChatterGroup chatterGroup) {
        return false;
    }

    @Override
    public boolean deleteChatterGroup(ChatterGroup chatterGroup) {
        return false;
    }

    @Override
    public ChatterGroup getBasicChatterGroup(int chatterGroupId) {
        return null;
    }

    @Override
    public ChatterGroup getChatterGroup(int chatterGroupId) {
        return null;
    }

    @Override
    public boolean resetHeadImage(ChatterGroup chatterGroup, int executeChatterId) {
        return false;
    }

    @Override
    public boolean resetGroupName(ChatterGroup chatterGroup, int executeChatterId) {
        return false;
    }

    @Override
    public boolean resetNotice(ChatterGroup chatterGroup, int executeChatterId) {
        return false;
    }

    @Override
    public boolean resetState(ChatterGroup chatterGroup, int executeChatterId) {
        return false;
    }

    @Override
    public boolean resetOwner(ChatterGroup chatterGroup, int executeChatterId, int newOwnerId) {
        return false;
    }

    @Override
    public boolean addGroupMember(ChatterGroup chatterGroup, int chatterId) {
        return false;
    }

    @Override
    public boolean deleteGroupMember(ChatterGroup chatterGroup, int chatterId) {
        return false;
    }

    @Override
    public List<ChatterGroup> getOwnChatterGroup(int chatterId) {
        return null;
    }
}

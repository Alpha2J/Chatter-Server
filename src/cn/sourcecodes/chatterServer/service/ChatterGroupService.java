package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupService {

    /**
     * 增加群组
     * @param chatterGroup
     * @return
     */
    boolean addChatterGroup(ChatterGroup chatterGroup);

    /**
     * 删除群组
     * @param chatterGroup
     * @return
     */
    boolean deleteChatterGroup(ChatterGroup chatterGroup);

    /**
     * 获取某个群基本信息, 不包括群成员
     * @param chatterGroupId
     * @return
     */
    ChatterGroup getBasicChatterGroup(int chatterGroupId);

    /**
     * 获取群信息, 包括群成员(群成员其实只包含id信息)
     * @param chatterGroupId
     * @return
     */
    ChatterGroup getChatterGroup(int chatterGroupId);

    /**
     * 更改群头像
     * @param chatterGroup 需要更改的群
     * @param executeChatterId 谁执行更改
     * @return
     */
    boolean resetHeadImage(ChatterGroup chatterGroup, int executeChatterId);

    /**
     * 更改群名
     * @param chatterGroup
     * @param executeChatterId
     * @return
     */
    boolean resetGroupName(ChatterGroup chatterGroup, int executeChatterId);

    /**
     * 更改群公告
     * @param chatterGroup
     * @param executeChatterId
     * @return
     */
    boolean resetNotice(ChatterGroup chatterGroup, int executeChatterId);

    /**
     * 更改群状态, 禁言, 群主发言等
     * @param chatterGroup
     * @param executeChatterId
     * @return
     */
    boolean resetState(ChatterGroup chatterGroup, int executeChatterId);

    /**
     * 更换群主
     * @param chatterGroup
     * @param executeChatterId
     * @param newOwnerId
     * @return
     */
    boolean resetOwner(ChatterGroup chatterGroup, int executeChatterId, int newOwnerId);

    /**
     * 增加群成员
     * @param chatterGroup
     * @param chatterId 要加入的那个人的id
     * @return
     */
    boolean addGroupMember(ChatterGroup chatterGroup, int chatterId);

    /**
     * 删除群成员
     * @param chatterGroup
     * @param chatterId
     * @return
     */
    boolean deleteGroupMember(ChatterGroup chatterGroup, int chatterId);

    /**
     * 获取某个人拥有的所有群组
     * @param chatterId
     * @return
     */
    List<ChatterGroup> getOwnChatterGroup(int chatterId);



}

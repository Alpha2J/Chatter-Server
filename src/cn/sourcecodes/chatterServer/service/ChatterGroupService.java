package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.ChatterGroup;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupService {

    /**
     * 创建群组
     * @param chatterGroup
     * @return
     */
    boolean createChatterGroup(ChatterGroup chatterGroup);

    /**
     * 增加群成员
     * @param groupId
     * @param memberId
     * @return
     */
    boolean addGroupMember(int groupId, int memberId);

    /**
     * 增加一组成员
     * @param groupId
     * @param memberIds
     * @return
     */
    boolean addGroupMembers(int groupId, List<Integer> memberIds);

    /**
     * 删除群组
     * @param chatterGroup
     * @return
     */
    boolean deleteChatterGroup(ChatterGroup chatterGroup);

    /**
     * 删除群成员(t人)
     * @param groupId
     * @param memberId
     * @return
     */
    boolean deleteGroupMember(int groupId, int memberId);

    /**
     * t一组成员
     * @param groupId
     * @param memberIds
     * @return
     */
    boolean deleteGroupMembers(int groupId, List<Integer> memberIds);

    /**
     * 获取某个群基本信息, 不包括群成员
     * @param chatterGroupId
     * @return
     */
    ChatterGroup getBasicChatterGroup(int chatterGroupId);

    /**
     * 获取群信息, 包括群成员
     * @param chatterGroupId
     * @return
     */
    ChatterGroup getChatterGroup(int chatterGroupId);

    /**
     * 更改群头像
     * @param chatterGroup 需要更改的群
     * @param headImage 新的群头像
     * @return
     */
    boolean resetHeadImage(ChatterGroup chatterGroup, String headImage);

    /**
     * 更改群名
     * @param chatterGroup
     * @param groupName 新的群名
     * @return
     */
    boolean resetGroupName(ChatterGroup chatterGroup, String groupName);

    /**
     * 更改群公告
     * @param chatterGroup
     * @param notice
     * @return
     */
    boolean resetNotice(ChatterGroup chatterGroup, String notice);

    /**
     * 更改群状态, 只允许群主发言等
     * @param chatterGroup
     * @param state
     * @return
     */
    boolean resetState(ChatterGroup chatterGroup, int state);

    /**
     * 更换群主
     * @param chatterGroup
     * @param executeChatterId
     * @param newOwnerId
     * @return
     */
    boolean resetOwner(ChatterGroup chatterGroup, int executeChatterId, int newOwnerId);

    /**
     * 获取某个人拥有的所有群组
     * @param chatterId
     * @return
     */
    List<ChatterGroup> getOwnChatterGroup(int chatterId);
}

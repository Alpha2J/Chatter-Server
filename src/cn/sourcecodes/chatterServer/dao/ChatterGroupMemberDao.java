package cn.sourcecodes.chatterServer.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 代表群成员的dao, 其实数据库中只是一张映射表
 * Created by cn.sourcecodes on 2017/5/21.
 */
public interface ChatterGroupMemberDao {

    /**
     * 增加一个群成员
     * @param groupId
     * @param memberId
     * @return
     */
    long addGroupMember(int groupId, int memberId) throws SQLException;

    /**
     * 增加一组群成员
     * @param groupId
     * @param memberIds
     * @return
     */
    List<Long> addGroupMembers(int groupId, List<Integer> memberIds) throws SQLException;

    /**
     *
     * @param groupId
     * @param memberId
     * @return
     */
    boolean deleteGroupMember(int groupId, int memberId) throws SQLException;

    /**
     * 删除一组群成员
     * @param groupId
     * @param memberIds
     * @return
     */
    boolean deleteGroupMembers(int groupId, List<Integer> memberIds) throws SQLException;

    /**
     * 删除群组(即删除所有成员)
     * @param groupId
     * @return
     */
    boolean deleteGroup(int groupId) throws SQLException;

    /**
     * 获取指定群组的所有成员id
     * @param groupId
     * @return
     */
    List<Integer> getGroupMember(int groupId) throws SQLException;

    /**
     * 获取某个人的所属的所有群组
     * @param memberId
     * @return
     * @throws SQLException
     */
    List<Integer> getOwnGroup(int memberId) throws SQLException;
}

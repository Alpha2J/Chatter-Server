package cn.sourcecodes.chatterServer.service;

import cn.sourcecodes.chatterServer.entity.ContactGroupType;

import java.util.List;

/**
 * Created by cn.sourcecodes on 2017/5/20.
 */
public interface ContactGroupTypeService {

    /**
     * 增加分组
     * @param contactGroupType
     * @return
     */
    boolean addContactGroupType(ContactGroupType contactGroupType);

    /**
     * 删除分组, contactMapping里面还有外键引用时是无法删除的.
     * @param contactGroupId
     * @return
     */
    boolean deleteContactGroup(int contactGroupId);

    /**
     * 获取指定分组
     * @param contactGroupId
     * @return
     */
    ContactGroupType getContactGroupType(int contactGroupId);

    /**
     * 获取某个人的所有ContactGroupType
     * @param chatterId
     * @return
     */
    List<ContactGroupType> getAllContactGroupType(int chatterId);

    /**
     * 更改分组名字
     * @param contactGroupId
     * @param typeName
     * @return
     */
    boolean renameContactGroup(int contactGroupId, String typeName);

}

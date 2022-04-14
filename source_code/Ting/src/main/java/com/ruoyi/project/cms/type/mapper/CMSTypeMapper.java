package com.ruoyi.project.cms.type.mapper;

import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.system.post.domain.Post;

import java.util.List;

public interface CMSTypeMapper {
    /**
     */
    public List<CMSType> selectCmsTypeList(CMSType cmsType);

    /**
     * 根据公告类型名称获取公告类型信息
     * @param typeName
     * @return
     */
    CMSType checkCMSTypeNameUnique(String typeName);

    /**
     * 新增公告类型
     * @param cmsType
     * @return
     */
    int insertCMSType(CMSType cmsType);

    /**
     * 根据id获取公告类型信息
     * @param typeId
     * @return
     */
    CMSType selectCmsTypeById(Long typeId);

    /**
     * 修改公告类型
     * @param cmsType
     * @return
     */
    int updateCMSType(CMSType cmsType);

    /**
     * 删除公告类型
     * @param typeIds
     * @return
     */
    int deleteCMSTypeByIds(Long[] typeIds);
}

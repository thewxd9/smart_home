package com.ruoyi.project.cms.type.service;

import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.system.post.domain.Post;

import java.util.List;

public interface ICMSTypeService {
    /**
     * 查询公告类型信息集合
     *
     * @param cmsType 岗位信息
     * @return 岗位信息集合
     */
    public List<CMSType> selectCmsTypeList(CMSType cmsType);

    /**
     * 公告类型名称唯一性校验
     * @param cmsType
     * @return
     */
    String checkCMSTypeNameUnique(CMSType cmsType);

    /**
     * 新增公告类型业务方法
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
     * @param ids
     * @return
     */
    int deleteCMSTypeByIds(String ids);
}

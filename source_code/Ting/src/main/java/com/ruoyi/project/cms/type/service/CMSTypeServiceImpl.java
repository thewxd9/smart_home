package com.ruoyi.project.cms.type.service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.cms.type.mapper.CMSTypeMapper;
import com.ruoyi.project.system.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CMSTypeServiceImpl implements ICMSTypeService{
    //声明mapper层属性
    @Autowired
    private CMSTypeMapper cmsTypeMapper;
    /**
     * 查询公告类型集合信息
     */
    @Override
    public List<CMSType> selectCmsTypeList(CMSType cmsType) {
        return cmsTypeMapper.selectCmsTypeList(cmsType);
    }

    /**
     * 新增公告类型名称的唯一性校验
     * @param cmsType
     * @return
     */
    @Override
    public String checkCMSTypeNameUnique(CMSType cmsType) {

        Long typeId = StringUtils.isNull(cmsType.getTypeId()) ? -1L : cmsType.getTypeId();
        CMSType info = cmsTypeMapper.checkCMSTypeNameUnique(cmsType.getTypeName());
        if (StringUtils.isNotNull(info) && info.getTypeId().longValue() != typeId.longValue())
        {
            return UserConstants.CMS_TYPE_NAME_NOT_UNIQUE;
        }
        return UserConstants.CMS_TYPE_NAME_UNIQUE;
    }
    /**
     * 新增公告类型
     * @param cmsType
     * @return
     */
    @Override
    public int insertCMSType(CMSType cmsType) {
        cmsType.setCreateBy(ShiroUtils.getLoginName());
        return cmsTypeMapper.insertCMSType(cmsType);
    }

    /**
     * 根据id获取公告类型信息
     * @param typeId
     * @return
     */
    @Override
    public CMSType selectCmsTypeById(Long typeId) {
        return cmsTypeMapper.selectCmsTypeById(typeId);
    }

    /**
     * 修改公告类型
     * @param cmsType
     * @return
     */
    @Override
    public int updateCMSType(CMSType cmsType) {
        cmsType.setUpdateBy(ShiroUtils.getLoginName());
        return cmsTypeMapper.updateCMSType(cmsType);
    }

    /**
     * 删除公告类型
     * @param ids
     * @return
     */
    @Override
    public int deleteCMSTypeByIds(String ids) {
        Long[] typeIds = Convert.toLongArray(ids);
        //应该在删除之前先校验该类型下是否有公告，有则不能删除，报错

        return cmsTypeMapper.deleteCMSTypeByIds(typeIds);
    }
}

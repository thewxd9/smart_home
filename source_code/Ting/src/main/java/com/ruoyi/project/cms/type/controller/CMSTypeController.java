package com.ruoyi.project.cms.type.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cms.type.domain.CMSType;
import com.ruoyi.project.cms.type.service.ICMSTypeService;
import com.ruoyi.project.system.post.domain.Post;
import com.ruoyi.project.system.post.service.IPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cms/type")
public class CMSTypeController extends BaseController {
    //声明业务层属性
    @Autowired
    private ICMSTypeService cmsTypeService;
    //声明资源地址的公共前缀
    private String prefix = "cms/type";

    /**
     * 删除公告类型
     */
    @RequiresPermissions("cms:type:remove")
    @Log(title = "公告管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(cmsTypeService.deleteCMSTypeByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 修改保存公告类型
     */
    @RequiresPermissions("cms:type:edit")
    @Log(title = "公告类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CMSType cmsType)
    {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(cmsTypeService.checkCMSTypeNameUnique(cmsType)))
        {
            return error("修改公告类型'" + cmsType.getTypeName()+ "'失败，类型名称已存在");
        }
        return toAjax(cmsTypeService.updateCMSType(cmsType));
    }
    /**
     * 修改公告类型
     */
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        mmap.put("type", cmsTypeService.selectCmsTypeById(typeId));
        return prefix + "/edit";
    }
    //声明单元方法:跳转公告类型管理页面
    @RequiresPermissions("cms:type:view")
    @GetMapping()
    public String cmsType(){
        return prefix+"/type";
    }
    /**
     * 校验公告类型名称
     */
    @PostMapping("/checkCMSTypeNameUnique")
    @ResponseBody
    public String checkPostNameUnique(CMSType cmsType)
    {
        return cmsTypeService.checkCMSTypeNameUnique(cmsType);
    }
    /**
     * 新增公告类型页面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    /**
     * 新增保存公告类型
     */
    @RequiresPermissions("cms:type:add")
    @Log(title = "公告类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CMSType cmsType)
    {
        if (UserConstants.CMS_TYPE_NAME_NOT_UNIQUE.equals(cmsTypeService.checkCMSTypeNameUnique(cmsType)))
        {
            return error("新增岗位'" + cmsType.getTypeName() + "'失败，岗位名称已存在");
        }

        return toAjax(cmsTypeService.insertCMSType(cmsType));
    }
    /**
     * 分页加载公告类型
     * @param cmsType
     * @return
     */
    @RequiresPermissions("cms:type:list")//校验发起此次请求的账户是否具备分页查看公告类型的权限
    @PostMapping("/list")//请求地址
    @ResponseBody//设置单元方法的返回值即为响应的数据，ajax响应
    public TableDataInfo list(CMSType cmsType)//声明实体类形参接收请求数据，但是只接收分页条件以外的数据
    {
        /**
         * 1.该方法底层调用PageHelper分页插件，自定完成分页查询的相关设置以及分页Sql语句的拼接
         * 也就是会自动的在我们声明的查询公告类型的Sql语句后拼接limit关键字
         * 2.startPage方法的底层会自动的调用HttpServletRequest对象获取此次分页请求中的
         *  分页条件，pageNum和pageSize。
         *
         */
        startPage();
        /**
         * 调用业务层完成分页查询，并获取分页结果
         */
        List<CMSType> list = cmsTypeService.selectCmsTypeList(cmsType);//查询公告类型
        /**
         * getDataTable方法将存储了分页结果的集合转换为TableDataInfo对象并返回
         * 然后响应给前台。
         * 注意：
         *  前台页面中接收的是TableDataInfo对象的json数据，不是list集合，相当于EasyUI的DataGrid
         *  的方式，DataGrid接收的是PageResult，只不过以前PageResult是我们自己声明，现在在若依
         *  项目中已经提前声明好了。
         *
         */
        return getDataTable(list);//返回
    }
}

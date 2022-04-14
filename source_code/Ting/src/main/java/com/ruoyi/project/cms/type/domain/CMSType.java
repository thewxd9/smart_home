package com.ruoyi.project.cms.type.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Objects;

/**
 * 创建公告类型实体类
 * 注意：
 *   需要继承BaseEntity,该父类中声明了公共的字段属性。
 *   我们无需再实体类中再次声明，提升开发效率，便于代码的维护。
 *
 *

 */
public class CMSType extends BaseEntity {
    private Long typeId;
    private String typeName;
    private String typeSort;
    private String typeDesc;

    @Override
    public String toString() {
        return "CMSType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeSort='" + typeSort + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMSType cmsType = (CMSType) o;
        return Objects.equals(typeId, cmsType.typeId) &&
                Objects.equals(typeName, cmsType.typeName) &&
                Objects.equals(typeSort, cmsType.typeSort) &&
                Objects.equals(typeDesc, cmsType.typeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, typeSort, typeDesc);
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public CMSType() {
    }

    public CMSType(Long typeId, String typeName, String typeSort, String typeDesc) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeSort = typeSort;
        this.typeDesc = typeDesc;
    }
}

package cn.bdqn.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hasee on 2017/7/31.
 * 供应商实体类
 */
public class Provider implements Serializable {
    private Integer id;//供应商ID
    private String proCode;//供应商编码
    private String proName;//供应商名称
    private String proDesc;//供应商描述
    private String proContact;//供应商联系人
    private String proPhone;//供应商电话
    private String proAddress;//供应商地址
    private String proFax;//供应商传真
    private Integer createdBy;//创建者ID
    private Date creationDate;//创建日期
    private Date modifyDate;//修改日期
    private Integer modifyBy;//修改者ID

    public Provider() {
    }

    public Provider(Integer id) {
        this.id = id;
    }

    public Provider(Integer id, String proCode, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, Integer createdBy, Date creationDate, Date modifyDate, Integer modifyBy) {
        this.id = id;
        this.proCode = proCode;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.modifyBy = modifyBy;
    }

    /**
     * 新增方法
     * @param proCode 供应商编号
     * @param proName 供应商名称
     * @param proDesc 供应商描述
     * @param proContact 供应商联系人
     * @param proPhone 供应商联系人电话
     * @param proAddress 供应商地址
     * @param proFax 供应商传真
     * @param createdBy 创建者ID
     */
    public Provider(String proCode, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, Integer createdBy) {
        this.proCode = proCode;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createdBy = createdBy;
    }

    /**
     * 修改构造方法
     * @param id 供应商ID
     * @param proName 供应商名称
     * @param proDesc 供应商描述
     * @param proContact 供应商联系人
     * @param proPhone 联系人电话
     * @param proAddress 供应商地址
     * @param proFax 供应商传真
     * @param modifyBy 修改者
     */
    public Provider(Integer id, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, Integer modifyBy) {
        this.id = id;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.modifyBy = modifyBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", proCode='" + proCode + '\'' +
                ", proName='" + proName + '\'' +
                ", proDesc='" + proDesc + '\'' +
                ", proContact='" + proContact + '\'' +
                ", proPhone='" + proPhone + '\'' +
                ", proAddress='" + proAddress + '\'' +
                ", proFax='" + proFax + '\'' +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyDate=" + modifyDate +
                ", modifyBy=" + modifyBy +
                '}';
    }
}

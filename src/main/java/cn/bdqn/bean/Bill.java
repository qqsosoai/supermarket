package cn.bdqn.bean;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hasee on 2017/7/31.
 * 账单实体类
 */
public class Bill implements Serializable{
    private Integer id;//账单ID
    private Provider proId;//供应商是对象
    private String billCode;//账单编号
    private String productName;//商品名称
    private String productDesc;//商品描述
    private String productUnit;//商品数量单位
    private BigDecimal productCount;//商品数量
    private BigDecimal totalPrice;//商品金额
    private Integer isPayment;//是否付款
    private Integer createdBy;//创建者ID
    private Date creationDate;//创建时间
    private Integer modifyBy;//修改者
    private Date modifyDate;//修改时间

    /**
     * 新增构造方法()
     * @param proId 供应商
     * @param billCode 商品编号
     * @param productName 商品名称
     * @param productDesc 商品备注
     * @param productUnit 商品数量单位
     * @param productCount 商品数量
     * @param totalPrice 商品金额
     * @param isPayment 是否支付
     * @param createdBy 创建者
     */
    public Bill(Provider proId, String billCode, String productName, String productDesc, String productUnit, BigDecimal productCount, BigDecimal totalPrice, Integer isPayment, Integer createdBy) {
        this.proId = proId;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
    }

    /**
     *修改构造方法
     * @param proId 对象供应商
     * @param productName 商品名称
     * @param productDesc 商品备注
     * @param productUnit 商品数量单位
     * @param productCount 商品数量
     * @param totalPrice 商品金额
     * @param isPayment 是否支付
     * @param modifyBy 修改者
     * @param id 账单ID
     */
    public Bill(Provider proId, String productName, String productDesc, String productUnit, BigDecimal productCount, BigDecimal totalPrice, Integer isPayment, Integer modifyBy,Integer id) {
        this.proId = proId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.modifyBy = modifyBy;
        this.id = id;
    }

    public Bill() {
    }

    public Bill(Integer id) {
        this.id=id;
    }

    public Bill(Integer id, Provider proId, String billCode, String productName, String productDesc, String productUnit, BigDecimal productCount, BigDecimal totalPrice, Integer isPayment, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate) {
        this.id = id;
        this.proId = proId;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Provider getProId() {
        return proId;
    }

    public void setProId(Provider proId) {
        this.proId = proId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
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

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", proId=" + proId +
                ", billCode='" + billCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productCount=" + productCount +
                ", totalPrice=" + totalPrice +
                ", isPayment=" + isPayment +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                '}';
    }
}

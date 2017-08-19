package cn.bdqn.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hasee on 2017/7/31.
 */
public class User implements Serializable{
    private Integer userId;//用户ID
    private String userCode;//用户账号
    private String userName;//用户姓名
    private String userPassword;//用户密码
    private Integer sex;//用户姓名
    private Date birthday;//用户出生日期
    private String phone;//用户电话
    private String address;//用户地址
    private Integer userType;//用户类型（1：系统管理员、2：经理、3：普通员工）
    private Integer createdBy;//创建人ID
    private Date creationDate;//创建时间
    private Integer modifyBy;//修改者ID
    private Date modifyDate;//修改时间
    private Integer age;
    public User() {
    }

    public User(Integer userId, String userCode, String userName, String userPassword, Integer sex, Date birthday, String phone, String address, Integer userType, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userType = userType;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    /**
     * 添加用户构造方法
     * @param userCode 用户账号
     * @param userName 用户姓名
     * @param userPassword 用户密码
     * @param sex 用户性别
     * @param birthday 用户生日
     * @param phone 用户电话
     * @param address 用户地址
     * @param userType 用户权限
     * @param createdBy 创建者
     */
    public User(String userCode, String userName, String userPassword, Integer sex, Date birthday, String phone, String address, Integer userType, Integer createdBy) {
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userType = userType;
        this.createdBy = createdBy;
    }

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param userName 用户姓名
     * @param sex 用户性别
     * @param birthday 用户生日
     * @param phone 用户电话
     * @param address 用户地址
     * @param modifyBy 修改人
     */
    public User(Integer userId, String userName, Integer sex, Date birthday, String phone, String address, Integer modifyBy) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.modifyBy = modifyBy;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = new Date().getYear() - birthday.getYear();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userType=" + userType +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                '}';
    }
}

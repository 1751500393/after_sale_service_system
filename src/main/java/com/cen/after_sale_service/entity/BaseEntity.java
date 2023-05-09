package com.cen.after_sale_service.entity;

import java.io.Serializable;//序列化类
import java.util.Date;
import java.util.Objects;

/** 作为实体类的基类(实际就是把公有字段提取出来，做成基类类似于Object) */
public class BaseEntity implements Serializable {
  private String createdUser;//日志-创建人
  private Date createdTime;//日志-创建时间
  private String  modifiedUser;//日志-最后修改执行人
  private Date  modifiedTime;//日志-最后修改时间
  //toString

  @Override
  public String toString() {
    return "BaseEntity{" +
            "createdUser='" + createdUser + '\'' +
            ", createdTime=" + createdTime +
            ", modifiedUser='" + modifiedUser + '\'' +
            ", modifiedTime=" + modifiedTime +
            '}';
  }

  //equals&&hashcode

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BaseEntity)) return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(getCreatedUser(), that.getCreatedUser()) &&
            Objects.equals(getCreatedTime(), that.getCreatedTime()) &&
            Objects.equals(getModifiedUser(), that.getModifiedUser()) &&
            Objects.equals(getModifiedTime(), that.getModifiedTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCreatedUser(), getCreatedTime(), getModifiedUser(), getModifiedTime());
  }

  //实体类get/set类
  public String getCreatedUser() {
    return createdUser;
  }

  public void setCreatedUser(String createdUser) {
    this.createdUser = createdUser;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public String getModifiedUser() {
    return modifiedUser;
  }

  public void setModifiedUser(String modifiedUser) {
    this.modifiedUser = modifiedUser;
  }

  public Date getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}

package demotest1.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 客户表(WkCrmCustomer)表实体类
 *
 * @author makejava
 * @since 2022-04-08 14:39:51
 */
@SuppressWarnings("serial")
public class WkCrmCustomer extends Model<WkCrmCustomer> {

    private Integer customerId;
    //客户名称
    private String customerName;
    //跟进状态 0未跟进1已跟进
    private Integer followup;
    //1锁定
    private Integer isLock;
    //下次联系时间
    private Date nextTime;
    //成交状态 0 未成交 1 已成交
    private Integer dealStatus;
    //成交时间
    private Date dealTime;
    //首要联系人ID
    private Integer contactsId;
    //手机
    private String mobile;
    //电话
    private String telephone;
    //网址
    private String website;
    //邮箱
    private String email;
    //备注
    private String remark;
    //创建人ID
    private Long createUserId;
    //负责人ID
    private Long ownerUserId;
    //只读权限
    private String roUserId;
    //读写权限
    private String rwUserId;
    //省市区
    private String address;
    //定位信息
    private String location;
    //详细地址
    private String detailAddress;
    //地理位置经度
    private String lng;
    //地理位置维度
    private String lat;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //批次 比如附件批次
    private String batchId;
    //客户状态 1 正常 2锁定 3删除
    private Integer status;
    //最后跟进时间
    private Date lastTime;
    //放入公海时间
    private Date poolTime;
    //1 分配 2 领取
    private Integer isReceive;
    //最后一条跟进记录
    private String lastContent;
    //接收到客户时间
    private Date receiveTime;
    //进入公海前负责人id
    private Long preOwnerUserId;
    //客户编号
    private String num;
    //成周周期开始
    private Date dealCycleStart;
    //成周周期截止
    private Date dealCycleEnd;
    //跟进人id
    private Long followUserId;
    //未回款金额
    private Double noReceiveMoney;
    //成交周期
    private Integer dealCycle;
    //默认地址
    private Integer addressId;
    //金蝶客户地址
    private String addressKd;
    //公司年销售额（元）
    private Double salesVolume;
    //公司人数
    private Integer peopleNum;
    //公司年需求金额（元）
    private Double demandAmount;
    //金蝶客户id
    private Integer fcustomerId;
    //所属组织id
    private Integer orgId;
    //金蝶所属组织id
    private Integer forgId;
    //金蝶客户分组
    private Integer fgroupId;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getFollowup() {
        return followup;
    }

    public void setFollowup(Integer followup) {
        this.followup = followup;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getContactsId() {
        return contactsId;
    }

    public void setContactsId(Integer contactsId) {
        this.contactsId = contactsId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getRoUserId() {
        return roUserId;
    }

    public void setRoUserId(String roUserId) {
        this.roUserId = roUserId;
    }

    public String getRwUserId() {
        return rwUserId;
    }

    public void setRwUserId(String rwUserId) {
        this.rwUserId = rwUserId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getPoolTime() {
        return poolTime;
    }

    public void setPoolTime(Date poolTime) {
        this.poolTime = poolTime;
    }

    public Integer getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(Integer isReceive) {
        this.isReceive = isReceive;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Long getPreOwnerUserId() {
        return preOwnerUserId;
    }

    public void setPreOwnerUserId(Long preOwnerUserId) {
        this.preOwnerUserId = preOwnerUserId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getDealCycleStart() {
        return dealCycleStart;
    }

    public void setDealCycleStart(Date dealCycleStart) {
        this.dealCycleStart = dealCycleStart;
    }

    public Date getDealCycleEnd() {
        return dealCycleEnd;
    }

    public void setDealCycleEnd(Date dealCycleEnd) {
        this.dealCycleEnd = dealCycleEnd;
    }

    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public Double getNoReceiveMoney() {
        return noReceiveMoney;
    }

    public void setNoReceiveMoney(Double noReceiveMoney) {
        this.noReceiveMoney = noReceiveMoney;
    }

    public Integer getDealCycle() {
        return dealCycle;
    }

    public void setDealCycle(Integer dealCycle) {
        this.dealCycle = dealCycle;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressKd() {
        return addressKd;
    }

    public void setAddressKd(String addressKd) {
        this.addressKd = addressKd;
    }

    public Double getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Double salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Double getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(Double demandAmount) {
        this.demandAmount = demandAmount;
    }

    public Integer getFcustomerId() {
        return fcustomerId;
    }

    public void setFcustomerId(Integer fcustomerId) {
        this.fcustomerId = fcustomerId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getForgId() {
        return forgId;
    }

    public void setForgId(Integer forgId) {
        this.forgId = forgId;
    }

    public Integer getFgroupId() {
        return fgroupId;
    }

    public void setFgroupId(Integer fgroupId) {
        this.fgroupId = fgroupId;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }
}


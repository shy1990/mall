package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sanji.mall.pojo.Base;

public class Order extends Base {
	private static final long serialVersionUID = 1L;

	private String id;

	private String memberId;

	private Date createtime = new Date();

	private String sourceType;

	private String saleType;

	private String status;

	private String payStatus;

	private String shipStatus;

	private String keshStatus;

	private String isDelivery;

	private String expressId;

	private String expressNum;

	private String payMent;

	private Date payTime;

	private String shipName;

	private String area;

	private String shipZip;

	private String shipTel;

	private String shipEmail;

	private Date shipTime;

	private BigDecimal totalCost;

	private BigDecimal carriage;

	private BigDecimal advance;

	private BigDecimal ebankMon;

	private String remark;

	private Date modifytime;

	private BigDecimal orderPoints;

	private String province;

	private String city;

	private String address;

	private String memberType;

	private String billType;

	private String billHead;

	private String billContent;

	private String disabled;

	private String orderNum;

	private String logisticsTel;

	private String dealId;

	private String dealType;

	private String ecerpNo;

	private String ecerpCreated;

	private String ecerpError;

	// 宽展字段省市区
	private String pname;
	private String cname;
	private String aname;

	private Date signForTime;

	private int showQuit;// 1显示退款退货 2不显示退款退货

	private String itemsId;// 订单详情Id

	private Date searchStartTime;// 搜索订单开始时间
	private Date searchEndTime;// 搜索订单结束时间

	private String overMan;

	private Date overTime;

	private String userName;

	// 下面两个是添加钱包支付新增加的属性
	private BigDecimal walletNum;// 使用钱包支付金额
	private BigDecimal actualPayNum;// 实际支付金额
	private String walletPayNo;// 钱包支付流水号
	
	//红包相关属性
	private String hbNo;//红包编号id
	private BigDecimal hbNum;//红包钱数
	
	//action接收参数辅助字段，由于写在action不行。临时解决方案
	   private String hdSkus;//要购买的活动商品列表
	
	

	public String getHdSkus() {
        return hdSkus;
    }

    public void setHdSkus(String hdSkus) {
        this.hdSkus = hdSkus;
    }

    public String getHbNo() {
		return hbNo;
	}

	public void setHbNo(String hbNo) {
		this.hbNo = hbNo;
	}

	public BigDecimal getHbNum() {
		return hbNum;
	}

	public void setHbNum(BigDecimal hbNum) {
		this.hbNum = hbNum;
	}

	public String getWalletPayNo() {
		return walletPayNo;
	}

	public void setWalletPayNo(String walletPayNo) {
		this.walletPayNo = walletPayNo;
	}

	public BigDecimal getWalletNum() {
		return walletNum;
	}

	public void setWalletNum(BigDecimal walletNum) {
		this.walletNum = walletNum;
	}

	public BigDecimal getActualPayNum() {
		return actualPayNum;
	}

	public void setActualPayNum(BigDecimal actualPayNum) {
		this.actualPayNum = actualPayNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOverMan() {
		return overMan;
	}

	public void setOverMan(String overMan) {
		this.overMan = overMan;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public Date getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public int getShowQuit() {
		return showQuit;
	}

	public void setShowQuit(int showQuit) {
		this.showQuit = showQuit;
	}

	public Date getSignForTime() {
		return signForTime;
	}

	public void setSignForTime(Date signForTime) {
		this.signForTime = signForTime;
	}

	// 订单详情列表
	private List<OrderItems> orderItemss;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public List<OrderItems> getOrderItemss() {
		return orderItemss;
	}

	public void setOrderItemss(List<OrderItems> orderItemss) {
		this.orderItemss = orderItemss;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType == null ? null : sourceType.trim();
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType == null ? null : saleType.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus == null ? null : payStatus.trim();
	}

	public String getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus == null ? null : shipStatus.trim();
	}

	public String getKeshStatus() {
		return keshStatus;
	}

	public void setKeshStatus(String keshStatus) {
		this.keshStatus = keshStatus == null ? null : keshStatus.trim();
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery == null ? null : isDelivery.trim();
	}

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId == null ? null : expressId.trim();
	}

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum == null ? null : expressNum.trim();
	}

	public String getPayMent() {
		return payMent;
	}

	public void setPayMent(String payMent) {
		this.payMent = payMent == null ? null : payMent.trim();
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName == null ? null : shipName.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip == null ? null : shipZip.trim();
	}

	public String getShipTel() {
		return shipTel;
	}

	public void setShipTel(String shipTel) {
		this.shipTel = shipTel == null ? null : shipTel.trim();
	}

	public String getShipEmail() {
		return shipEmail;
	}

	public void setShipEmail(String shipEmail) {
		this.shipEmail = shipEmail == null ? null : shipEmail.trim();
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getCarriage() {
		return carriage;
	}

	public void setCarriage(BigDecimal carriage) {
		this.carriage = carriage;
	}

	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public BigDecimal getEbankMon() {
		return ebankMon;
	}

	public void setEbankMon(BigDecimal ebankMon) {
		this.ebankMon = ebankMon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public BigDecimal getOrderPoints() {
		return orderPoints;
	}

	public void setOrderPoints(BigDecimal orderPoints) {
		this.orderPoints = orderPoints;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType == null ? null : memberType.trim();
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType == null ? null : billType.trim();
	}

	public String getBillHead() {
		return billHead;
	}

	public void setBillHead(String billHead) {
		this.billHead = billHead == null ? null : billHead.trim();
	}

	public String getBillContent() {
		return billContent;
	}

	public void setBillContent(String billContent) {
		this.billContent = billContent == null ? null : billContent.trim();
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled == null ? null : disabled.trim();
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum == null ? null : orderNum.trim();
	}

	public String getLogisticsTel() {
		return logisticsTel;
	}

	public void setLogisticsTel(String logisticsTel) {
		this.logisticsTel = logisticsTel == null ? null : logisticsTel.trim();
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId == null ? null : dealId.trim();
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType == null ? null : dealType.trim();
	}

	/**
	 * @return the ecerpNo
	 */
	public String getEcerpNo() {
		return ecerpNo;
	}

	/**
	 * @param ecerpNo
	 *            the ecerpNo to set
	 */
	public void setEcerpNo(String ecerpNo) {
		this.ecerpNo = ecerpNo;
	}

	/**
	 * @return the ecerpCreated
	 */
	public String getEcerpCreated() {
		return ecerpCreated;
	}

	/**
	 * @param ecerpCreated
	 *            the ecerpCreated to set
	 */
	public void setEcerpCreated(String ecerpCreated) {
		this.ecerpCreated = ecerpCreated;
	}

	/**
	 * @return the ecerpError
	 */
	public String getEcerpError() {
		return ecerpError;
	}

	/**
	 * @param ecerpError
	 *            the ecerpError to set
	 */
	public void setEcerpError(String ecerpError) {
		this.ecerpError = ecerpError;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", memberId=" + memberId + ", createtime=" + createtime + ", sourceType=" + sourceType + ", saleType=" + saleType + ", status=" + status
				+ ", payStatus=" + payStatus + ", shipStatus=" + shipStatus + ", keshStatus=" + keshStatus + ", isDelivery=" + isDelivery + ", expressId=" + expressId
				+ ", expressNum=" + expressNum + ", payMent=" + payMent + ", payTime=" + payTime + ", shipName=" + shipName + ", area=" + area + ", shipZip=" + shipZip
				+ ", shipTel=" + shipTel + ", shipEmail=" + shipEmail + ", shipTime=" + shipTime + ", totalCost=" + totalCost + ", carriage=" + carriage + ", advance=" + advance
				+ ", ebankMon=" + ebankMon + ", remark=" + remark + ", modifytime=" + modifytime + ", orderPoints=" + orderPoints + ", province=" + province + ", city=" + city
				+ ", address=" + address + ", memberType=" + memberType + ", billType=" + billType + ", billHead=" + billHead + ", billContent=" + billContent + ", disabled="
				+ disabled + ", orderNum=" + orderNum + ", logisticsTel=" + logisticsTel + ", dealId=" + dealId + ", dealType=" + dealType + ", pname=" + pname + ", cname="
				+ cname + ", aname=" + aname + ", orderItemss=" + orderItemss + "]";
	}

}
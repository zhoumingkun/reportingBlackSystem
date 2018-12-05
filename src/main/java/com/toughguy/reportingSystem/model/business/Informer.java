package com.toughguy.reportingSystem.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 举报人实体类
 * @author BOBO
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class Informer extends AbstractModel {
	
	private int id;
	private String informerName;    //举报人姓名
	private String idCard;          //身份证号
	private String otherContectWay;  //其他联系方式
	private String workPlace;        //工作单位
	private String livingArea;       //居住地区
	private String address;          //详细地址
	private String phoneNumber;      //手机号码
	private String openId;              //微信唯一标识符
	private String encryptName;      //加密举报人姓名
	private String encryptIdCard;     //加密举报人身份证号
	private String encryptPhoneNumber;   //加密手机号
	private String encryptOtherContectWay;   //加密其他联系方式
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInformerName() {
		return informerName;
	}
	public void setInformerName(String informerName) {
		this.informerName = informerName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getLivingArea() {
		return livingArea;
	}
	public void setLivingArea(String livingArea) {
		this.livingArea = livingArea;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOtherContectWay() {
		return otherContectWay;
	}
	public void setOtherContectWay(String otherContectWay) {
		this.otherContectWay = otherContectWay;
	}
	//加密信息
	public String getEncryptName() {
		return encryptName;
	}
	public void setEncryptName(String encryptName) {
		this.encryptName = encryptName;
	}
	public String getEncryptIdCard() {
		return encryptIdCard;
	}
	public void setEncryptIdCard(String encryptIdCard) {
		this.encryptIdCard = encryptIdCard;
	}
	public String getEncryptPhoneNumber() {
		return encryptPhoneNumber;
	}
	public void setEncryptPhoneNumber(String encryptPhoneNumber) {
		this.encryptPhoneNumber = encryptPhoneNumber;
	}
	public String getEncryptOtherContectWay() {
		return encryptOtherContectWay;
	}
	public void setEncryptOtherContectWay(String encryptOtherContectWay) {
		this.encryptOtherContectWay = encryptOtherContectWay;
	}
	

	
	
	
}

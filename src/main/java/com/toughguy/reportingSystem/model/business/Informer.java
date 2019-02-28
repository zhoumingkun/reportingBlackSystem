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
	private String phoneNumber;      //手机号码
	private String openId;              //微信唯一标识符
	private String encryptName;      //加密举报人姓名
	private String encryptPhoneNumber;   //加密手机号
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
	//加密信息
	public String getEncryptName() {
		return encryptName;
	}
	public void setEncryptName(String encryptName) {
		this.encryptName = encryptName;
	}
	public String getEncryptPhoneNumber() {
		return encryptPhoneNumber;
	}
	public void setEncryptPhoneNumber(String encryptPhoneNumber) {
		this.encryptPhoneNumber = encryptPhoneNumber;
	}
	

	
	
	
}

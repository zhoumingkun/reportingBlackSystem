package com.toughguy.reportingSystem.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 行业领域实体类
 * @author zmk
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class IndustryContent extends AbstractModel {
	private int id;
	private String content;      //机构名称
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

	
}

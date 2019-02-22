package com.toughguy.reportingSystem.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 奖励规定实体类
 * @author zmk
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class AwardContent extends AbstractModel {
	private int id;
	private String title;   //标题
	private String textContent;     //内容
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	

	
}

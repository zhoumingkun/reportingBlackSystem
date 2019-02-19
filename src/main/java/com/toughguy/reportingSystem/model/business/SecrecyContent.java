package com.toughguy.reportingSystem.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toughguy.reportingSystem.model.AbstractModel;

/**
 * 保密规定实体类
 * @author zmk
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //为空字段不返回
public class SecrecyContent extends AbstractModel {
	private int id;
	private String title;   //标题
	private String author;   //作者
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	

	
}

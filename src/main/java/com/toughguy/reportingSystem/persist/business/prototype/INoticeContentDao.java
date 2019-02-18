package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.NoticeContent;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 举报须知Dao接口类
 * @author zmk
 *
 */
public interface INoticeContentDao extends IGenericDao<NoticeContent, Integer>{
	
	/**
	 * 根据内容类型查询
	 * */
	public NoticeContent findByType(int type);
	

}

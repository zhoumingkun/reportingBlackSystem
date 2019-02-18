package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.AwardContent;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 奖励规定Dao接口类
 * @author zmk
 *
 */
public interface IAwardContentDao extends IGenericDao<AwardContent, Integer>{
	
	/**
	 * 根据内容类型查询
	 * */
	public AwardContent findByType(int type);
	

}

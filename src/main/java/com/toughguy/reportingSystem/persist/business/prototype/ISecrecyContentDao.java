package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.SecrecyContent;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 保密规定Dao接口类
 * @author zmk
 *
 */
public interface ISecrecyContentDao extends IGenericDao<SecrecyContent, Integer>{
	
	/**
	 * 根据内容类型查询
	 * */
	public SecrecyContent findByType(int type);
	

}

package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.KindContent;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 举报种类Dao接口类
 * @author zmk
 *
 */
public interface IKindContentDao extends IGenericDao<KindContent, Integer>{
	
	/**
	 * 根据内容类型查询
	 * */
	public KindContent findByType(int type);
	

}

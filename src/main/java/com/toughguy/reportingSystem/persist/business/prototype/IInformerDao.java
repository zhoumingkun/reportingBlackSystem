package com.toughguy.reportingSystem.persist.business.prototype;

import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 举报人Dao接口类
 * @author BOBO
 *
 */
public interface IInformerDao  extends IGenericDao<Informer, Integer>{
	/**
	 * 个人信息获取
	 */
	public Informer getInformer(String openId);
	
	/**
	 * 根据informerId获取个人信息
	 */
	public Informer findById(int informerId);
}


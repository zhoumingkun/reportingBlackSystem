package com.toughguy.reportingSystem.service.business.prototype;

import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.service.prototype.IGenericService;

/**
 * 举报人Service层接口类
 * @author BOBO
 *
 */
public interface IInformerService extends IGenericService<Informer, Integer>{


	public Informer getInformer(String openId);
	/**
	 * 根据informerId获取个人信息
	 */
	public Informer findById(int informerId);

}

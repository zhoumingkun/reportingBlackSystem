package com.toughguy.reportingSystem.service.business.prototype;
import com.toughguy.reportingSystem.model.business.SecrecyContent;
import com.toughguy.reportingSystem.service.prototype.IGenericService;
/**
 * 保密规定Service层接口类
 * @author zmk
 *
 */
public interface ISecrecyContentService extends IGenericService<SecrecyContent, Integer>{
	/**
	 * 根据内容类型查询
	 * */
	public SecrecyContent findByType(int type);

}

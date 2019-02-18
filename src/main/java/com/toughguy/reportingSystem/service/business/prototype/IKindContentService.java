package com.toughguy.reportingSystem.service.business.prototype;
import com.toughguy.reportingSystem.model.business.KindContent;
import com.toughguy.reportingSystem.service.prototype.IGenericService;
/**
 * 举报种类Service层接口类
 * @author zmk
 *
 */
public interface IKindContentService extends IGenericService<KindContent, Integer>{
	/**
	 * 根据内容类型查询
	 * */
	public KindContent findByType(int type);

}

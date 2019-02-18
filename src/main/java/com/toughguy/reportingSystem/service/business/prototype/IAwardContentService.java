package com.toughguy.reportingSystem.service.business.prototype;
import com.toughguy.reportingSystem.model.business.AwardContent;
import com.toughguy.reportingSystem.service.prototype.IGenericService;
/**
 * 奖励规定Service层接口类
 * @author zmk
 *
 */
public interface IAwardContentService extends IGenericService<AwardContent, Integer>{
	/**
	 * 根据内容类型查询
	 * */
	public AwardContent findByType(int type);

}

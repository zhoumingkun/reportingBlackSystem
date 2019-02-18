package com.toughguy.reportingSystem.service.business.prototype;
import com.toughguy.reportingSystem.model.business.NoticeContent;
import com.toughguy.reportingSystem.service.prototype.IGenericService;
/**
 * 举报须知Service层接口类
 * @author zmk
 *
 */
public interface INoticeContentService extends IGenericService<NoticeContent, Integer>{
	/**
	 * 根据内容类型查询
	 * */
	public NoticeContent findByType(int type);

}

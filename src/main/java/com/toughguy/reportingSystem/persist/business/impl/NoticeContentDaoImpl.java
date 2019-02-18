package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;
import com.toughguy.reportingSystem.model.business.NoticeContent;
import com.toughguy.reportingSystem.persist.business.prototype.INoticeContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 举报须知Dao实现类
 * @author zmk
 *
 */
@Repository
public class NoticeContentDaoImpl extends GenericDaoImpl<NoticeContent, Integer> implements INoticeContentDao{
	
	@Override
	public NoticeContent findByType(int type) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByType", type);
	}

}

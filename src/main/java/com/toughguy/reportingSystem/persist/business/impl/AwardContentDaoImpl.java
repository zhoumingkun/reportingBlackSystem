package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.AwardContent;
import com.toughguy.reportingSystem.persist.business.prototype.IAwardContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 奖励规定Dao实现类
 * @author zmk
 *
 */
@Repository
public class AwardContentDaoImpl extends GenericDaoImpl<AwardContent, Integer> implements IAwardContentDao{
	
	@Override
	public AwardContent findByType(int type) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByType", type);
	}

}

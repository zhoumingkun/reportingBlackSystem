package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;
import com.toughguy.reportingSystem.model.business.KindContent;
import com.toughguy.reportingSystem.persist.business.prototype.IKindContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 举报种类Dao实现类
 * @author zmk
 *
 */
@Repository
public class KindContentDaoImpl extends GenericDaoImpl<KindContent, Integer> implements IKindContentDao{
	
	@Override
	public KindContent findByType(int type) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByType", type);
	}

}

package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.IndustryContent;
import com.toughguy.reportingSystem.persist.business.prototype.IIndustryContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 行业领域Dao实现类
 * @author zmk
 *
 */
@Repository
public class IndustryContentDaoImpl extends GenericDaoImpl<IndustryContent, Integer> implements IIndustryContentDao{
	

}

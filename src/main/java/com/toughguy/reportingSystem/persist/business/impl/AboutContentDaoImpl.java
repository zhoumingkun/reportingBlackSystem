package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.AboutContent;
import com.toughguy.reportingSystem.persist.business.prototype.IAboutContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 关于我们Dao实现类
 * @author zmk
 *
 */
@Repository
public class AboutContentDaoImpl extends GenericDaoImpl<AboutContent, Integer> implements IAboutContentDao{
	

}

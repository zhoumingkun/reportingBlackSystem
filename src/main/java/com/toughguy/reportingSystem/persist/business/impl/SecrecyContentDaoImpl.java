package com.toughguy.reportingSystem.persist.business.impl;
import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.SecrecyContent;
import com.toughguy.reportingSystem.persist.business.prototype.ISecrecyContentDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 保密规定Dao实现类
 * @author zmk
 *
 */
@Repository
public class SecrecyContentDaoImpl extends GenericDaoImpl<SecrecyContent, Integer> implements ISecrecyContentDao{
	

}

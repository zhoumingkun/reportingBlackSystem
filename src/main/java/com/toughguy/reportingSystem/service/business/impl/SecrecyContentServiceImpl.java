package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.SecrecyContent;
import com.toughguy.reportingSystem.persist.business.prototype.ISecrecyContentDao;
import com.toughguy.reportingSystem.service.business.prototype.ISecrecyContentService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;


/**
 * 保密规定Service实现类
 * @author zmk
 *
 */
@Service
public class SecrecyContentServiceImpl extends GenericServiceImpl<SecrecyContent, Integer> implements ISecrecyContentService{
	
	@Override
	public SecrecyContent findByType(int type) {
		// TODO Auto-generated method stub
		return ((ISecrecyContentDao)dao).findByType(type);	
	}

}

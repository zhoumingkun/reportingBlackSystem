package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.KindContent;
import com.toughguy.reportingSystem.persist.business.prototype.IKindContentDao;
import com.toughguy.reportingSystem.service.business.prototype.IKindContentService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;


/**
 * 举报种类Service实现类
 * @author zmk
 *
 */
@Service
public class KindContentServiceImpl extends GenericServiceImpl<KindContent, Integer> implements IKindContentService{

}

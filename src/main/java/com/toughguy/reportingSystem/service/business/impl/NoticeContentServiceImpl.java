package com.toughguy.reportingSystem.service.business.impl;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.AwardContent;
import com.toughguy.reportingSystem.model.business.NoticeContent;
import com.toughguy.reportingSystem.persist.business.prototype.IAwardContentDao;
import com.toughguy.reportingSystem.persist.business.prototype.INoticeContentDao;
import com.toughguy.reportingSystem.service.business.prototype.IAwardContentService;
import com.toughguy.reportingSystem.service.business.prototype.INoticeContentService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;


/**
 * 举报须知Service实现类
 * @author zmk
 *
 */
@Service
public class NoticeContentServiceImpl extends GenericServiceImpl<NoticeContent, Integer> implements INoticeContentService{
	

}

package com.toughguy.reportingSystem.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.persist.business.prototype.IInformationDao;
import com.toughguy.reportingSystem.service.business.prototype.IInformationService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;

/**
 * 举报信息Service实现类
 * @author BOBO
 *
 */
@Service
public class InformationServiceImpl extends GenericServiceImpl<Information, Integer> implements IInformationService {

	@Override
	public int findNum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findNum(params);
	}
	@Override
	public List<Integer> findValidNumber(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findValidNumber(threadAreaIdAndAcceptUnits);
	}
	@Override
	public List<InformationDTO> findSum(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findSum(threadAreaIdAndAcceptUnits);
	}
	@Override
	public List<Information> getInformation(int informerId) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).getInformation(informerId);
	}
	@Override
	public Information findAllInformerType(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findAllInformerType(threadAreaIdAndAcceptUnits);
	}
	@Override
	public List<Information> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findByOpenId(openId);
	}
	@Override
	public List<Information> findByThreadAreaId(int threadAreaId) {
		// TODO Auto-generated method stub
		return ((IInformationDao)dao).findByThreadAreaId(threadAreaId);
	}


}

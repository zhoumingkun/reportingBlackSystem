package com.toughguy.reportingSystem.service.business.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughguy.reportingSystem.model.business.Region;
import com.toughguy.reportingSystem.persist.business.prototype.IRegionDao;
import com.toughguy.reportingSystem.service.business.prototype.IRegionService;
import com.toughguy.reportingSystem.service.impl.GenericServiceImpl;


/**
 * 地域Service实现类
 * @author zmk
 *
 */
@Service
public class RegionServiceImpl extends GenericServiceImpl<Region, Integer> implements IRegionService{
	@Autowired
	private IRegionDao dao;
	
	@Override
	public List<Region> findByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.findByParams(params);
	}
	@Override
	public Region findByRegionName(String regionName) {
		// TODO Auto-generated method stub
		return dao.findByRegionName(regionName);
	}
	@Override
	public Region findByPId(int id) {
		// TODO Auto-generated method stub
		return dao.findByPId(id);
	}

}

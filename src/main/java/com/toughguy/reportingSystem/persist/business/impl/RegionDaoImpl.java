package com.toughguy.reportingSystem.persist.business.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.model.business.Region;
import com.toughguy.reportingSystem.persist.business.prototype.IRegionDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
/**
 * 地域Dao实现类
 * @author zmk
 *
 */
@Repository
public class RegionDaoImpl extends GenericDaoImpl<Region, Integer> implements IRegionDao{
	@Override
	public List<Region> findByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByParams",params);
	}

	@Override
	public Region findByRegionName(String regionName) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findByRegionName",regionName);
	}

}

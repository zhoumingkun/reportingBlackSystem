package com.toughguy.reportingSystem.persist.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.authority.User;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.model.business.Informer;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.persist.business.prototype.IInformationDao;
import com.toughguy.reportingSystem.persist.impl.GenericDaoImpl;
import com.toughguy.reportingSystem.system.SystemContext;

/**
 * 举报信息Dao实现类
 * @author BOBO
 *
 */
@Repository
public class InformationDaoImpl extends GenericDaoImpl<Information, Integer> implements IInformationDao{

	@Override
	public int findNum(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findNum", params);
	}
	
	//admin
	@Override
	public int findNumAll(int state) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findNumAll", state);
	}
	
	//admin
		@Override
		public int findAllNum() {
			// TODO Auto-generated method stub
			return sqlSessionTemplate.selectOne(typeNameSpace + ".findAllNum");
		}

	@Override
	public List<Integer> findValidNumber(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findValidNumber",threadAreaIdAndAcceptUnits);
	}
	
	//admin
	@Override
	public List<Integer> findValidNumberAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findValidNumberAll");
	}
	
	@Override
	public List<InformationDTO> findSum(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findSum",threadAreaIdAndAcceptUnits);
	}
	
	//admin
	@Override
	public List<InformationDTO> findSumAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findSumAll");
	}

	// -- 获取总的条目数 (分页查询中使用)
	private int findValidTotal() {
		int count = (Integer) sqlSessionTemplate.selectOne(typeNameSpace + ".findValidTotal");
		return count;
	}
	// -- 举报记录小程序查询
	@Override
	public List<Information> getInformation(int informerId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".getInformation", informerId);
		
	}
	// -- 查询各行业领域类型数量
	@Override
	public Information findAllInformerType(int threadAreaIdAndAcceptUnits) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne(typeNameSpace + ".findAllInformerType",threadAreaIdAndAcceptUnits);
	}
	
	//admin
	@Override
	public Information findAllInformerTypeAll() {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne(typeNameSpace + ".findAllInformerTypeAll");
	}

	@Override
	public List<Information> findByOpenId(String openId) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectList(typeNameSpace + ".findByOpenId",openId);
	}
	
	@Override
	public List<Information> newInformation(String openId) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectList(typeNameSpace + ".newInformation",openId);
	}

	@Override
	public List<Information> findByThreadAreaId(int threadAreaId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findByThreadAreaId",threadAreaId);
	}
	
	@Override
	public Information findRegionNum() {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne(typeNameSpace + ".findRegionNum");
	}
	
	@Override
	public Information findAllRegionNum() {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne(typeNameSpace + ".findAllRegionNum");
	}
}

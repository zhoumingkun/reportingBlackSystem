package com.toughguy.reportingSystem.service.business.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.service.prototype.IGenericService;

/**
 * 举报信息Service层接口类
 * @author BOBO
 *
 */
public interface IInformationService extends IGenericService<Information, Integer>{

	/**
	 * 查询案件数量接口
	 * @param params
	 * @return
	 */
	public int findNum(Map<String, Object> params);
	
	/**
	 * 查询案件数量接口(admin)
	 * @param state
	 * @param params
	 * @return
	 */
	public int findNumAll(int state);
	public int findAllNum();
	/**
	 * 查询每日举报已接案件数量
	 * @param threadAreaIdAndAcceptUnits
	 * @return
	 */
	public List<Integer> findValidNumber(int threadAreaIdAndAcceptUnits);
	
	/**
	 * 查询每日举报已接案件数量(admin)
	 * @param 
	 * @return
	 */
	public List<Integer> findValidNumberAll();
	
	/**
	 * 查询每日举报总数
	 * @param threadAreaIdAndAcceptUnits
	 * @return
	 */
	public List<InformationDTO> findSum(int threadAreaIdAndAcceptUnits);
	
	/**
	 * 查询每日举报总数(admin)
	 * @param 
	 * @return
	 */
	public List<InformationDTO> findSumAll();
	
	/**
	 * 查询记录小程序查询
	 * @return
	 */
//	public List<Information> getInformation(String informerId);
	public List<Information> getInformation(int informerId);
	
	/**
	 * 查询各行业领域类型数量
	 * @param threadAreaIdAndAcceptUnits
	 * @return
	 */
	public Information findAllInformerType(int threadAreaIdAndAcceptUnits);
	
	/**
	 * 查询各行业领域类型数量(admin)
	 * @param 
	 * @return
	 */
	public Information findAllInformerTypeAll();
	
	/**
	 * 根据openId查询举报信息（匿名）
	 */
	public List<Information> findByOpenId(String openId);
	
	/**
	 *  根据线报地域查询举报信息 
	 */
	public List<Information> findByThreadAreaId(int threadAreaId);
}

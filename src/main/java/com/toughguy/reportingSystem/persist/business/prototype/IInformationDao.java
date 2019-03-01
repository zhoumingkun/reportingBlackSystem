package com.toughguy.reportingSystem.persist.business.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.reportingSystem.dto.InformationDTO;
import com.toughguy.reportingSystem.model.business.Information;
import com.toughguy.reportingSystem.pagination.PagerModel;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 举报信息Dao接口类
 * @author BOBO
 *
 */
public interface IInformationDao  extends IGenericDao<Information, Integer>{

	/**
	 * 查询案件数量接口
	 * @param params (state,threadAreaId,acceptUnits)
	 * @return
	 */
	public int findNum(Map<String, Object> params);
	/**
	 * 查询每日举报已接案件数量
	 * @param threadAreaIdAndAcceptUnits
	 * @return
	 */
	public List<Integer> findValidNumber(int threadAreaIdAndAcceptUnits);
	/**
	 * 查询每日举报总数
	 * @param threadAreaIdAndAcceptUnits
	 * @return
	 */
	public List<InformationDTO> findSum(int threadAreaIdAndAcceptUnits);
	/**
	 * 举报记录小程序查询
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
	 * 根据openId查询举报信息（匿名）
	 */
	public List<Information> findByOpenId(String openId);
	/**
	 *  根据线报地域查询举报信息 
	 */
	public List<Information> findByThreadAreaId(int threadAreaId);
}

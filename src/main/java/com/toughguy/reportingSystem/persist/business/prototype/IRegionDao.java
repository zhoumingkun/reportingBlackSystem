package com.toughguy.reportingSystem.persist.business.prototype;

import java.util.List;
import java.util.Map;

import com.toughguy.reportingSystem.model.business.Region;
import com.toughguy.reportingSystem.persist.prototype.IGenericDao;

/**
 * 地域Dao接口类
 * @author zmk
 *
 */
public interface IRegionDao extends IGenericDao<Region, Integer>{
	/**
	 * 根据条件查询多个地域
	 * @param params
	 * @return 集合
	 */
	public List<Region> findByParams(Map<String, Object> params);
	/**
	 * 临时查询之根据库名查询库
	 * @param regionName
	 * @return 
	 */
	public Region findByRegionName(String regionName);
	/**
	 *  根据区县id查询市或者是根据市id查询省
	 * @param id 区县ID或市ID
	 * @return 
	 */
	public Region findByPId(int id);

}

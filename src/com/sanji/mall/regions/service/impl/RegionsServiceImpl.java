package com.sanji.mall.regions.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.model.Regions;
import com.sanji.mall.regions.dao.RegionsMapper;
import com.sanji.mall.regions.service.RegionsService;

@Service("regionsService")
@Transactional(rollbackFor = Exception.class)
public class RegionsServiceImpl implements RegionsService {

	@Resource
	private RegionsMapper regionsMapper;

	/*
	 * (非 Javadoc) <p>Title: gainProvince</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.regions.service#gainProvince
	 */
	public List<Regions> gainProvince() {
		List<Regions> pList = regionsMapper.gainProvince();
		if (pList != null && pList.size() > 0) {
			return pList;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title: gainCity</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.regions.service#gainCity
	 */
	public List gainCity(String id) {
		List<Regions> cList = regionsMapper.gainCity(id);
		if (cList != null && cList.size() > 0) {
			return cList;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title: gainArea</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.regions.service#gainArea
	 */
	public List gainArea(String id) {
		List<Regions> aList = regionsMapper.gainArea(id);
		if (aList != null && aList.size() > 0) {
			return aList;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title: gainTown</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.regions.service#gainArea
	 */
	public List gainTown(String id) {
		List<Regions> tList = regionsMapper.gainTown(id);
		// System.out.println("---------------"+id);
		if (tList != null && tList.size() > 0) {
			/*
			 * for(int i=0;i<tList.size();i++){ //
			 * System.out.println("---------------"+tList.get(i)); }
			 */
			return tList;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title: gainCityListByPid</p> <p>Description: </p>
	 * 
	 * @param pid
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.regions.service.RegionsService#gainCityListByPid(java.
	 * lang.String)
	 */

	public List<Regions> gainCityListByPid(String pid) {
		return regionsMapper.gainCityListByPid(pid);
	}

	/*
	 * (非 Javadoc) <p>Title: gainAreaListByCityId</p> <p>Description: </p>
	 * 
	 * @param cityId
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.regions.service.RegionsService#gainAreaListByCityId(java
	 * .lang.String)
	 */

	public List<Regions> gainAreaListByCityId(String cityId) {
		return regionsMapper.gainAreaListByCityId(cityId);
	}

}

package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.OrganizationInit;
import kq.qh.dao.dataSource0.ValidateBdcdyhDao;
import kq.qh.entity.ValidateBdcdyh;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Service;


/**
 * @author Administrator
 *
 */
@Service
public class BdcdyValidateService {

	@Resource
	private OrganizationInit organizationInit;
	
	@Resource
	private ValidateBdcdyhDao validateBdcdyhDao;
	/**
	 * 不动产单元验证监控数据查询
	 * @param startTime
	 * @param endTime
	 * @param validateBdcdyh
	 * @param page
	 * @return
	 */
	public List<ValidateBdcdyh> findEntityListInPage(String startTime, String endTime,
			ValidateBdcdyh validateBdcdyh, PageUtil page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("item", validateBdcdyh);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("startRow",  (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		return validateBdcdyhDao.findEntityListInPage(map);
	}
	/**
	 * 不动产单元验证监控导出Excel文档
	 * @param startTime
	 * @param endTime
	 * @param validateBdcdyh
	 * @return
	 */
	public List<ValidateBdcdyh> findEntityListInPage(String startTime, String endTime,
			ValidateBdcdyh validateBdcdyh) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("item", validateBdcdyh);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return validateBdcdyhDao.findEntityListInPage(map);
	}
	/**
	 * 不动产单元验证监控数据查询统计
	 * @param startTime
	 * @param endTime
	 * @param validateBdcdyh
	 * @return
	 */
	public int count(String startTime, String endTime, ValidateBdcdyh validateBdcdyh) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("item", validateBdcdyh);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return validateBdcdyhDao.count(map);
	}
	/**
	 * 不动产单元验证统计数据查询
	 * @param startTime
	 * @param endTime
	 * @param orgCode
	 * @param exist
	 * @return
	 */
	public List<ValidateBdcdyh> countNum(String startTime, String endTime, String orgCode,String exist) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exist", exist);
		map.put("orgCode", orgCode);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<ValidateBdcdyh> list = validateBdcdyhDao.countNum(map);
		for(ValidateBdcdyh entity : list){
			entity.setOrganization( organizationInit.getObjMap().get(entity.getOrgCode()));
		}
		return list;
	}

}

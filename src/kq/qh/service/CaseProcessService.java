package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.RegistTypeInit;
import kq.qh.common.OrganizationInit;
import kq.qh.common.ProcessNodeInit;
import kq.qh.dao.dataSource0.CaseProcessDao;
import kq.qh.entity.CaseProcess;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;


/**
 * @Package kq.qh.service
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年9月8日 下午2:34:37
 * @version V1.0
 */
@Service
public class CaseProcessService {

	@Resource
	private CaseProcessDao caseProcessDao;
	@Resource
	private OrganizationInit organizationInit;
	@Resource
	private ProcessNodeService processNodeService;
	@Resource
	private ProcessNodeInit processNodeInit;
	@Resource
	private RegistTypeInit registTypeInit;
	@Resource
	private DeptInit deptInit;
	
	/**
	 * @Title  CaseProcessService.java
	 * @param caseProcess
	 * @author ylf
	 * @date 2016年9月8日 下午2:35:46
	 * @version V1.0
	 */
	
	public List<CaseProcess> findEntityList(Map<String , Object> map) {
		List<CaseProcess> list = caseProcessDao.findEntityList(map);
		for (CaseProcess caseProcess : list) {
			caseProcess.setOrganization1(organizationInit.getObjMap().get(caseProcess.getCaseOrgCode()));
			caseProcess.setDept(deptInit.getDeptMap().get(caseProcess.getOperateOrgCode()));
		}
		return list;
	}
	
	/**
	 * @Title  CaseProcessService.java
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 上午10:24:47
	 * @version V1.0
	 */
	public int count(CaseProcess caseProcess) {
		return caseProcessDao.count(caseProcess);
	}

	/**
	 * @Title  CaseProcessService.java
	 * @param caseProcess
	 * @param pageUtil
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 上午10:40:57
	 * @version V1.0
	 */
	@Cacheable(cacheName = "default")
	public List<CaseProcess> findTheLatestData(String oprid,String starTime,String endTime,CaseProcess caseProcess,PageUtil page) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("oprid", oprid);
		map.put("starTime", starTime);
		map.put("endTime", endTime);
		map.put("item", caseProcess);
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		
		List<CaseProcess> list = caseProcessDao.findTheLatestData(map);
		for (CaseProcess caseProcess2 : list) {
			caseProcess2.setOrganization1(organizationInit.getObjMap().get(caseProcess2.getCaseOrgCode()));
			caseProcess2.setDept(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
//			caseProcess2.setDept1(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
		}
		return list;
	}
	/**
	 * 主面数据统计双击弹窗出案件详情查询
	 * @param oprid
	 * @param starTime
	 * @param endTime
	 * @param caseProcess
	 * @param page
	 * @return
	 */
	public List<CaseProcess> findTheLatest(String oprid,String starTime,String endTime,CaseProcess caseProcess,PageUtil page) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("oprid", oprid);
		map.put("starTime", starTime);
		map.put("endTime", endTime);
		map.put("item", caseProcess);
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		
		List<CaseProcess> list = caseProcessDao.findTheLatest(map);
		for (CaseProcess caseProcess2 : list) {
			caseProcess2.setOrganization1(organizationInit.getObjMap().get(caseProcess2.getCaseOrgCode()));
			caseProcess2.setDept(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
//			caseProcess2.setDept1(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
		}
		return list;
	}
	/**
	 * @Title  CaseProcessService.java
	 * @param caseProcess
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 下午3:43:50
	 * @version V1.0
	 */
	public int countTheLatestData(String oprid,String starTime,String endTime,CaseProcess caseProcess) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("oprid", oprid);
		map.put("starTime", starTime);
		map.put("endTime", endTime);
		map.put("caseProcess", caseProcess);
		return caseProcessDao.countTheLatestData(map);
	}
	/**
	 * 主面数据统计双击弹窗出案件详情查询统计
	 * @param oprid
	 * @param starTime
	 * @param endTime
	 * @param caseProcess
	 * @return
	 */
	public int countTheLatest(String oprid,String starTime,String endTime,CaseProcess caseProcess) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("oprid", oprid);
		map.put("starTime", starTime);
		map.put("endTime", endTime);
		map.put("caseProcess", caseProcess);
		return caseProcessDao.countTheLatest(map);
	}
	
	public List<CaseProcess> findAll(CaseProcess caseProcess){
		return caseProcessDao.findAll(caseProcess);
	};

}

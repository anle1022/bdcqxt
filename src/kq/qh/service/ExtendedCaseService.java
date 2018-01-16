package kq.qh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.dao.dataSource0.ExtendedCaseDao;
import kq.qh.entity.CaseProcess;
import kq.qh.entity.ExtendedCase;
import kq.qh.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedCaseService {
	
	@Autowired
	private ExtendedCaseDao extendedCaseDao;
	@Resource
	private DeptInit DeptInit;
	/**
	 * 超期案件数据查询
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @return
	 */
	public List<ExtendedCase> findExtendedCaseList(String startTime, String endTime, ExtendedCase extendedCase) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("extendedCase", extendedCase);
		List<ExtendedCase> list = extendedCaseDao.findExtendedCaseList(map);
		for(ExtendedCase v : list){
			v.setOrgName(DeptInit.getDeptMap().get(v.getOrgCode()).getOrgName());
		}
		return list;
	}

	public int deleteEntityByTime(String time) {
		return extendedCaseDao.deleteEntityByTime(time);
	}

	public int addEntityListInBatch(List<ExtendedCase> extendedCaseList) {
		if(extendedCaseList.size()>0){
			List<ExtendedCase> addListInBatch = new ArrayList<ExtendedCase>();
			for(int i=0;i<extendedCaseList.size();i++){
				addListInBatch.add(extendedCaseList.get(i));
				if(i%1000==0){
					extendedCaseDao.addEntityListInBatch(addListInBatch);
					addListInBatch.clear();
				}
			}
			if(addListInBatch.size()>0){	
				extendedCaseDao.addEntityListInBatch(addListInBatch);
			}
		}
		return 1;
	}
	/**
	 * 超期案件弹窗详情数据查询
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @param page
	 * @return
	 */
	public List<CaseProcess> findExtend(String startTime, String endTime, ExtendedCase extendedCase,PageUtil page ){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", extendedCase);
		map.put("startRow", null == page ?0:(page.getPage()-1)*page.getRows());
		map.put("endRow", null == page ?0:page.getPage() * page.getRows() );
		return extendedCaseDao.findExtend(map);
	};
	/**
	 * 超期案件弹窗详情数据统计查询
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @return
	 */
	public Integer countFindExtend(String startTime, String endTime, ExtendedCase extendedCase){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", extendedCase);
		return extendedCaseDao.countFindExtend(map);
	}

}

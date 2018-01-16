package kq.qh.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kq.qh.common.DeptInit;
import kq.qh.common.OperationInit;
import kq.qh.common.ProcessNodeInit;
import kq.qh.dao.dataSource0.CountDao;
import kq.qh.dao.dataSource0.DaySummaryDao;
import kq.qh.dao.dataSource0.DictDao;
import kq.qh.entity.DaySummary;
import kq.qh.entity.Dept;
import kq.qh.entity.Dict;
import kq.qh.entity.Operation;
import kq.qh.entity.ProcessNode;
import kq.qh.util.CollectionUtil;
import kq.qh.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

@Service
public class CountService {

	@Autowired
	private CountDao countDao;
	
	@Autowired
	private ProcessNodeInit nodeInit;
	//@Autowired
	//private OrganizationInit orgInit;
	@Autowired
	private OperationInit operateInit;
	@Autowired
	private DeptInit deptInit;
	@Autowired
	private DictDao dictDao;
	@Autowired
	private DaySummaryDao daySummaryDao;
	/**
	 * 地区流程信息数据查询
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Cacheable(cacheName = "default")
	public Map<String,Object> findAll(String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<DaySummary> list = countDao.findAll(map);
		Map<String,String> bdclxMap = new HashMap<String,String>();
		List<Dict> dictList = dictDao.findEntityList("BDCLX");
		for(Dict dict : dictList){
			bdclxMap.put(dict.getValue(), dict.getNameCN());
		}
		List<ProcessNode> processList = nodeInit.getList();
		for(DaySummary value : list){
			Operation o = operateInit.findFirstParent(value.getDjlx2());
			if (null==o) {
				o = operateInit.getObjMap().get("900");
			}
			Operation o1 = operateInit.getObjMap().get( value.getDjlx2() );
			value.setDjlx2(o1.getName() );
			value.setBllx( bdclxMap.get(o1.getBdclx()) );
			value.setDjlx1(o.getName());
			//String orgCode = value.getOrgCode();
			//orgCode = deptInit.getDeptMap().get(orgCode).getId();
			//value.setOrgCode(orgCode);
		}
		Map<String,Integer> bllxMap = new HashMap<String,Integer>();
		Map<String,Integer> orgSLMap = new HashMap<String,Integer>();
		
		Collections.sort(processList,new Comparator<ProcessNode>() {
			@Override
			public int compare(ProcessNode o1, ProcessNode o2) {
				return o1.getOrderBy().compareTo(o2.getOrderBy());
			}
		});
		map.put("processNode", processList);
		List<Dept> deptList = deptInit.getList();
		//List<Organization> orgList = orgInit.getList();
		Collections.sort(deptList,new Comparator<Dept>() {
			@Override
			public int compare(Dept o1, Dept o2) {
				return o1.getOrgCode().compareTo(o2.getOrgCode());
			}
		});
		map.put("org", deptList);
		//鏍规嵁 鍦板尯--娴佺▼鑺傜偣  缁熻鍚堣  姣忕粍鏁版嵁涓虹粺涓�祦绋嬭妭鐐癸紝涓嶅悓鍦板尯   鏌辩姸鍥�
		List<Integer[]> totallist = new ArrayList<Integer[]>();
		
		for(int i=0;i<processList.size();i++){
			Integer []processCount = new Integer[deptList.size()];
			for(int j=0;j<deptList.size();j++){
				processCount[j]= processCount[j]==null?0:processCount[j];
				for(DaySummary value : list){
					if(value.getOrgCode().equals(deptList.get(j).getId()) && value.getProcessNode().equals(processList.get(i).getKey())){
						processCount[j] += value.getTotal();
					}
				}
			}
			totallist.add(processCount);
			//map1.put(processList.get(i).getKey(), processCount);
		}	
		//楗煎浘  
		Map<String,Integer> djlx1Map = new HashMap<String,Integer>();
		for(DaySummary value : list){
			if(!value.getProcessNode().equals(processList.get(0).getKey())){
				continue;
			}
			//鐧昏绫诲瀷
			if(null == djlx1Map.get(value.getDjlx1())){
				djlx1Map.put(value.getDjlx1(), value.getTotal());
			}else{
				djlx1Map.put(value.getDjlx1(), djlx1Map.get(value.getDjlx1())+value.getTotal());
			}
			
			String bdclx = value.getBllx();
			//鍔炵悊绫诲瀷
			if(null == bllxMap.get(value.getBllx())){
				bllxMap.put(bdclx, value.getTotal());
			}else{
				bllxMap.put(bdclx, bllxMap.get(bdclx)+value.getTotal());
			}
			
//			String orgName = orgInit.getObjMap().get(value.getOrgCode()).getOrgName();
			String orgName = deptInit.getDeptMap().get(value.getOrgCode()).getOrgName();
			if(null == orgSLMap.get(orgName)){
				orgSLMap.put(orgName, value.getTotal());
			}else{
				orgSLMap.put(orgName, orgSLMap.get(orgName)+value.getTotal());
			}
		}
		bllxMap.remove("");
		map.put("djlx1List", CollectionUtil.transforKVEntity(djlx1Map));
		map.put("bllxList", CollectionUtil.transforKVEntity(bllxMap) );
		map.put("orgSLList", CollectionUtil.transforKVEntity(orgSLMap) );
		map.put("processNode_orgList", totallist);
		return map;
	}
	/**
	 * 主页统计信息数据查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @param page
	 * @return
	 */
	@Cacheable(cacheName = "default")
	public List<DaySummary> findDaySummaryInPage(String startTime,String endTime,DaySummary daySummary,PageUtil page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("daySummary", daySummary);
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		List<DaySummary> list = daySummaryDao.findEntityListInPage(map);
		for(DaySummary value : list){
			value.setDept(deptInit.getDeptMap().get(value.getOrgCode()));
		}
		Collections.sort(list,new Comparator<DaySummary>() {
			@Override
			public int compare(DaySummary o1, DaySummary o2) {
				return nodeInit.getObjMap().get(o1.getProcessNode()).getOrderBy().compareTo(nodeInit.getObjMap().get(o2.getProcessNode()).getOrderBy());
			}
		});
		return list;
	}
	/**
	 * 主页统计信息数据统计查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @return
	 */
	public int count(String startTime,String endTime,DaySummary daySummary) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("daySummary", daySummary);
		return daySummaryDao.count(map);
	}

}

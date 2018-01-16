package kq.qh.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.OperationInit;
import kq.qh.common.ProcessNodeInit;
import kq.qh.common.RegistTypeInit;
import kq.qh.dao.dataSource0.DaySummaryDao;
import kq.qh.entity.DaySummary;
import kq.qh.entity.Dept;
import kq.qh.entity.KeyValueEntity;
import kq.qh.entity.ProcessNode;
import kq.qh.entity.RegistType;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mysql.jdbc.StringUtils;

@Service
public class DaySummaryService {

	//@Resource
	//private OrganizationInit organizationInit;
	@Resource
	private ProcessNodeInit processNodeInit;
	@Resource
	private RegistTypeInit registTypeInit;
	@Resource
	private DeptInit deptInit;
	@Resource
	private DaySummaryDao daySummaryDao;
	@Resource
	private OperationInit operationInit;
	/**
	 * 业务量监控数据查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @param djlxList
	 * @return
	 */
	@Cacheable(cacheName="default")
	public List<String[]> countReports( String startTime,String endTime,DaySummary daySummary,List<String> djlxList){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", daySummary);
		map.put("djlxList", djlxList);
		List<DaySummary> list = daySummaryDao.countReports(map);
		int length = 1;
		if(djlxList.size()>0){
			length ++;
		}
		Map<String, String[]> processMap = new HashMap<String,String[]>();//鏍规嵁娴佺▼鑺傜偣涓嶅悓鏌ヨ鏁伴噺
		Map<String, Integer> dept_processNode_map = new HashMap<String,Integer>();//根据地区+流程节点
		Map<String, Integer> djlx_processNode_map = new HashMap<String,Integer>();//地区+登记类型+流程节点
	
		for(ProcessNode processNode:processNodeInit.getList()){
			for(Dept org : deptInit.getList()){
				dept_processNode_map.put(org.getId()+"-"+processNode.getKey(), 0);
			}
		}
		for(ProcessNode processNode:processNodeInit.getList()){
			for(Dept org : deptInit.getList()){
				for(RegistType ca : registTypeInit.getSecondList()){
					String name = registTypeInit.getParentNameMap().get(ca.getId());
					if(StringUtils.isNullOrEmpty(name)){
						continue;
					}
					djlx_processNode_map.put(org.getId()+"-"+name+"-"+processNode.getKey(), 0);
				}
			}
		}
		//循环遍历集合
		for (DaySummary day: list) {
			String deptId = day.getOrgCode();
			String processNode = day.getProcessNode();
			String djlx = day.getDjlx2();
			String name = registTypeInit.getParentNameMap().get(djlx);
			
			if(djlxList.size()>0){
				if (null != djlx_processNode_map.get(deptId+"-"+name+"-"+processNode)) {
					if(!StringUtils.isNullOrEmpty(name)){
						Integer count1 = djlx_processNode_map.get(deptId+"-"+name+"-"+processNode);
						Integer Num = day.getTotal();
						count1+= Num;
						djlx_processNode_map.put(deptId+"-"+name+"-"+processNode, count1);
					}
				}
			}else {
				if(null != dept_processNode_map.get(deptId+"-"+processNode)){
					Integer count1 = dept_processNode_map.get(deptId+"-"+processNode);
					Integer Num = day.getTotal();
					count1+= Num;
					dept_processNode_map.put(deptId+"-"+processNode, count1);
				}
			}
		}
		
		String[] str = null;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int a4 = 0;
		int a5 = 0;
		//循环遍历集合重新取出组成一个新数组
		for (DaySummary day: list) {
			String deptId = day.getOrgCode();
			String processNode = day.getProcessNode();
			String djlx1 = day.getDjlx2();
			String name = registTypeInit.getParentNameMap().get(djlx1);
			
			str = new String[processNodeInit.getList().size()+length];
			String key = "";
			String orgCode = "";
//			String orgCode1 = "";
			String djlx = "";
			key += day.getOrgCode()+"-";
			orgCode = day.getOrgCode(); 
			if(djlxList.size()>0){
				key += day.getDjlx2()+"-";
				djlx = day.getDjlx2();
			} 
//			if(!StringUtils.isNullOrEmpty(key)){
//				key.substring(0, key.length()-2);
//			}
			if(null != processMap.get(key)){
				str = processMap.get(key);
			}
				//灏唎rgCode 杞崲鎴�orgName
//			orgCode = organizationInit.getObjMap().get(day.getOrgCode()).getOrgName(); 
			System.out.println(deptInit.getDeptMap().get(day.getOrgCode()));
			orgCode = deptInit.getDeptMap().get(day.getOrgCode()).getOrgName(); 
			
			str[0] = orgCode;
			if(djlxList.size()>0){
				str[1] = operationInit.getObjMap().get(djlx).getName();
			}
			Integer num = processNodeInit.getObjMap().get(day.getProcessNode()).getOrderBy();
			if(djlxList.size()>0){
				str[length-1+num] = djlx_processNode_map.get(deptId+"-"+name+"-"+processNode)+"";
			}else {
				str[length-1+num] = dept_processNode_map.get(deptId+"-"+processNode)+"";
			}
			processMap.put(key, str);
			if (djlxList.size() > 0) {
				switch (length-1+num) {
				case 2:
					a1 +=Integer.parseInt(str[length-1+num]);
					break;
				case 3:
					a2 +=Integer.parseInt(str[length-1+num]);
					break;
				case 4:
					a3 +=Integer.parseInt(str[length-1+num]);
					break;
				case 5:
					a4 +=Integer.parseInt(str[length-1+num]);
					break;
				case 6:
					a5 +=Integer.parseInt(str[length-1+num]);
					break;
				default:
					break;
				}
			}else {
				switch (length-1+num) {
				case 1:
					a1 +=Integer.parseInt(str[length-1+num]);
					break;
				case 2:
					a2 +=Integer.parseInt(str[length-1+num]);
					break;
				case 3:
					a3 +=Integer.parseInt(str[length-1+num]);
					break;
				case 4:
					a4 +=Integer.parseInt(str[length-1+num]);
					break;
				case 5:
					a5 +=Integer.parseInt(str[length-1+num]);
					break;
				default:
					break;
				}
			}
		}
		List<String[]> arrList = new ArrayList<String[]>();
		Set<?> set = processMap.keySet();
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			String[] arrStr = processMap.get(key);
			arrList.add(arrStr);
		}
		Collections.sort(arrList,new Comparator<String[]>(){
			
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[0].compareTo(o2[0]);
			}
		});
		if (djlxList.size()>0) {
			String[]  cc = {"合 计","---",a1+" ",a2+" ",a3+" ",a4+" ",a5+" "};
			arrList.add(cc);
		}else {
			String[]  cc = {"合 计",a1+" ",a2+" ",a3+" ",a4+" ",a5+" "};
			arrList.add(cc);
		}
 		return arrList;
	}
	/**
	 * 主页数据统计页面数据查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @return
	 */
	@Cacheable(cacheName="default")
	public Map<String,Object> countCaseDetial(String startTime,String endTime,DaySummary daySummary) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("caseProcess",daySummary);
		
		List<DaySummary> caseProcessList = daySummaryDao.countCaseDetial(map);
//		Map<String,Integer> m = new HashMap<String,Integer>();//鏍规嵁orgCode 鏌ヨ妗堜欢鑺傜偣鏁伴噺
		Map<String,Integer> m1 = new HashMap<String,Integer>();//鏍规嵁娴佺▼鑺傜偣涓嶅悓鏌ヨ鏁伴噺
		Map<String,Integer> dept_processNode_map = new HashMap<String,Integer>();//鏍规嵁orgCode 鍜�娴佺▼鑺傜偣锛屾煡璇㈡暟閲�
		
		Map<String, Integer> djlx_processNode_map = new HashMap<String, Integer>();
		
		Map<String,Integer> m2 = new HashMap<String,Integer>();
		Integer bl=0;
		m2.put("宗地", bl);
		
		for(ProcessNode processNode:processNodeInit.getList()){
			for(RegistType ca : registTypeInit.getSecondList()){
				String name = registTypeInit.getParentNameMap().get(ca.getId());
				if(StringUtils.isNullOrEmpty(name)){
					continue;
				}
				djlx_processNode_map.put(name+"-"+processNode.getKey(), 0);
			}
		}	
//		for(Organization org : organizationInit.getList()){
//			m.put(org.getOrgCode()+"", 0);
//		}
		for(ProcessNode processNode:processNodeInit.getList()){
			m1.put(processNode.getKey(), 0);
		}
		for(ProcessNode processNode:processNodeInit.getList()){
			for(Dept dept : deptInit.getList()){
				dept_processNode_map.put(dept.getId()+"-"+processNode.getKey(), 0);
			}
		}
		for(DaySummary entity:caseProcessList){
			String processNode = entity.getProcessNode();
			if (processNode != null) {
				Integer count1 =m1.get(processNode);
				Integer Num = entity.getTotal();
				count1+=Num;
				m1.put(processNode, count1);
			}
//			String caseOrgCode = entity.getOrgCode();
//			if(caseOrgCode != null){
//				Integer coNum= m.get(caseOrgCode+"");
//				Integer coNums = entity.getTotal();
//				coNum+=coNums;
//				m.put(caseOrgCode+"", coNum);
//			}
			String deptId =  entity.getOrgCode() ;
			
//			if(caseOrgCode != null){
//				Integer count1 = m.get(caseOrgCode+"");
//				m.put(caseOrgCode+"", ++count1);
//			}
			if(null != dept_processNode_map.get(deptId+"-"+processNode)){
				Integer count1 = dept_processNode_map.get(deptId+"-"+processNode);
				Integer Num = entity.getTotal();
				count1+= Num;
				dept_processNode_map.put(deptId+"-"+processNode, count1);
			}
			String djlx = entity.getDjlx2();
			String name = registTypeInit.getParentNameMap().get(djlx);
			if (null != djlx_processNode_map.get(name+"-"+processNode)) {
				if(!StringUtils.isNullOrEmpty(name)){
					Integer count1 = djlx_processNode_map.get(name+"-"+processNode);
					Integer Num = entity.getTotal();
					count1+= Num;
					djlx_processNode_map.put(name+"-"+processNode, count1);
				}
			}
			String bllx = entity.getBllx();
			if (bllx != null) {
				if (bllx.equals("宗地")) {
					 Integer aa = entity.getTotal();
					bl+=aa;
					m2.put(bllx, bl);
				}
			}
		}
//		List<KeyValueEntity> orgKVList = transforKVEntity(m);
//		for(KeyValueEntity v:orgKVList){
//			v.setValue1(organizationInit.getMap().get( v.getKey() ));
//		}
//		Collections.sort(orgKVList,new Comparator<KeyValueEntity>() {
//
//			@Override
//			public int compare(KeyValueEntity o1, KeyValueEntity o2) {
//				return o1.getKey().compareTo(o2.getKey());
//			}
//		});
//		for(KeyValueEntity v:orgKVList){
//			v.setValue1(organizationInit.getMap().get(  v.getKey()   ));
//		}
		List<KeyValueEntity> processNodeKVList = transforKVEntity(m1);
		List<KeyValueEntity> org_processNode_KVList = transforKVEntity(dept_processNode_map);
		for(KeyValueEntity value : org_processNode_KVList){
			value.setValue1(deptInit.getDeptMap().get(value.getKey().split("-")[0]).getOrgName() );
		}
		
		Collections.sort(org_processNode_KVList, new Comparator<KeyValueEntity>(){
			@Override
			public int compare(KeyValueEntity o1, KeyValueEntity o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		Collections.sort(org_processNode_KVList, new Comparator<KeyValueEntity>(){
			@Override
			public int compare(KeyValueEntity o1, KeyValueEntity o2) {
				ProcessNode p1 = processNodeInit.getObjMap().get( o1.getKey().split("-")[1] );
				ProcessNode p2 = processNodeInit.getObjMap().get( o2.getKey().split("-")[1] );
				return p1.getOrderBy().compareTo(p2.getOrderBy());
			}
		});
		List<KeyValueEntity> djlxProcessNodeKVList = transforKVEntity(djlx_processNode_map);
		Collections.sort(djlxProcessNodeKVList, new Comparator<KeyValueEntity>(){
			@Override
			public int compare(KeyValueEntity o1, KeyValueEntity o2) {
				ProcessNode p1 = processNodeInit.getObjMap().get( o1.getKey().split("-")[1] );
				ProcessNode p2 = processNodeInit.getObjMap().get( o2.getKey().split("-")[1] );
				return p1.getOrderBy().compareTo(p2.getOrderBy());
			}
		});
		for(KeyValueEntity value : djlxProcessNodeKVList){
			System.out.println(value.getKey().split("_")[0]);
			value.setValue1(operationInit.getMap().get( value.getKey().split("_")[0] ) );
		}
		Collections.sort(djlxProcessNodeKVList, new Comparator<KeyValueEntity>(){
			@Override 
			public int compare(KeyValueEntity o1, KeyValueEntity o2) {
				String id1 = registTypeInit.getMap().get( o1.getKey().split("-")[0].split("_")[1] );
				String id2 = registTypeInit.getMap().get( o2.getKey().split("-")[0].split("_")[1] );
				return id1.compareTo(id2);
			}
		});
		
		List<KeyValueEntity> bllx = transforKVEntity(m2);
		
//		map.put("org", orgKVList);
		map.put("processNode", processNodeKVList);
		map.put("org_processNode", org_processNode_KVList);
		map.put("djlx_processNode", djlxProcessNodeKVList);
		map.put("bllx", bllx);
		return map;
	}
	
	private List<KeyValueEntity> transforKVEntity(Map<String,?> map){
		List<KeyValueEntity> list = new ArrayList<KeyValueEntity>();
		Set<?> set = map.keySet();
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			KeyValueEntity kv = new KeyValueEntity();
			String key = (String) it.next();
			kv.setKey(key);
			kv.setValue(map.get(key).toString());
			list.add(kv);
		}
		return list;
	}
}

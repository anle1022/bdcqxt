package kq.qh.schedule.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kq.qh.common.OperationInit;
import kq.qh.common.ProcessNodeInit;
import kq.qh.dao.dataSource0.CaseProcessDao;
import kq.qh.dao.dataSource0.CollectDao;
import kq.qh.dao.dataSource0.DaySummaryDao;
import kq.qh.entity.CaseProcess;
import kq.qh.entity.ExtendedCase;
import kq.qh.entity.Operation;
import kq.qh.schedule.otherService.HolidayService;
import kq.qh.service.ExtendedCaseService;
import kq.qh.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

/**
 * title  采集数据，流程数据，案件信息数据
 * @author Administrator
 *
 */
@Service(value="collectDataService")
public class CollectDataService {
	
	@Autowired
	private CollectDao collectDao;
	@Autowired
	private CaseProcessDao caseProcessDao;
	@Autowired
	private DaySummaryDao daySummaryDao;
	@Autowired
	private OperationInit operateInit;
	@Autowired
	private HolidayService hilidayService;
	@Autowired
	private ExtendedCaseService extendedCaseService;
	@Autowired
	private ProcessNodeInit processNodeInit;
	
	private final int DEFUALT_LIMIT_TIME = 35;
	private final int PROCESS_OVER_NODE = 3;
	
	public void collectFlowData(String startTime,String endTime){
		List<String> timeList = DateUtil.getBetweenDate(startTime, endTime);
		for(String time : timeList){
			collectFlowData(time);
		}
	}
	
	@Transactional
	public boolean collectFlowData(String time){
		try{
			//采集之前先将当天数据删除
			caseProcessDao.deleteCaseProcessByTime(time);
			//采集流程信息-- 受理
			List<CaseProcess> slList = collectDao.findSLxx(time);
			if(slList.size()>0){
				List<CaseProcess> slListInBatch = new ArrayList<CaseProcess>();
				for(int i = 0;i<slList.size();i++){
					CaseProcess caseProcess = slList.get(i);
					if(StringUtils.isNullOrEmpty( caseProcess.getBdcdyh() ) || caseProcess.getDjlx1().length()>3 ){
						caseProcess.setDjlx(caseProcess.getDjlx1());
					}else{
						for(Operation v :operateInit.getList()){
							if(StringUtils.isNullOrEmpty(v.getZdtzm())  ){
								continue;
							}
							String zdtzm = caseProcess.getBdcdyh().substring(12, 14);
							String dzwtzm = caseProcess.getBdcdyh().substring(19, 20);
							if(zdtzm.equals("GB")&&dzwtzm.equals("F")){
								System.out.println(caseProcess);
							}
							if(v.getPid().equals(caseProcess.getDjlx1())&&v.getZdtzm().contains(zdtzm)&&v.getDzwtzm().contains(dzwtzm)){
								caseProcess.setDjlx(v.getId());
								break;
							}
						}
					}
					slListInBatch.add(slList.get(i));
					if(i !=0 && i%1000 ==0){
						caseProcessDao.addEntityInBatch(slList);
						slListInBatch.clear();
					}
				}
				caseProcessDao.addEntityInBatch(slListInBatch);
			}
			
			//查询出当天的核定的信息
			List<CaseProcess> hdList = collectDao.findHDxx(time);
			//将核定信息更新到数据库表中
			if(hdList.size()>0){
				List<CaseProcess> hdListInBatch = new ArrayList<CaseProcess>();
				for(int i = 0;i<hdList.size();i++){
					CaseProcess caseProcess = hdList.get(i);
					if(StringUtils.isNullOrEmpty( caseProcess.getBdcdyh() ) || caseProcess.getDjlx1().length()>3 ){
						caseProcess.setDjlx(caseProcess.getDjlx1());
					}else{
						for(Operation v :operateInit.getList()){
							if(StringUtils.isNullOrEmpty(v.getZdtzm())  ){
								continue;
							}
							String zdtzm = caseProcess.getBdcdyh().substring(12, 14);
							String dzwtzm = caseProcess.getBdcdyh().substring(19, 20);
							if(v.getPid().equals(caseProcess.getDjlx1())&&v.getZdtzm().contains(zdtzm)&&v.getDzwtzm().contains(dzwtzm)){
								caseProcess.setDjlx(v.getId());
								break;
							}
						}
					}
					hdListInBatch.add(hdList.get(i));
					if(i !=0 && i%1000 == 0){
						caseProcessDao.addEntityInBatch(hdListInBatch);
						hdListInBatch.clear();
					}
				}
				caseProcessDao.addEntityInBatch(hdListInBatch);
			}
			//查询出当天的缮证的信息
			List<CaseProcess> szList = collectDao.findSZxx(time);
			//将缮证信息更新到数据库表中
			if(szList.size()>0){
				List<CaseProcess> szListInBatch = new ArrayList<CaseProcess>();
				for(int i = 0;i<szList.size();i++){
					CaseProcess caseProcess = szList.get(i);
					if(StringUtils.isNullOrEmpty( caseProcess.getBdcdyh() ) || caseProcess.getDjlx1().length()>3 ){
						caseProcess.setDjlx(caseProcess.getDjlx1());
					}else{
						for(Operation v :operateInit.getList()){
							if(StringUtils.isNullOrEmpty(v.getZdtzm())  ){
								continue;
							}
							String zdtzm = caseProcess.getBdcdyh().substring(12, 14);
							String dzwtzm = caseProcess.getBdcdyh().substring(19, 20);
							if(v.getPid().equals(caseProcess.getDjlx1())&&v.getZdtzm().contains(zdtzm)&&v.getDzwtzm().contains(dzwtzm)){
								caseProcess.setDjlx(v.getId());
								break;
							}
						}
					}
					szListInBatch.add(szList.get(i));
					if(i !=0 && i%1000 == 0){
						caseProcessDao.addEntityInBatch(szListInBatch);
						szListInBatch.clear();
					}
				}
				caseProcessDao.addEntityInBatch(szListInBatch);
			}
	
			//查询出当天的发证的信息
			List<CaseProcess> fzList = collectDao.findFZxx(time);
			//将发证信息更新到数据库表中
			if(fzList.size()>0){
				List<CaseProcess> fzListInBatch = new ArrayList<CaseProcess>();
				for(int i = 0;i<fzList.size();i++){
					CaseProcess caseProcess = fzList.get(i);
					if(StringUtils.isNullOrEmpty( caseProcess.getBdcdyh() ) || caseProcess.getDjlx1().length()>3 ){
						caseProcess.setDjlx(caseProcess.getDjlx1());
					}else{
						for(Operation v :operateInit.getList()){
							if(StringUtils.isNullOrEmpty(v.getZdtzm())  ){
								continue;
							}
							String zdtzm = caseProcess.getBdcdyh().substring(12, 14);
							String dzwtzm = caseProcess.getBdcdyh().substring(19, 20);
							if(v.getPid().equals(caseProcess.getDjlx1())&&v.getZdtzm().contains(zdtzm)&&v.getDzwtzm().contains(dzwtzm)){
								caseProcess.setDjlx(v.getId());
								break;
							}
						}
					}
					System.out.println();
					fzListInBatch.add(fzList.get(i));
					if(i !=0 && i%1000 == 0){
						caseProcessDao.addEntityInBatch(fzListInBatch);
						fzListInBatch.clear();
					}
				}
				caseProcessDao.addEntityInBatch(fzListInBatch);
			}
			
			//查询出当天的退案的信息
			List<CaseProcess> taList = collectDao.findTAxx(time);
			//将退案信息更新到数据库表中
			if(taList.size()>0){
				List<CaseProcess> taListInBatch = new ArrayList<CaseProcess>();
				for(int i = 0;i<taList.size();i++){
					CaseProcess caseProcess = taList.get(i);
					if(StringUtils.isNullOrEmpty( caseProcess.getBdcdyh() ) || caseProcess.getDjlx1().length()>3 ){
						caseProcess.setDjlx(caseProcess.getDjlx1());
					}else{
						for(Operation v :operateInit.getList()){
							if(StringUtils.isNullOrEmpty(v.getZdtzm())  ){
								continue;
							}
							String zdtzm = caseProcess.getBdcdyh().substring(12, 14);
							String dzwtzm = caseProcess.getBdcdyh().substring(19, 20);
							if(v.getPid().equals(caseProcess.getDjlx1())&&v.getZdtzm().contains(zdtzm)&&v.getDzwtzm().contains(dzwtzm)){
								caseProcess.setDjlx(v.getId());
								break;
							}
						}
					}
					taListInBatch.add(taList.get(i));
					if(i !=0 && i%1000 == 0){
						caseProcessDao.addEntityInBatch(taListInBatch);
						taListInBatch.clear();
					}
				}
				caseProcessDao.addEntityInBatch(taListInBatch);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void countFlowData(String startTime,String endTime){
		List<String> timeList = DateUtil.getBetweenDate(startTime, endTime);
		for(String time : timeList){
			countFlowData(time);
		}
	}
	
	@Transactional
	public void countFlowData(String time){
		try{
			//线删除当天数据。
			daySummaryDao.deleteEntityByTime(time);
			daySummaryDao.addEntityListByTime(time);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void countExtendedCase(String startTime,String endTime){
		List<String> timeList = DateUtil.getBetweenDate(startTime, endTime);
		for(String time : timeList){
			countExtendedCase(time);
		}
	}
	
	@Transactional
	public void countExtendedCase(String time){
		if(time.compareTo(DateUtil.DateToString(new Date()))>0){
			return;
		}
		//查询每种登记类型的案件的超期时间
		List<Operation> list = operateInit.getList();
		List<ExtendedCase> extendedList = new ArrayList<ExtendedCase>();
		for(Operation v : list){
			int limitDay = (null == v.getTimelimit()||v.getTimelimit()==0)?DEFUALT_LIMIT_TIME:v.getTimelimit();
			Date kssjDate = DateUtil.getAfterDate(DateUtil.stringToDate(time, "yyyy-MM-dd"), 0-limitDay);
			String kssj = DateUtil.DateToString(kssjDate);
			//查询开始时间和结束时间之间的节假日
			List<String> lists = hilidayService.findRestTime(kssj, time);
			if(lists.contains(time)){
				return;
			}
			String startTime = findStartTime(kssj,time);
			//查询出startTime 受理的案件和最后环节的的时间
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("startTime", startTime);
			map.put("endTime", time);
			map.put("djlx", v.getId());
			List<CaseProcess> caseList = caseProcessDao.findSlCaseAndLastProcessTime(map);
			
			for(CaseProcess caseProcess : caseList){
				if(caseProcess.getDjzh().equals("28315")){
					System.out.println(caseProcess.getOperateTime()+caseProcess.getProcessNode());
				}
				int orderBy = processNodeInit.getObjMap().get(caseProcess.getProcessNode()).getOrderBy();
				//查看最后的环节是否是了案件结束的流程节点
				if(orderBy < PROCESS_OVER_NODE){
					ExtendedCase extendedCase = new ExtendedCase();
					extendedCase.setDjzh(caseProcess.getDjzh());
					extendedCase.setStartTime(startTime);
					extendedCase.setOrgCode(caseProcess.getOperateOrgCode());
					extendedCase.setTime(time);
					extendedList.add(extendedCase);
				}
			}	
			
		}
		extendedCaseService.deleteEntityByTime(time);
		extendedCaseService.addEntityListInBatch(extendedList);
	}
	
	private String findStartTime(String startTime,String endTime){
		 
		int count = hilidayService.findRestTimeCount(startTime, endTime);
		if(count>0){
			Date kssjDate = DateUtil.getAfterDate(DateUtil.stringToDate(startTime, "yyyy-MM-dd"), 0-count);
			startTime = findStartTime(DateUtil.DateToString(kssjDate),startTime);
		}
		return startTime;
	}
	
	
}

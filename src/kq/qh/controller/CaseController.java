package kq.qh.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import kq.qh.common.DeptInit;
import kq.qh.common.ProcessNodeInit;
import kq.qh.entity.CaseProcess;
import kq.qh.entity.DaySummary;
import kq.qh.entity.ExtendedCase;
import kq.qh.schedule.entity.CQxx;
import kq.qh.schedule.entity.QLRxx;
import kq.qh.schedule.entity.SPBxx;
import kq.qh.service.CaseProcessService;
import kq.qh.service.DaySummaryService;
import kq.qh.service.ExtendedCaseService;
import kq.qh.service.ViewService;
import kq.qh.util.DateUtil;
import kq.qh.util.ExcelUtil;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;


/**
 * @Package kq.qh.controller
 * @Copyright  Copyright (c) 2016 
 * @Company 苍穹广州技术中心
 * 
 * @title 案件相关操作
 * 		<p>一、  案件统计：</p>
 * 		<p>1、根据地区不同，展示各地区办理的案件数量（图形+表格）</p>
 *		<p>2、根据办理人不同，展示办理人办理的案件数据</p>
 *		<p>3、根据月份不同，展示办理案件的数据（得出办理高峰月）</p>
 *		<p>4、根据流程不同，展示办理案件的数据（每个流程的时间）</p>
 *		<p>5、根据取号类型不同，~~~~</p>
 *		<p>二、案件流程</p>
 *		<p> 根据案件编号or登记字号or不动产证号or不动产证明号or日期or经办人  查看案件流程信息。</p>
 *		<p>	展示内容：</p>
 *		<p>	1、案件编号，2、取号类型（登记字号or不动产证号or不动产证明号），3、号码，
 *			4、流程名，5、流程结束时间，6、地区名（orgName），7、经办人 </p>
 *			
 * @author ylf
 * @date 2016年8月18日 上午10:01:36
 * @version V1.0
 */
@Controller
@RequestMapping("/case")
public class CaseController {
	
	@Resource
	private CaseProcessService caseProcessService;
	@Resource
	private ProcessNodeInit processNodeInit;
	@Resource
	private DaySummaryService daySummaryService;
	@Resource
	private ViewService viewService;
	@Resource
	private ExtendedCaseService extendedCaseService;
	@Resource 
	private DeptInit deptInit;
	/**
	 * @Title 跳转到业务量监控页面
	 * 		<p>1、根据地区不同，展示各地区办理的案件数量（图形+表格）</p>
	 *		<p>2、根据办理人不同，展示办理人办理的案件数据</p>
	 *		<p>3、根据月份不同，展示办理案件的数据（得出办理高峰月）</p>
	 *		<p>4、根据流程不同，展示办理案件的数据（每个流程的时间）</p>
	 *		<p>5、根据取号类型不同，~~~~</p>
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午10:51:59
	 * @version V1.0
	 */
	
	@RequestMapping("/countDq")
	public String countDq(ModelMap map){
		String startTime = DateUtil.getNowMonthBegin();
		String endTime = DateUtil.getMonthEnd(startTime);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return "case/countDq";
	}
	
	/**
	 * @Title 跳转业务流程监控页面
	 *		<p>二、案件流程</p>
	 *		<p> 根据案件编号or登记字号or不动产证号or不动产证明号or日期or经办人  查看案件流程信息。</p>
	 *		<p>	展示内容：</p>
	 *		<p>	1、案件编号，2、取号类型（登记字号or不动产证号or不动产证明号），3、号码，
	 *			4、流程名，5、流程结束时间，6、地区名（orgName），7、经办人 </p>
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午10:53:14
	 * @version V1.0
	 */
	@RequestMapping("/flow")
	public String flow(String startTime, String endTime,String processNode,String djlx,String caseOrgCode,ModelMap map){
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = DateUtil.getNowMonthBegin();
		}
		if(StringUtils.isNullOrEmpty(endTime)){
			endTime = DateUtil.getMonthEnd(startTime);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		/*if(!StringUtils.isNullOrEmpty(processNode)){
			processNode = URLEncoder.encode(processNode);
		}*/
		map.put("processNode",processNode);
		map.put("orgCode", caseOrgCode);
		map.put("djlx", djlx);
//		map.put("item", caseProcess);
		return "case/flow";
	}
	/**
	 * 业务流程监控数据查询
	 * @param oprid			登记类型
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param processNode	环节
	 * @param caseProcess
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/findTheLatestData",produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<String,Object> findTheLatestData(String oprid,String startTime,String endTime,String processNode,CaseProcess caseProcess,@RequestParam(defaultValue="10") int rows,@RequestParam(defaultValue="1")int page ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if(!StringUtils.isNullOrEmpty(processNode)){
			processNode = URLDecoder.decode(processNode);
			caseProcess.setProcessNode(processNode);
		}
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(rows);	
		List<CaseProcess> caseProcessList = caseProcessService.findTheLatestData(oprid,startTime,endTime,caseProcess,pageUtil);
		map.put("rows", caseProcessList);
		map.put("total", caseProcessService.countTheLatestData(oprid,startTime,endTime,caseProcess));
		return map;
	} 
	/**
	 * 主面数据统计双击弹窗出案件详情查询
	 * @param oprid
	 * @param startTime
	 * @param endTime
	 * @param processNode
	 * @param caseProcess
	 * @param rows
	 * @param page
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/findTheLatest",produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<String,Object> findTheLatest(String oprid,String startTime,String endTime,String processNode,CaseProcess caseProcess,@RequestParam(defaultValue="10") int rows,@RequestParam(defaultValue="1")int page ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if(!StringUtils.isNullOrEmpty(processNode)){
			processNode = URLDecoder.decode(processNode);
			caseProcess.setProcessNode(processNode);
		}
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(rows);	
		List<CaseProcess> caseProcessList = caseProcessService.findTheLatest(oprid,startTime,endTime,caseProcess,pageUtil);
		map.put("rows", caseProcessList);
		map.put("total", caseProcessService.countTheLatest(oprid,startTime,endTime,caseProcess));
		return map;
	}
	/**
	 * 业务流程监控中双击案件弹出案件详情信息
	 * @param startTime
	 * @param endTime
	 * @param caseProcess
	 * @return
	 */
	@RequestMapping("/findEntityList")
	@ResponseBody
	public List<CaseProcess> findEntityList(String startTime, String endTime,CaseProcess caseProcess){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("ajbh", caseProcess.getAjbh());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("caseProcess", caseProcess);
		List<CaseProcess> caseProcessList = caseProcessService.findEntityList(map);		
		return caseProcessList;
	}
	/**
	 * 主页数据统计页面数据查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @return
	 */
	@RequestMapping("/countCaseDetial")
	@ResponseBody
	public Map<String,Object> countCaseDetial(String startTime,String endTime,DaySummary daySummary ){
		Map<String,Object> map = daySummaryService.countCaseDetial(startTime,endTime,daySummary);
		return map;
	}
	/**
	 * 业务量监控数据导出Excel文档
	 * @param city
	 * @param nodes
	 * @param selfSystem
	 * @param startTime
	 * @param endTime
	 * @param response
	 * @param daySummary
	 */
	@RequestMapping("/exportExcels")
	public void exportExcels(String city ,String nodes,boolean selfSystem,String startTime, String endTime,HttpServletResponse response,DaySummary daySummary){
		List<String> djlxList = new ArrayList<String>();
		if(nodes.contains(",")){
			nodes = nodes.substring(0, nodes.length()-1);
			for(String value :nodes.split(",")){
				djlxList.add(value);
			}
		}
		
		List<String[]> list = daySummaryService.countReports(startTime,endTime,daySummary,djlxList);
		ExcelUtil excel = new ExcelUtil();
		int length =1;
		if (djlxList.size()>0) {
			length++;
		}
		String []str = new String[processNodeInit.getList().size()+length]; 
		
		if (djlxList.size()>0) {
			str[0] = "地区";
			str[1] = "登记类型";
		} else {
			str[0] = "地区";
		}
		for(int i = 0;i<processNodeInit.getList().size();i++){
			str[i+length] = processNodeInit.getList().get(i).getName();
		}
		excel.writeExcel(list, str, "统计报表", response);
	}
	
	
	/**
	 * 业务量监控数据查询
	 * @param startTime
	 * @param endTime
	 * @param daySummary
	 * @param nodes		一个登记类型的集合  List
	 * @return
	 */
	@RequestMapping("/countReports")
	@ResponseBody
	public List<String[]> countReports( String startTime,String endTime,DaySummary daySummary, String nodes){
		List<String> djlxList = new ArrayList<String>();
		if(nodes.contains(",")){
			nodes = nodes.substring(0, nodes.length()-1);
			for(String value :nodes.split(",")){
				djlxList.add(value);
			}
		}
		return daySummaryService.countReports( startTime,endTime,daySummary,djlxList);
	}
	/**
	 * 产权信息查询
	 * @param cqxx
	 * @return
	 */
	@RequestMapping("/findCq")
	@ResponseBody
	public List<CQxx> findCq(CQxx cqxx){
		return viewService.findCq(cqxx);
	}
	/**
	 * 权利人信息查询
	 * @param qlrxx
	 * @return
	 */
	@RequestMapping("/findQlr")
	@ResponseBody
	public List<QLRxx> findQlr(QLRxx qlrxx){
		List<QLRxx> list = viewService.findQlr(qlrxx);
		return list;
	}
	/**
	 * 审批表信息查询
	 * @param spbxx
	 * @return
	 */
	@RequestMapping("/findSpb")
	@ResponseBody
	public List<SPBxx> findSpb(SPBxx spbxx){
		return viewService.findSpb(spbxx);
	}
	/**
	 * 超期案件导出Excel文档
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @param response
	 */
	@RequestMapping("/exportExtendedCase")
	public void exportExtendedCase(String startTime, String endTime,ExtendedCase extendedCase,HttpServletResponse response){
		List<CaseProcess> list = extendedCaseService.findExtend(startTime,endTime,extendedCase,null);
		for (CaseProcess caseProcess2 : list) {
			caseProcess2.setDept(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
		}
		//org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		String[] names = new String[]{"ajbh","djzh","operator","processNode","operateTime","slsj","extendedTime"};
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.writeExcel(list,"超期案件",names,CaseProcess.class,response);
	}
}

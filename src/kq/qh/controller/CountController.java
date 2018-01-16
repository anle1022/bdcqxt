package kq.qh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.OrganizationInit;
import kq.qh.entity.CaseProcess;
import kq.qh.entity.DaySummary;
import kq.qh.entity.ExtendedCase;
import kq.qh.service.CountService;
import kq.qh.service.ExtendedCaseService;
import kq.qh.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/count")
public class CountController {
	
	@Autowired
	private CountService countService;
	@Resource
	private OrganizationInit organizationInit;
	@Resource
	private DeptInit deptInit;
	
	@Autowired
	private ExtendedCaseService extendedCaseService;
	/**
	 * 
	 * 1、地区流程信息数据查询
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public Map<String,Object> findALL(String startTime,String endTime){
		Map<String,Object> map = countService.findAll(startTime,endTime);
		return map;
	}
	/*
	@RequestMapping("/countExtendedCase")
	@ResponseBody
	public Map<String,Object> findExtendedCase(String startTime,String endTime){
		Map<String,Object> map = countService.countExtendedCase(startTime,endTime);
		return map;
	}*/
	/**
	 * 主页统计信息数据查询
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	@RequestMapping("/findDaySummaryInPage")
	@ResponseBody
	public Map<String,Object> findDaySummaryInPage(String startTime,String endTime,PageUtil page){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", countService.findDaySummaryInPage(startTime, endTime,new DaySummary(),page));
		map.put("total",countService.count(startTime,endTime,new DaySummary()));
		return map;
	}
	/**
	 * 超期案件数据查询
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @return
	 */
	@RequestMapping(value="/findExtendedCaseList")
	@ResponseBody
	public List<ExtendedCase> findExtendedCaseList(String startTime,String endTime,ExtendedCase extendedCase){
		List<ExtendedCase> list = extendedCaseService.findExtendedCaseList(startTime,endTime, extendedCase);
		return list;
	}
	
	@RequestMapping(value="/countFindExtend")
	@ResponseBody
	public int countFindExtend(String startTime,String endTime){
		return extendedCaseService.countFindExtend(startTime,endTime,new ExtendedCase());
	}
	
	/**
	 * 超期案件详情弹窗数据查询
	 * @param startTime
	 * @param endTime
	 * @param extendedCase
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/findExtend")
	@ResponseBody
	public Map<String,Object> findExtend(String startTime, String endTime,ExtendedCase extendedCase,@RequestParam(defaultValue="10") int rows,@RequestParam(defaultValue="1")int page){
		Map<String,Object> map = new HashMap<String,Object>();
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(rows);	
		List<CaseProcess> list = extendedCaseService.findExtend(startTime,endTime,extendedCase,pageUtil);
		for (CaseProcess caseProcess2 : list) {
			caseProcess2.setDept(deptInit.getDeptMap().get(caseProcess2.getOperateOrgCode()));
		}
		map.put("rows", list);
		map.put("total",extendedCaseService.countFindExtend(startTime,endTime,extendedCase));
		return map;
	};
}

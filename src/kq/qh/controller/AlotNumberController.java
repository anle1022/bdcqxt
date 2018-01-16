package kq.qh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.NumberTypeInit;
import kq.qh.common.TypeInit;
import kq.qh.dao.dataSource0.TypeDao;
import kq.qh.entity.AlotNumber;
import kq.qh.entity.NumberType;
import kq.qh.entity.SelfSystem;
import kq.qh.entity.Type;
import kq.qh.service.AlotNumberService;
import kq.qh.service.DeptService;
import kq.qh.service.NumberTypeService;
import kq.qh.util.DateUtil;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;


/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * @Entity kq.qh.AlotNumber
 * @title 根据不同的取号类型，系统，
 * 		<p>1.1.1、登记字号监控</p>
 * 		<p>1.1.2、不动产权证号监控</p>
 * 		<p>1.1.3、不动产证明号监控</p>
 *  
 * @author ylf
 * @date 2016年8月16日 下午3:57:37
 * @version V1.0
 */
@Controller
@RequestMapping("/alotNumber")
public class AlotNumberController {
	
	@Resource
	private AlotNumberService alotNumberService;
	@Resource
	private NumberTypeService numberTypeService;
	@Resource
	private DeptService deptService;
	@Resource
	private DeptInit deptInit;
	@Resource
	private TypeInit typeInit;
	@Resource
	private NumberTypeInit numberTypeInit;
	@Resource
	private TypeDao typeDao;
	/**
	 * 
	 * @Title  跳转到取号监控页面
	 * 		<p>1.1.1、登记字号监控</p>
	 * 		<p>1.1.2、不动产权证号监控</p>
	 * 		<p>1.1.3、不动产证明号监控</p>
	 * @return url
	 * @author ylf
	 * @date 2016年8月18日 上午10:39:41
	 * @version V1.0
	 */
	@RequestMapping("/monitor")
	public String monitor(ModelMap map){
		String startTime = DateUtil.getNowMonthBegin();
		String endTime = DateUtil.getMonthEnd(startTime);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return "alotNumber/monitor";
	}
	/**
	 * 跳转到取号统计页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/monitorDj")
	public String monitorDj(ModelMap map){
		String startTime = DateUtil.getNowMonthBegin();
		String endTime = DateUtil.getMonthEnd(startTime);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return "alotNumber/monitorDj";
	}
	/**
	 * 取号监控页面的数据查询
	 * @param type
	 * @param numberType
	 * @param selfSystem
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @param rows			
	 * @param page
	 * @return
	 */
	@RequestMapping("/findEntityInPage")
	@ResponseBody
	public Map<String , Object> findEntityInPage(Type type,NumberType numberType,SelfSystem selfSystem,String startTime,String endTime,AlotNumber alotNumber,@RequestParam(defaultValue="10") int rows,@RequestParam(defaultValue="1")int page ){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = DateUtil.getNowMonthBegin();
		}
		if(StringUtils.isNullOrEmpty(endTime)){
			endTime = DateUtil.getMonthEnd(startTime);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(rows);	
		List<AlotNumber> alotNumberList = alotNumberService.findEntityInPage(type,numberType,selfSystem,startTime,endTime,alotNumber,pageUtil);
		map.put("rows", alotNumberList);
		map.put("total", alotNumberService.count(numberType,selfSystem,startTime,endTime,alotNumber));
		return map;
	}
	
	/**
	 * 取号统计页面的数据查询
	 * @param numberType
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping("/findEntityInPages")
	@ResponseBody
	public Map<String , Object>  findEntityInPages(NumberType numberType,String startTime,String endTime,AlotNumber alotNumber,@RequestParam(defaultValue="10") int rows,@RequestParam(defaultValue="1")int page ){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = DateUtil.getNowMonthBegin();
		}
		if(StringUtils.isNullOrEmpty(endTime)){
			endTime = DateUtil.getMonthEnd(startTime);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(rows);	
		map.put("total", alotNumberService.countfind(numberType,startTime,endTime,alotNumber));
		map.put("rows", alotNumberService.findEntityInPages(numberType, startTime, endTime, alotNumber, pageUtil));
		return map;
	}
	
//	public String show(){
//		return "";
//	}
	
//	@RequestMapping("/deleteEntity")
//	@ResponseBody
//	public String deleteEntity(String id) {
//		return alotNumberService.deleteEntity(id);
//	
//	}
	
}

package kq.qh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.NumberType;
import kq.qh.entity.SelfSystem;
import kq.qh.entity.SerialNumber;
import kq.qh.service.SerialNumberService;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 *
 * @title   流水号配置
 * 		  <p>1、登记字号流水号配置</p>
 * 		  <p>2、不动产权证号流水号配置</p>
 * 		  <p>3、不动产证明 流水号配置</p>
 *  
 * @author ylf
 * @date 2016年8月18日 上午10:27:02
 * @version V1.0
 */
@Controller
@RequestMapping("/serialNumber")
public class SerialNumberController {
	
	@Resource
	private SerialNumberService serialNumberService;
	/**
	 * 跳转到流水号配置页面
	 * @return
	 */
	@RequestMapping("/set")
	public String set(){
		return "serialNumber/set";
	}
	/**
	 * 流水号配置页面添加功能
	 * @return
	 */
	@RequestMapping(value="/addEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addEntity(SerialNumber serialNumber){
		serialNumber.setId(serialNumber.getNumberTypeId()+"_"+serialNumber.getYear());
		return  serialNumberService.addEntity(serialNumber);
		
	}
	/**
	 * 流水号配置页面修改功能
	 * @return
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateEntity(SerialNumber serialNumber){
		return serialNumberService.updateEntity(serialNumber);
	}
	/**
	 * 流水号配置页面数据查询
	 * @param serialNumber
	 * @param numberType
	 * @param page
	 * @return
	 */
	@RequestMapping("/findEntityListInPage")
	@ResponseBody
	public Map<String,Object> findEntityListInPage(SerialNumber serialNumber,NumberType numberType,SelfSystem selfSystem,PageUtil page){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", serialNumberService.findEntityListInPage(serialNumber,numberType,selfSystem,page));
		map.put("total",serialNumberService.count(serialNumber,numberType,selfSystem));
		return map;
	}
	/**
	 * 流水号配置页面删除功能
	 * @return
	 */
	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteEntity(String pkid){
		return serialNumberService.deleteEntity(pkid);
	}
}

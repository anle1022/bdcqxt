package kq.qh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.NumberType;
import kq.qh.entity.Type;
import kq.qh.service.NumberTypeService;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @title 取号类型配置
 * 		  <p>1、查询取号类型</p>
 * 		  <p>2、添加取号类型</p>
 * 		  <p>3、修改</p>
 * 		  <p>4、删除</p>
 * 	对应Entity @kq.qh.NumberType 
 * @author ylf
 * @date 2016年8月18日 上午10:24:27
 * @version V1.0
 */
@Controller
@RequestMapping("/numberType")
public class NumberTypeController {
	
	@Resource
	private NumberTypeService numberTypeService;
	
	/**
	 * 
	 * @Title 跳转到取号类型配置页面
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午11:05:36
	 * @version V1.0
	 */
	@RequestMapping("/set")
	public String set(){
		return "numberType/set";
	}
	
	/**
	 * 
	 * @Title  添加or修改取号类型
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午11:10:34
	 * @version V1.0
	 */
	@RequestMapping(value="/addEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addEntity(NumberType numberType){
		return numberTypeService.addEntity(numberType);
		
	}
	
	/**
	 * 
	 * @Title  添加or修改取号类型
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午11:10:34
	 * @version V1.0
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateEntity(NumberType numberType){
		return numberTypeService.updateEntity(numberType);

	}
	/**
	 * 
	 * @Title  删除取号类型
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月18日 上午11:10:51
	 * @version V1.0
	 */
	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteEntity(String id){
		return numberTypeService.deleteEntity(id);
		
	}
	/**
	 * 取号类型配置数据查询
	 * @param type
	 * @param numberType
	 * @param page
	 * @return
	 */
	@RequestMapping("/findEntityListInPage")
	@ResponseBody
	public Map<String,Object> findEntityListInPage(Type type,NumberType numberType,PageUtil page){
		Map<String,Object> map = new HashMap<String,Object>();
		List<NumberType> list = numberTypeService.findEntityListInPage(type,numberType,page);
//		map.put("rows", numberTypeService.findEntityListInPage(type,numberType,page));
		map.put("rows", list);
		map.put("total",numberTypeService.count(type,numberType));
		return map;
	}
	/**
	 * 查询所有的分区类型
	 * @param numberType
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<NumberType> findAll(NumberType numberType){
		return numberTypeService.findAll(numberType);
	}
	
	@RequestMapping(value="/validateEntity" )
	@ResponseBody
	public Object validateEntity(NumberType numberType){
		return numberTypeService.validateEntity(numberType);
	}
}

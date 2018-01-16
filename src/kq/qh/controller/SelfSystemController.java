package kq.qh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.SelfSystem;
import kq.qh.service.SelfSystemService;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @title 自建系统相关操作
 * 		  <p>1、查询自建系统</p>
 * 		  <p>2、添加自建系统</p>
 * 		  <p>3、修改自建系统</p>
 *  	  <p>4、删除自建系统</p>
 * @author ylf
 * @date 2016年8月18日 上午10:21:46
 * @version V1.0
 */
@Controller
@RequestMapping("/selfSystem")
public class SelfSystemController {
	
	@Resource
	private SelfSystemService selfSystemService;
	/**
	 * 跳转到自建系统配置页面
	 * @return
	 */
	@RequestMapping("/set")
	public String set(){
		return "selfSystem/set";
	}
	/**
	 * 自建系统配置数据查询
	 * @param selfSystem
	 * @return
	 */
	@RequestMapping("/findEntityListInPage")
	@ResponseBody
	public Map<String,Object> findEntityListInPage(SelfSystem selfSystem,PageUtil page){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", selfSystemService.findEntityListInPage(selfSystem,page));
		map.put("total",selfSystemService.count(selfSystem));
		return map;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SelfSystem> findAll(){
		return selfSystemService.findAll();
	}
	/**
	 * 自建系统配置页面添加功能
	 * @param selfSystem
	 * @return
	 */
	@RequestMapping(value="/addEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addEntity(SelfSystem selfSystem){
		
		return selfSystemService.addEntity(selfSystem);
	}
	/**
	 * 自建系统配置页面修改功能
	 * @param selfSystem
	 * @return
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateEntity(SelfSystem selfSystem){
		int i = selfSystemService.updateEntity(selfSystem);
		if(i>0){
			return "修改成功！";
		}else{
			return "修改失败，请联系管理员.";
		}
	}
	
//	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String deleteEntity(SelfSystem selfSystem){
//		return selfSystemService.addEntity(selfSystem);
//	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteEntitys(String id){
		int i =selfSystemService.deleteEntity(id);
		if(i>0){
			return "ok";
		}else{
			return "error";
		}
	};
}

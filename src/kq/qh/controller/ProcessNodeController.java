package kq.qh.controller;


import java.util.List;

import javax.annotation.Resource;

import kq.qh.entity.ProcessNode;
import kq.qh.service.ProcessNodeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/processNode")
public class ProcessNodeController {
	
	@Resource
	private ProcessNodeService processNodeService;
	/**
	 * 跳转到流程节点为监控配置页面
	 * @param processNode
	 * @return
	 */
	@RequestMapping("/set")
	public String set(ProcessNode processNode){
		return "case/set";
	}
	/**
	 * 各流程节点查询
	 * @return
	 */
	@RequestMapping("/findEntityList")
	@ResponseBody
	public List<ProcessNode> findEntityList(){
		
		return processNodeService.findEntityList();
	}
	/**
	 * 流程节点为监控配置页面数据查询
	 * @param processNode
	 * @return
	 */
	@RequestMapping("/findEntityListOrder")
	@ResponseBody
	public List<ProcessNode> findEntityListOrder(ProcessNode processNode ){

		return processNodeService.findEntityListOrder(processNode);
	}
	/**
	 * 流程节点为监控配置页面添加功能
	 * @param processNode
	 * @return
	 */
	@RequestMapping(value="/addEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addEntity(ProcessNode processNode){
		return processNodeService.addEntity( processNode);
 
	}
	/**
	 * 流程节点为监控配置页面修改功能
	 * @param processNode
	 * @return
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateEntity(ProcessNode processNode){
		return processNodeService.updateEntity( processNode);
	}	
	/**
	 * /**
	 * 流程节点为监控配置页面删除功能
	 * @param processNode
	 * @return
	 */
	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteEntity(String id){
		return processNodeService.deleteEntity(id);
	}
}

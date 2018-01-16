package kq.qh.controller;


import java.util.List;

import javax.annotation.Resource;

import kq.qh.entity.Dept;
import kq.qh.service.DeptService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DeptController {
	
	@Resource
	private DeptService deptService;;
	
	
	/**
	 * 跳转到登记部门代号配置页面
	 * @param dept
	 * @return
	 */
	@RequestMapping("/set")
	public String set(Dept dept){
		return "dept/set";
	}
	/**
	 * 登记部门代号配置页面数据查询
	 * @param dept
	 * @return
	 */
	@RequestMapping("/findEntityList")
	@ResponseBody
	public List<Dept> findEntityList(Dept dept){
		return deptService.findEntityList(dept);
	}
	
	
	/**
	 * 登记部门代号配置页面数据添加功能
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="/addEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addEntity(Dept dept){
		
		return deptService.addEntity( dept);
 
	}
	/**
	 * 登记部门代号配置页面数据修改功能
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateEntity(Dept dept){
		return deptService.updateEntity( dept);
	}	
	/**
	 * 登记部门代号配置页面数据删除功能
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="/deleteEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteEntity(String id){
		return deptService.deleteEntity(id);
	}
}

package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.ProcessNodeInit;
import kq.qh.dao.dataSource0.ProcessNodeDao;
import kq.qh.entity.ProcessNode;

import org.springframework.stereotype.Service;

@Service
public class ProcessNodeService {

	@Resource
	private ProcessNodeDao processNodeDao;
	
	@Resource
	private ProcessNodeInit processNodeInit;
	
	/**
	 * 各流程节点查询
	 * @return
	 */
	public List<ProcessNode> findEntityList() {
		return processNodeDao.findEntityList();
	}
	/**
	 * 流程节点为监控配置页面数据查询
	 * @param processNode
	 * @return
	 */
	public List<ProcessNode> findEntityListOrder(ProcessNode processNode) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("key", processNode.getKey());
		map.put("name", processNode.getName());
		map.put("orderBy", processNode.getOrderBy());
		return processNodeDao.findEntityListOrder(map);
	}
	/**
	 * 流程节点为监控配置页面添加功能
	 * @param processNode
	 * @return
	 */
	public String addEntity(ProcessNode processNode) {
		//验证数据是否存在
		int count = validateEntity(processNode);
		if(count == 1){
			return "该节点关键字或者名称已存在！";
		}
		int i =  processNodeDao.addEntity(processNode);
		if(i>0){
			processNodeInit.destory();
			return "添加成功！";
		}else{
			return "添加失败！请联系技术人员。";
		}
	}

	private int validateEntity(ProcessNode processNode) {
		return processNodeDao.validateEntity(processNode);
	}
	/**
	 * 流程节点为监控配置页面修改功能
	 * @param processNode
	 * @return
	 */
	public String  updateEntity(ProcessNode processNode) {
		int i = processNodeDao.updateEntity( processNode);
		if(i>0){
			processNodeInit.destory();
			return "修改成功！";
		}else{
			return "修改失败，请联系管理员.";
		}
	}
	/**
	 * 流程节点为监控配置页面删除功能
	 * @param processNode
	 * @return
	 */
	public String deleteEntity(String id){
		int i = processNodeDao.deleteEntity(id);
		if(i>0){
			processNodeInit.destory();
			return "ok";
		}else{
			return "error";
		}
	}
	
}

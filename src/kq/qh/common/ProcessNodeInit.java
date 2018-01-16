package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kq.qh.entity.ProcessNode;
import kq.qh.service.ProcessNodeService;

/**
 * 流程节点缓存
 * @author Administrator
 *
 */
@Service
public class ProcessNodeInit {
	
	@Resource
	private ProcessNodeService processNodeService;
	
	private Map<String,String> map; 
	
	private List<ProcessNode> list; 
	
	private Map<String,ProcessNode> objMap;
	
	
	
	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String,ProcessNode> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public List<ProcessNode> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public void init(){
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, ProcessNode>();
		this.list = processNodeService.findEntityList();
		for(ProcessNode value:list){
			this.map.put(value.getKey(), value.getName());
			this.objMap.put(value.getKey(), value);
		}
	}

	public void destory(){
		if(null != list && list.size()>0){
			this.map.clear();
			this.objMap.clear();
			this.list.clear();
		}
	}

}

package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.Dept;
import kq.qh.service.DeptService;

import org.springframework.stereotype.Service;

/**
 * 登记机构缓存
 * @author Administrator
 *
 */
@Service
public class DeptInit {
	
	@Resource
	private DeptService deptService;
	
	private Map<String,String> map; 
	
	private List<Dept> list; 
	
	private Map<String,Dept> objMap;
	
	private Map<String,Dept> deptMap;
	
	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String, Dept> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public Map<String, Dept> getDeptMap() {
		if(null == this.deptMap || this.deptMap.isEmpty() ){
			init();
		}
		return deptMap;
	}
	public List<Dept> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public void init(){
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, Dept>();
		this.deptMap = new HashMap<String, Dept>();
		this.list = deptService.findEntityList(null);
		for(Dept value:list){
			this.map.put(value.getOrgCode(), value.getOrgName());
			this.objMap.put(value.getOrgCode(), value);
			this.deptMap.put(value.getId(), value);
		}
	}
	
	public void destory(){
		if(null != list && list.size()>0){
			this.map.clear();
			this.objMap.clear();
			this.deptMap.clear();
			this.list.clear();
		}
	}
}

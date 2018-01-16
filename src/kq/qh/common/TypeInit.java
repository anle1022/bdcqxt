package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.Type;
import kq.qh.service.TypeService;

import org.springframework.stereotype.Service;


@Service
public class TypeInit {
	
	@Resource
	private TypeService typeService;
	
	private Map<String,String> map; 
	
	private List<Type> list; 
	
	private Map<String,Type> objMap;
	
	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String, Type> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public List<Type> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public void init(){
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, Type>();
		this.list = typeService.findAll();
		for(Type value:list){
			this.map.put(value.getId(), value.getName());
			this.objMap.put(value.getId(), value);
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

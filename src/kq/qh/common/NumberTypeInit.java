package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kq.qh.entity.NumberType;
import kq.qh.service.NumberTypeService;

/**
 * 取号类型缓存
 * @author Administrator
 *
 */
@Service
public class NumberTypeInit {
	
	@Resource
	private NumberTypeService numberpeTyService;
	private NumberType numberType;
	
	private Map<String,String> map; 
	
	private List<NumberType> list; 
	
	private Map<String,NumberType> objMap;
	
	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String,NumberType> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public List<NumberType> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public void init(){
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, NumberType>();
		this.list = numberpeTyService.findAll(numberType);
		for(NumberType value:list){
			this.map.put(value.getId(), value.getText());
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

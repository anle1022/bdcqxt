package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kq.qh.entity.RegistType;
import kq.qh.service.RegistTypeService;

/**
 * 登记类型Init
 * @author Administrator
 *
 */
@Service
public class RegistTypeInit {
	
	@Resource
	private RegistTypeService registTypeService;
	
	private Map<String,String> map; 
	
	private List<RegistType> list; 
	
	private List<RegistType> firstList;
	private List<RegistType> secondList;
	
	private Map<String,String> parentNameMap; 
	private Map<String,RegistType> objMap;
	
	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String,RegistType> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public List<RegistType> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public List<RegistType> getFirstList() {
		if(null == this.firstList || this.firstList.size() == 0){
			init();
		}
		return firstList;
	}

	public List<RegistType> getSecondList() {
		if(null == this.secondList || this.secondList.size() == 0){
			init();
		}
		return secondList;
	}

	public Map<String, String> getParentNameMap() {
		if(null == this.parentNameMap || this.parentNameMap.isEmpty() ){
			init();
		}
		return parentNameMap;
	}

	public void init(){
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, RegistType>();
		this.parentNameMap = new HashMap<String, String>();
		this.list = registTypeService.findTypeByPid();
		this.firstList =  registTypeService.findMenuByPid(null,this.list);
		for(RegistType value:firstList){
			this.map.put(value.getText(), value.getId());
			this.objMap.put(value.getId(), value);
		}
		this.list.removeAll(firstList);
		this.secondList = this.list;
		for(RegistType value:secondList){
			RegistType r = this.objMap.get(value.getPid());
			if(null == r){
				continue;
			}
			String pName = r.getText();
			this.parentNameMap.put(value.getId(), value.getText()+"_"+pName);
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

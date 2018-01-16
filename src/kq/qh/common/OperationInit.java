package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.dao.dataSource0.OperationDao;
import kq.qh.entity.Operation;

import org.springframework.stereotype.Service;

/**
 * 登记类型缓存
 * @author Administrator
 *
 */
@Service
public class OperationInit {
	@Resource
	private OperationDao operationDao;
	
	private List<Operation> list; 
	
	private Map<String,Operation> objMap;
	
	private Map<String,String> Map;
	
	public List<Operation> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}
	public Map<String, Operation> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	public Map<String, String> getMap() {
		if(null == this.Map || this.Map.isEmpty() ){
			init();
		}
		return Map;
	}
 
	public void init(){
		this.list = operationDao.findEntityList();
		this.objMap = new HashMap<String, Operation>();
		this.Map = new HashMap<String, String>();
		for(Operation value:list){
			this.objMap.put(value.getId(), value);
			this.Map.put(value.getName(), value.getId());
		}
	}
	
	public Operation findFirstParent(String id){
		Operation o = getObjMap().get(id);
		if(null != o && null != o.getPid()){
			o = findFirstParent(o.getPid());
		}
		return o;
	}
}

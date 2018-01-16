package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.ProcessNode;

public interface ProcessNodeDao {

	List<ProcessNode> findEntityList();

	List<ProcessNode> findEntityListOrder(Map<String, Object> map);
	
	int addEntity(ProcessNode processNode);

	int validateEntity(ProcessNode processNode);

	int updateEntity(ProcessNode processNode);
	
	public int deleteEntity(String id);
	
}

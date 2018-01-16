package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kq.qh.entity.AlotNumber;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月25日 下午4:44:36
 * @version V1.0
 */
@Repository
public interface AlotNumberDao {
	
	List<AlotNumber> findEntityInPage(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public int deleteEntity(String id);
	

	List<AlotNumber> findEntityInPages(Map<String, Object> map);
	
	public int countfind(Map<String, Object> map);
	
	
	
	Integer addEntity(AlotNumber alotNumber);
	
	List<AlotNumber> findEntityByCondition(AlotNumber alotNumber);
	
	int addEntityInBatch(List<AlotNumber> alotNumberlist);

	void deleteEntityInBatch(List<String> numbers);

	void updateBZ(Map<String, Object> map);
}

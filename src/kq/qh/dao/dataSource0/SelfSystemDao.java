package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.SelfSystem;

import org.springframework.stereotype.Repository;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月25日 上午9:45:39
 * @version V1.0
 */
@Repository
public interface SelfSystemDao {

	/**
	 * @Title  SelfSystemDao.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:47:45
	 * @version V1.0
	 */
	public int addEntity(SelfSystem selfSystem);

	/**
	 * @Title  SelfSystemDao.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:49:37
	 * @version V1.0
	 */
	public int updateEntity(SelfSystem selfSystem);

	/**
	 * @Title  SelfSystemDao.java
	 * @param map
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午10:00:04
	 * @version V1.0
	 */
	public List<SelfSystem> findEntityListInPage(Map<String, Object> map);

	/**
	 * @Title  SelfSystemDao.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午10:00:19
	 * @version V1.0
	 */
	public int count(SelfSystem selfSystem);

	/**
	 * @Title  SelfSystemDao.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午11:05:30
	 * @version V1.0
	 */
	public int validateEntity(SelfSystem selfSystem);
	
	SelfSystem findEntityById(String id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteEntity(String id);

	public List<SelfSystem> findAll();
	
 
	
}

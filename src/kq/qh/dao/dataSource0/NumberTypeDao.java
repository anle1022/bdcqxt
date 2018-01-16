package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.NumberType;

import org.springframework.stereotype.Repository;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月23日 上午10:40:40
 * @version V1.0
 */
@Repository
public interface NumberTypeDao {

	/**
	 * @Title  NumberTypeDao.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午10:41:21
	 * @version V1.0
	 */
	int addEntity(NumberType numberType);

	/**
	 * @Title  NumberTypeDao.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:21:07
	 * @version V1.0
	 */
	int updateEntity(NumberType numberType);

	/**
	 * @Title  NumberTypeDao.java
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:22:19
	 * @version V1.0
	 */
	int deleteEntity(String id);

	/**
	 * @Title  NumberTypeDao.java
	 * @param map
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:33:18
	 * @version V1.0
	 */
	List<NumberType> findEntityListInPage(Map<String, Object> map);

	/**
	 * @Title  NumberTypeDao.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 下午2:01:08
	 * @version V1.0
	 */
	int count(Map<String, Object> map);

	/**
	 * @Title  NumberTypeDao.java
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 下午2:02:05
	 * @version V1.0
	 */
	List<NumberType> findAll(NumberType numberType);
	NumberType findAllOne(NumberType numberType);

	/**
	 * @Title  NumberTypeDao.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月24日 上午9:33:49
	 * @version V1.0
	 */
	int validateEntity(NumberType numberType);

	NumberType findEntityById(String id);
}

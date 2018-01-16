package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.SerialNumber;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月19日 下午5:54:53
 * @version V1.0
 */
/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月19日 下午5:54:53
 * @version V1.0
 */
public interface SerialNumberDao {

	/**
	 * @Title  SerialNumberDao.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 上午9:08:31
	 * @version V1.0
	 */
	int addEntity(SerialNumber serialNumber);

	/**
	 * @Title  SerialNumberDao.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 上午9:08:39
	 * @version V1.0
	 */
	int updateEntity(SerialNumber serialNumber);

	/**
	 * @Title  SerialNumberDao.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 下午2:09:10
	 * @version V1.0
	 */
	int count(Map<String, Object> map);

	/**
	 * @Title  SerialNumberDao.java
	 * @param map
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 下午2:09:38
	 * @version V1.0
	 */
	List<SerialNumber> findEntityListInPage(Map<String, Object> map);

	/**
	 * @Title  SerialNumberDao.java
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 下午5:23:39
	 * @version V1.0
	 */
	int deleteEntity(String id);

	/**
	 * @Title  SerialNumberDao.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 下午5:47:14
	 * @version V1.0
	 */
	List<SerialNumber> findEntityList(SerialNumber serialNumber);

	/**
	 * @Title  SerialNumberDao.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 下午2:47:11
	 * @version V1.0
	 */
	int validateEntity(SerialNumber serialNumber);
	
	List<SerialNumber> validateEntityFind(SerialNumber serialNumber);
	
	SerialNumber findEntityById(SerialNumber serialNumber);
//	List<SerialNumber> findEntityById(SerialNumber serialNumber);

	void addEntityListInBatch(List<SerialNumber> list);

}

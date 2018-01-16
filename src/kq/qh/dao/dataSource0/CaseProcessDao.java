package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.CaseProcess;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月31日 下午2:13:58
 * @version V1.0
 */
/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月31日 下午2:13:58
 * @version V1.0
 */
public interface CaseProcessDao {

	int addEntity(CaseProcess caseProcess);
	int addEntityInBatch(List<CaseProcess> caseProcess);
	/**
	 * @Title  CaseProcessDao.java
	 * @param caseProcess
	 * @return
	 * @author ylf
	 * @date 2016年9月8日 下午2:40:28
	 * @version V1.0
	 */
	List<CaseProcess> findEntityList(Map<String , Object> map);
	/**
	 * @Title  CaseProcessDao.java
	 * @param caseProcess
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 上午10:25:44
	 * @version V1.0
	 */
	int count(CaseProcess caseProcess);
	/**
	 * @Title  CaseProcessDao.java
	 * @param map
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 上午10:44:40
	 * @version V1.0
	 */
	List<CaseProcess> findTheLatest(Map<String, Object> map);
	
	List<CaseProcess> findTheLatestData(Map<String, Object> map);
	/**
	 * @Title  CaseProcessDao.java
	 * @param caseProcess
	 * @return
	 * @author ylf
	 * @date 2016年9月9日 下午3:44:14
	 * @version V1.0
	 */
	int countTheLatestData(Map<String , Object> map);
	
	int countTheLatest(Map<String , Object> map);
	
	
	public List<CaseProcess> findAll(CaseProcess caseProcess);
	
	int deleteCaseProcessByTime(String time);
	
	List<CaseProcess> findSlCaseAndLastProcessTime(Map<String, Object> map);
	
}

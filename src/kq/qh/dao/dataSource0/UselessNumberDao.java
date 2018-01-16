package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.UselessNumbers;


/**
 * @Package kq.qh.dao.dataSource1
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月31日 下午3:28:38
 * @version V1.0
 */
public interface UselessNumberDao {
	UselessNumbers findEntityByCondition(UselessNumbers uselessNumber);
	int addEntity(UselessNumbers uselessNumber);
	void addEntityInBatch(List<UselessNumbers> list);
}

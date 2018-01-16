package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.Organization;


/**
 * @Title: OrganizationDao.java
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @author ylf
 * @date 2016年8月1日 上午10:16:50
 * @version V1.0
 */
public interface OrganizationDao {

	/**
	 * @return
	 */
	List<Organization> findOrganizationList();
	
	Organization findEntityById(Integer orgCode);
	
}

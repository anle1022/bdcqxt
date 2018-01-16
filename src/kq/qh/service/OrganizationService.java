package kq.qh.service;

import java.util.List;

import javax.annotation.Resource;

import kq.qh.dao.dataSource0.OrganizationDao;
import kq.qh.entity.Organization;

import org.springframework.stereotype.Service;


/**
 * @Title: 组织机构服务类
 * @Package kq.dh.service
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @author ylf
 * @date 2016年8月1日 上午10:24:14
 * @version V1.0
 */
@Service
public class OrganizationService {

	@Resource
	private OrganizationDao organizationDao;
	
	/**
	 * 查找所有的组织机构
	 * @return
	 */
	public List<Organization> findOrganizationList() {
		return organizationDao.findOrganizationList();
	}
	
	
}

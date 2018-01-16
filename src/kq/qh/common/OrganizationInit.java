package kq.qh.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.entity.Organization;
import kq.qh.service.OrganizationService;

import org.springframework.stereotype.Service;


/**
 * @Title: 组织机构缓存
 * @Package kq.dh.entity.init
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @author ylf
 * @date 2016年8月3日 下午4:22:24
 * @version V1.0
 */
@Service
public class OrganizationInit {
	
	@Resource
	private OrganizationService organizationService;
	
	private Map<String,String> map; 
	
	private List<Organization> list; 
	
	private Map<String,Organization> objMap;
	
	
	
	

	public Map<String, String> getMap() {
		if(null == this.map || this.map.isEmpty()){
			init();
		}
		return this.map;
	}
	
	public Map<String, Organization> getObjMap() {
		if(null == this.objMap || this.objMap.isEmpty() ){
			init();
		}
		return objMap;
	}
	
	public List<Organization> getList() {
		if(null == this.list || this.list.size() == 0){
			init();
		}
		return this.list;
	}

	public void init(){
		
		
		this.map = new HashMap<String, String>();
		this.objMap = new HashMap<String, Organization>();
		this.list = organizationService.findOrganizationList();
		Organization organization = new Organization();
		organization.setOrgCode("440100");
		organization.setOrgName("广州市");
		this.list.add(organization);
		for(Organization value:list){
			this.map.put(value.getOrgCode(), value.getOrgName());
			this.objMap.put(value.getOrgCode(), value);
		}
	}
	
	
}

package kq.qh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import kq.qh.common.OrganizationInit;
import kq.qh.entity.Organization;
import kq.qh.service.OrganizationService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 *
 * @author ylf
 * @date 2016年8月15日 下午3:02:09
 * @version V1.0
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	@Resource
	private OrganizationInit organizationInit;
	
	@Resource
	private OrganizationService organizationService;
	
	
	/**
	 * 查询  orgCode  与  对应的   orgName
	 * @param orgCode
	 * @return
	 */
	@RequestMapping("/findOrgTreeByOrgCode")
	@ResponseBody
	public List<Organization> findOrgTreeByOrgCode(Integer orgCode){
		List<Organization> list = null;
		if(orgCode == 440100){
			list = organizationInit.getList();
		}else{
			list = new ArrayList<Organization>();
			list.add( organizationInit.getObjMap().get(orgCode) );
		}
		return list;
	}
	
	
	
}

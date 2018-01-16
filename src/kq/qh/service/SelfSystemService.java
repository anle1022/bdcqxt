package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import kq.qh.common.DynamicDataSource;
import kq.qh.common.OrganizationInit;
import kq.qh.dao.dataSource0.SelfSystemDao;
import kq.qh.entity.SelfSystem;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Service;


/**
 * @Package kq.qh.service
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月25日 上午9:45:20
 * @version V1.0
 */
@Service
public class SelfSystemService {

	@Resource
	private SelfSystemDao selfSystemDao;
	
	@Resource
	private OrganizationInit organizationInit;
	/**
	 * @Title  SelfSystemService.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:46:52
	 * @version V1.0
	 */
	public String addEntity(SelfSystem selfSystem) {
		String  name = selfSystem.getName();
		Pattern p = Pattern.compile("^[\u4E00-\u9FA5|a-zA-z0-9]{1}([\u4E00-\u9FA5|a-zA-z0-9._]{2,49})([\u4E00-\u9FA5|a-zA-z0-9])$");
		Matcher m = p.matcher(name);
		boolean b = m.matches();
		if (name.length()>=4) {
			if (b) {
				int j = selfSystemDao.validateEntity(selfSystem);
				if (j>0) {
					return "已存在，请重新输入";
				} else {
					int i = selfSystemDao.addEntity(selfSystem);
					if(i>0){
						return "添加成功！";
					}else{
						return "添加失败，请联系技术人员！";
					}
				}
			} else {
				return "包含特殊字符，请重新输入";
			}
		} else {
			return "命名不符合规范，长度必须4位以上";
		}
	}
	/**
	 * @Title  SelfSystemService.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:49:26
	 * @version V1.0
	 */
	public int updateEntity(SelfSystem selfSystem) {
		return selfSystemDao.updateEntity(selfSystem);
	}
	/**
	 * @Title  SelfSystemService.java
	 * @param selfSystem
	 * @param page
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:53:05
	 * @version V1.0
	 */
	public List<SelfSystem> findEntityListInPage(SelfSystem selfSystem, PageUtil page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("password", selfSystem.getPassword());
		map.put("status", selfSystem.getStatus());
		map.put("orgCode",  selfSystem.getOrgCode() );
		map.put("name",  selfSystem.getName() );
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		List<SelfSystem> list = selfSystemDao.findEntityListInPage(map);
		for(SelfSystem system:list){
			system.setOrganization(organizationInit.getObjMap().get(system.getOrgCode()));
		}
		return list;
	}
	
	public List<SelfSystem> findAll() {
		return selfSystemDao.findAll();
	}
	
	/**
	 * @Title  SelfSystemService.java
	 * @param selfSystem
	 * @return
	 * @author ylf
	 * @date 2016年8月25日 上午9:53:10
	 * @version V1.0
	 */
	public int count(SelfSystem selfSystem) {
		return selfSystemDao.count(selfSystem);
	}
	
	public int validateEntity(SelfSystem selfSystem){
		return selfSystemDao.validateEntity(selfSystem);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteEntity(String id){
		return selfSystemDao.deleteEntity(id);
	};
	
	
}

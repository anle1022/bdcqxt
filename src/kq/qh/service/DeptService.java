package kq.qh.service;

import java.util.List;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.OrganizationInit;
import kq.qh.dao.dataSource0.DeptDao;
import kq.qh.entity.Dept;

import org.springframework.stereotype.Service;

@Service
public class DeptService {
	@Resource
	private DeptDao deptDao;
	@Resource
	private DeptInit deptInit;
	
	@Resource
	private OrganizationInit organizationInit;
	/**
	 * 登记部门代号配置页面数据查询
	 * @param dept
	 * @return
	 */
	public List<Dept> findEntityList(Dept dept) {
		List<Dept> list = deptDao.findEntityList(dept);
//		for (Dept deptList : list) {
//			deptList.setOrganization(organizationInit.getObjMap().get(deptList.getOrgCode()));
//		}
		return list;
//		return deptDao.findEntityList(dept);
	}
	
//	public int count(Dept dept) {
//		return deptDao.count(dept);
//	}
	
	/**
	 * 登记部门代号配置页面数据添加功能
	 * @param dept
	 * @return
	 */
	public String addEntity(Dept dept) {
		int j = deptDao.validateEntity(dept);
		if (j==0) {
			int i = deptDao.addEntity(dept);
			if(i>0){
				deptInit.destory();
				return "添加成功！";
			}else{
				return "添加失败，请联系技术人员！";
			}
		} else {
			return "登记部门或相关规范代码已存在！";
		}
	}
	
	/**
	 * 登记部门代号配置页面数据修改功能
	 * @param dept
	 * @return
	 */
	public String updateEntity(Dept dept) {
		int j = deptDao.validateEntity(dept);
		if (j==0) {
			int i = deptDao.updateEntity(dept);
			if(i>0){
				deptInit.destory();
				return "修改成功！";
			}else{
				return "修改失败，请联系技术人员！";
			}
		} else {
			return "相关规范代码已存在！";
		}
	}
	/**
	 * 登记部门代号配置页面数据删除功能
	 * @param dept
	 * @return
	 */
	public String deleteEntity(String id){
		int i = deptDao.deleteEntity(id);
		if (i>0) {
			deptInit.destory();
			return "ok";
		} else {
			return "error";
		}
	}	
	

	
}

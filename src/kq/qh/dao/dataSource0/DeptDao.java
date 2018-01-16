package kq.qh.dao.dataSource0;

import java.util.List;

import org.springframework.stereotype.Repository;

import kq.qh.entity.Dept;

@Repository
public interface DeptDao {

	List<Dept> findEntityList(Dept dept);
	
	int count(Dept dept);
	
	int addEntity(Dept dept);

	int updateEntity(Dept dept);
	
	public int deleteEntity(String id);
	/**
	 * 添加时判断登记部门是否已经存在
	 * @param dept
	 * @return
	 */
	public int validateEntity(Dept dept);
	
	
}

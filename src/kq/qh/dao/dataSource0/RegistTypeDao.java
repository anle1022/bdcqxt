package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.RegistType;

import org.springframework.stereotype.Repository;

@Repository
public interface RegistTypeDao {

	public List<RegistType> findTypeByPid();

	
}

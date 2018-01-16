package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.Operation;

import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao {

	public List<Operation> findEntityList();
	
}

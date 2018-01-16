package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.CaseProcess;

import org.springframework.stereotype.Repository;

@Repository
public interface CollectDao {

	List<CaseProcess> addSLxx(String time);

	List<CaseProcess> findHDxx(String time);

	List<CaseProcess> findSZxx(String time);
	
	List<CaseProcess> findFZxx(String time);

	List<CaseProcess> findTAxx(String time);

	List<CaseProcess> findSLxx(String time);


	//
}

package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kq.qh.entity.CaseProcess;
import kq.qh.entity.ExtendedCase;

@Repository
public interface ExtendedCaseDao {

	List<ExtendedCase> findExtendedCaseList(Map<String, Object> map);

	void addEntityListInBatch(List<ExtendedCase> extendedList);

	int deleteEntityByTime(String time);
	
	List<CaseProcess> findExtend(Map<String, Object> map);
	
	Integer countFindExtend (Map<String, Object> map);

}

package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kq.qh.entity.DaySummary;

@Repository
public interface DaySummaryDao {

	List<DaySummary> countReports(Map<String,Object> map);

	List<DaySummary> countCaseDetial(Map<String,Object> map);
	
	List<DaySummary> findEntityListInPage(Map<String,Object> map);
	
	int count(Map<String, Object> map);

	int deleteEntityByTime(String time);

	void addEntityListByTime(String time);
	
}

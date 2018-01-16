package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.DaySummary;
import kq.qh.entity.ExtendedCase;

import org.springframework.stereotype.Repository;

@Repository
public interface CountDao {

	List<DaySummary> findAll(Map<String, Object> map);

	List<ExtendedCase> findExtendedCase(Map<String, Object> map);

}

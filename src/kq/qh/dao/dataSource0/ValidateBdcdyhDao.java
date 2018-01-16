package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import kq.qh.entity.ValidateBdcdyh;

import org.springframework.stereotype.Repository;

@Repository
public interface ValidateBdcdyhDao {

	List<ValidateBdcdyh> findEntityListInPage(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int validateBDCDY(String bdcdyh);

	List<ValidateBdcdyh> countNum(Map<String, Object> map);

	void addEntityListInBatch(List<ValidateBdcdyh> bdcdyyzList);
}

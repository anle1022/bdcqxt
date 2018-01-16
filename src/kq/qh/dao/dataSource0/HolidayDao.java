package kq.qh.dao.dataSource0;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface HolidayDao {

	int findRestTimeCount(Map<String, String> map);

	List<String> findRestTime(Map<String, String> map);
}

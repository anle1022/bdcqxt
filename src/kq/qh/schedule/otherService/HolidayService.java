package kq.qh.schedule.otherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kq.qh.dao.dataSource0.HolidayDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
	
	@Autowired
	private HolidayDao holidayDao;
	
	public int findRestTimeCount(String startTime,String endTime){
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return holidayDao.findRestTimeCount(map);
	}

	public List<String> findRestTime(String startTime, String endTime) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return holidayDao.findRestTime(map);
	}
	
}

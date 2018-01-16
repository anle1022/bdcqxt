package kq.qh.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kq.qh.schedule.service.CollectDataService;
import kq.qh.schedule.service.SetSerialNumberPerYearService;
import kq.qh.util.DateUtil;

@Service
public class Jobs {
	
	static{
		System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");
	}
	
	@Autowired
	private CollectDataService collectDataService;
	@Autowired
	private SetSerialNumberPerYearService setSerialNumberPerYearService;
	
	public void setSerialNumber(){
		setSerialNumberPerYearService.setSerialNumber();
	}
	
	public void collectData(){
		String time = DateUtil.DateToString(new Date());
		collectDataService.collectFlowData(time);
		collectDataService.countFlowData(time);
		collectDataService.countExtendedCase(time);
	}
	
}

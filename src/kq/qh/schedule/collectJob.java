package kq.qh.schedule;

import java.util.Date;

import kq.qh.common.NumberTypeInit;
import kq.qh.common.WebContextSpringBeanFactory;
import kq.qh.dao.dataSource0.AlotNumberDao;
import kq.qh.schedule.service.CollectDataService;
import kq.qh.util.DateUtil;
import kq.qh.webService.service.WService;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

public class collectJob extends QuartzJobBean {
	
	private CollectDataService collectDataService;
	
	public collectJob(){
		init();
	}
	
	private void init(){
		if(null == collectDataService){
			collectDataService = (CollectDataService)WebContextSpringBeanFactory.getBean("collectDataService");
		}
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("================== Jobs start ======================"+arg0);
		System.out.println(collectDataService);
		System.out.println(DateUtil.DateToString(new Date()));
		String time = DateUtil.DateToString(new Date());
		collectDataService.collectFlowData(time);
		collectDataService.countFlowData(time);
		collectDataService.countExtendedCase(time);
	}
}

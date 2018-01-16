package kq.qh.schedule.jobs;

import kq.qh.schedule.service.CollectDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collectData")
public class CollectDataController {
	
	@Autowired
	private CollectDataService collectData;
	
	@RequestMapping("/collect")
	@ResponseBody
	public boolean collect(String startTime,String endTime){
		collectData.collectFlowData(startTime, endTime);
		return true;
	}
	
	@RequestMapping("/collectData")
	public String collectData(){
		return "collectData/collectData";
	}
	
	@RequestMapping("/countFlowData")
	@ResponseBody
	public boolean countFlowData(String startTime,String endTime){
		collectData.countFlowData(startTime, endTime);
		return true;
	}
	
	@RequestMapping("/countExtendedCase")
	@ResponseBody
	public boolean countExtendedCase(String startTime,String endTime){
		collectData.countExtendedCase(startTime, endTime);
		return true;
	}
}

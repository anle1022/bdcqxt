package kq.qh.schedule.jobs;

import javax.annotation.Resource;

import kq.qh.entity.SerialNumber;
import kq.qh.schedule.service.SetSerialNumberPerYearService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/serialNumYear")
public class SerialNumYearController {
	
	@Resource
	private SetSerialNumberPerYearService setSerialNumberPerYearService;
	/**
	 * 流水号配置复制上年度信息
	 * @param serialNumber
	 */
	@RequestMapping("/year")
	@ResponseBody
	public void findEntityList(SerialNumber serialNumber) {
		setSerialNumberPerYearService.setSerialNumber(serialNumber);
	}
//	public static void main(String[] args) {
//		SerialNumber serialNumber = new SerialNumber();
//		serialNumber.setYear(Integer.parseInt(DateUtil.getYear()));
//		SerialNumYearController ser = new SerialNumYearController();
//		ser.findEntityList(serialNumber);
//	}
}

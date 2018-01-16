package kq.qh.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kq.qh.dao.dataSource0.SerialNumberDao;
import kq.qh.entity.SerialNumber;
import kq.qh.util.DateUtil;

@Service
public class SetSerialNumberPerYearService {

	@Autowired
	private SerialNumberDao serialNumberDao;
	
	@Transactional
	public void setSerialNumber() {
		SerialNumber serialNumber = new SerialNumber();
		String year = DateUtil.getYear();
		Integer years = Integer.parseInt(year)+1;
		serialNumber.setYear(years);
		List<SerialNumber> listYears = serialNumberDao.findEntityList(serialNumber);
		if(listYears.size()>0){
			return;
		}
		serialNumber.setYear(Integer.parseInt(year));
		List<SerialNumber> list = serialNumberDao.findEntityList(serialNumber);
		for (SerialNumber serialNum: list) {
			String id = serialNum.getId();
			String ids = id.substring(0, id.length()-4)+years;
			serialNum.setId(ids);
			serialNum.setYear(years);
			serialNum.setCurNumber(serialNum.getMinNumber());
			
		}
		serialNumberDao.addEntityListInBatch(list);
	}
	
	@Transactional
	public void setSerialNumber(SerialNumber serialNumber) {
		String year = DateUtil.getYear();
		Integer years = serialNumber.getYear();
		serialNumber.setYear(years);
		List<SerialNumber> listYears = serialNumberDao.findEntityList(serialNumber);
		if(listYears.size()>0){
			return;
		}
		if (years.equals(Integer.parseInt(year))) {
			serialNumber.setYear(Integer.parseInt(year)-1);
		}else {
			serialNumber.setYear(Integer.parseInt(year));
		}
		List<SerialNumber> list = serialNumberDao.findEntityList(serialNumber);
		for (SerialNumber serialNum: list) {
			String id = serialNum.getId();
			String ids = id.substring(0, id.length()-4)+years;
			serialNum.setId(ids);
			serialNum.setYear(years);
			serialNum.setCurNumber(serialNum.getMinNumber());
			
		}
		serialNumberDao.addEntityListInBatch(list);
	}
}

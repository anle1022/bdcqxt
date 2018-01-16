package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.dao.dataSource0.SerialNumberDao;
import kq.qh.entity.NumberType;
import kq.qh.entity.SelfSystem;
import kq.qh.entity.SerialNumber;
import kq.qh.entity.Type;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Service;


/**
 * @Package kq.qh.service
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月19日 下午5:54:24
 * @version V1.0
 */
@Service
public class SerialNumberService {
	
	@Resource
	private SerialNumberDao serialNumberDao;
	@Resource
	private TypeService typeService;
	@Resource
	private NumberTypeService numberTypeService;
	/**
	 * @Title  SerialNumberService.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月19日 下午5:57:35
	 * @version V1.0
	 */
	public String addEntity(SerialNumber serialNumber) {
		String typeId = serialNumber.getNumberTypeId();
		NumberType numberType = new NumberType();
		numberType.setId(typeId);
		NumberType num = numberTypeService.findAllOne(numberType);
		String typ = num.getType();
		Type type = new Type();
		type.setId(typ);
		type = typeService.findAll(type);
		if (type.getIsdistinctebySystem().equals(0)) {
			serialNumber.setSelfSystemId("");
		} 
		List<SerialNumber> list = serialNumberDao.validateEntityFind(serialNumber);
		for (SerialNumber serialNumber2 : list) {
			if (serialNumber2.getIsRunOut().equals(0)) {
				return "还有剩余号，不可添加";
			}
		}
			int i = serialNumberDao.addEntity(serialNumber);
			if(i>0){
				return "添加成功！";
			}else{
				return "添加失败！请联系技术人员。";
			}
	}

	/**
	 * @Title  SerialNumberService.java
	 * @param serialNumber
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 上午9:01:35
	 * @version V1.0
	 */
	public String updateEntity(SerialNumber serialNumber) {
		int i = serialNumberDao.updateEntity(serialNumber);
		if(i>0){
			return "ok";
		}else{
			return "error";
		}
	}

	/**
	 * @Title  SerialNumberService.java
	 * @param page
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 下午12:08:17
	 * @version V1.0
	 */
	public List<SerialNumber> findEntityListInPage(SerialNumber serialNumber,NumberType numberType,SelfSystem selfSystem,PageUtil page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("itmes", selfSystem);
		map.put("itme", numberType);
		map.put("year", serialNumber.getYear());
		map.put("numberTypeId", serialNumber.getNumberTypeId());
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		List<SerialNumber> l = serialNumberDao.findEntityListInPage(map);
		return l;
	}
	
	public List<SerialNumber> findEntityList(SerialNumber serialNumber) {
		return serialNumberDao.findEntityList(serialNumber);
	}
	
	/**
	 * 流水号配置页面数据统计查询
	 * @param serialNumber
	 * @param numberType
	 * @param page
	 * @return
	 */
	public int count(SerialNumber serialNumber,NumberType numberType,SelfSystem selfSystem){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("itmes", selfSystem);
		map.put("itme", numberType);
		map.put("year", serialNumber.getYear());
		map.put("numberTypeId", serialNumber.getNumberTypeId());
		return serialNumberDao.count(map);
	}

	/**
	 * @Title  SerialNumberService.java
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月22日 下午5:23:27
	 * @version V1.0
	 */
	public String  deleteEntity(String pkid) {
		int i = serialNumberDao.deleteEntity(pkid);
		if(i>0){
			return "ok";
		}else{
			return "error";
		}
	}
	
	public int validateEntity(SerialNumber serialNumber){
		return serialNumberDao.validateEntity(serialNumber);
	}
}

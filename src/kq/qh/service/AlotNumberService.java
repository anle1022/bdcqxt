package kq.qh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DeptInit;
import kq.qh.common.NumberTypeInit;
import kq.qh.common.TypeInit;
import kq.qh.dao.dataSource0.AlotNumberDao;
import kq.qh.dao.dataSource0.NumberTypeDao;
import kq.qh.dao.dataSource0.TypeDao;
import kq.qh.entity.AlotNumber;
import kq.qh.entity.Dept;
import kq.qh.entity.NumberType;
import kq.qh.entity.SelfSystem;
import kq.qh.entity.SetNumber;
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
 * @date 2016年8月25日 下午4:55:46
 * @version V1.0
 */
/**
 * @Package kq.qh.service
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 
 * @author ylf
 * @date 2016年8月25日 下午4:55:46
 * @version V1.0
 */
@Service
public class AlotNumberService {
	
	@Resource
	private AlotNumberDao alotNumberDao;
	@Resource
	private NumberTypeDao numberTypeDao;
	@Resource
	private DeptInit deptInit;
	@Resource
	private TypeInit typeInit;
	@Resource
	private NumberTypeInit numberTypeInit;
	@Resource
	private TypeDao typeDao;
	/**
	 * 取号监控页面的数据查询
	 * @param type
	 * @param numberType
	 * @param selfSystem
	 * @param startTime		开始时间  
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @param page			分页
	 * @return
	 */
	public  List<AlotNumber> findEntityInPage(Type type,NumberType numberType,SelfSystem selfSystem,String startTime,String endTime,AlotNumber alotNumber,PageUtil page){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", alotNumber);
		map.put("items", selfSystem);
		map.put("items1", numberType);
		map.put("items2", type);
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		return alotNumberDao.findEntityInPage(map);
	}
	/**
	 * 取号监控页面的数据查询统计
	 * @param numberType
	 * @param selfSystem
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @return
	 */
	public int count(NumberType numberType,SelfSystem selfSystem,String startTime,String endTime,AlotNumber alotNumber){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", alotNumber);
		map.put("items", selfSystem);
		map.put("items1", numberType);
		return alotNumberDao.count(map);
	};
	
	
	public String deleteEntity(String id) {
		int i = alotNumberDao.deleteEntity(id);
		if(i>0){
			return "ok";
		}else{
			return "error";
		}
	}
	
	/**
	 * 取号统计页面的数据查询
	 * @param numberType
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @param page
	 * @return
	 */
	public  List<SetNumber> findEntityInPages(NumberType numberType,String startTime,String endTime,AlotNumber alotNumber,PageUtil page){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", alotNumber);
		map.put("items1", numberType);
		map.put("startRow", (page.getPage()-1)*page.getRows());
		map.put("endRow", page.getPage() * page.getRows() );
		
		List<AlotNumber> alotNumberList = alotNumberDao.findEntityInPages(map);
		List<NumberType> lists = numberTypeDao.findAll(numberType);
		List<Type>  list = typeDao.findAll();
		List<SetNumber> setNumbers = new ArrayList<SetNumber>();
		
		Map<String, String> m2 = new HashMap<String, String>();		//根据  BDCZSH   BDCZMH   存储  orgName
		Map<String, String> m3 = new HashMap<String, String>();		//根据  DJZH  存储  orgName
		for (Dept dept:deptInit.getList()) {
			m2.put(dept.getZsNo(),dept.getOrgName());
			m3.put(dept.getDjzgNo(),dept.getOrgName());
		}
		for(Type type:typeInit.getList()){
			m3.put(type.getName(), type.getId());
		}
		for (AlotNumber alot : alotNumberList) {
			SetNumber setNumber =  new SetNumber() ;
			String numberTypeId =alot.getNumberTypeId();
			String lengthString = numberTypeId.substring(numberTypeId.length()-2, numberTypeId.length());
			for (Type type : list) {
				if (numberTypeId.contains(type.getId())) {
					setNumber.setType(type.getName());
					if (type.getId().equals(m3.get(alot.getName())) && type.getId().length() == 4) {
						if (numberTypeId.contains(lengthString)) {
							setNumber.setDjType(m3.get(lengthString));
						}
					}else {
						setNumber.setDjType(m2.get(lengthString));
					}
				}
			}
			for (NumberType numberType2 : lists) {
				if (numberTypeId.equals(numberType2.getId())) {
					setNumber.setText(numberType2.getText());
				}
			}
			setNumber.setCurNumber(alot.getNum());
			setNumber.setMaxNumber(alot.getNumbers());
			setNumbers.add(setNumber);
		}	
		return setNumbers;
	}
	/**
	 * 取号统计页面的数据查询统计
	 * @param numberType
	 * @param startTime		开始时间	
	 * @param endTime		结束时间
	 * @param alotNumber
	 * @return
	 */
	public int countfind(NumberType numberType,String startTime,String endTime,AlotNumber alotNumber){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("item", alotNumber);
		map.put("items1", numberType);
		return alotNumberDao.countfind(map);
	};
}

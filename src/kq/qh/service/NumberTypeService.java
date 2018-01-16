package kq.qh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kq.qh.common.DynamicDataSource;
import kq.qh.common.NumberTypeInit;
import kq.qh.dao.dataSource0.NumberTypeDao;
import kq.qh.entity.NumberType;
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
 * @date 2016年8月23日 上午9:36:03
 * @version V1.0
 */
@Service
public class NumberTypeService {
	
	@Resource
	private NumberTypeDao numberTypeDao;
	@Resource
	private SerialNumberService serialNumberService;
	@Resource
	private NumberTypeInit numberTypeInit;
	/**
	 * @Title  NumberTypeService.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午9:37:22
	 * @version V1.0
	 */
	public String addEntity(NumberType numberType) {
		//验证数据是否存在
		int count = validateEntity(numberType);
		if(count == 1){
			return "关键字或类型名称已存在！";
		}
		int i =  numberTypeDao.addEntity(numberType);
		if(i>0){
			numberTypeInit.destory();
			return "添加成功！";
		}else{
			return "添加失败！请联系技术人员。";
		}
	}
	/**
	 * @Title 修改取号类型时需满足以下条件
	 * 		  1：该取号类型目前未运用（即：在流水号中为配置）
	 * 
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:20:24
	 * @version V1.0
	 */
	public String updateEntity(NumberType numberType) {
		//查询serialnumber表中是否存在numberType数据
//		SerialNumber serialNumber = new SerialNumber();
//		serialNumber.setNumberTypeId(numberType.getId());
//		int count = serialNumberService.findEntityList(serialNumber).size();
//		if(count == 0){
			int i = numberTypeDao.updateEntity(numberType);
			if(i>0){
				numberTypeInit.destory();
				return "修改成功！";
			}else{
				return "修改失败，请联系技术人员！";
			}
//		}else{
//			return "该取号类型已经开始应用，如需修改，可重新配置新的取号类型。";
//		}
	}
	/**
	 * @Title  NumberTypeService.java
	 * @param id
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:21:39
	 * @version V1.0
	 */
	public String deleteEntity(String id) {
		int i = numberTypeDao.deleteEntity(id);
		if(i>0){
			numberTypeInit.destory();
			return "ok";
		}else{
			return "error";
		}
	}
	/**
	 * @Title  NumberTypeService.java
	 * @param numberType
	 * @param page
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:30:20
	 * @version V1.0
	 */
	public List<NumberType> findEntityListInPage(Type type,NumberType numberType, PageUtil page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("item", type);
		map.put("items", numberType);
		map.put("startRow", page!=null?(page.getPage()-1)*page.getRows():null);
		map.put("endRow", page!=null?page.getPage() * page.getRows():null );
		return numberTypeDao.findEntityListInPage(map);
	}
	/**
	 * @Title  NumberTypeService.java
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 上午11:30:24
	 * @version V1.0
	 */
	public int count(Type type,NumberType numberType) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("item", type);
		map.put("items", numberType);
		return numberTypeDao.count(map);
	}
	/**
	 * @Title  NumberTypeService.java
	 * @return
	 * @author ylf
	 * @date 2016年8月23日 下午2:00:30
	 * @version V1.0
	 */
	public List<NumberType> findAll(NumberType numberType) {
		return numberTypeDao.findAll(numberType);
	}
	
	public NumberType findAllOne(NumberType numberType) {
		return numberTypeDao.findAllOne(numberType);
	}
	/**
	 * 
	 * @Title 验证取号类型是否存在；
	 * 		  <p>1：id不能重复</p>
	 *        <p>2：text不能重复</p>
	 * @param numberType
	 * @return
	 * @author ylf
	 * @date 2016年8月24日 上午9:33:55
	 * @version V1.0
	 */
	public int validateEntity(NumberType numberType){
		return numberTypeDao.validateEntity(numberType);
	}
}

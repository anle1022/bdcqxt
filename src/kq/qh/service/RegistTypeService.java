package kq.qh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;







import kq.qh.dao.dataSource0.RegistTypeDao;
import kq.qh.entity.RegistType;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;



	
@Service
public class RegistTypeService {
		
	@Resource
	private RegistTypeDao registTypeDao;	
		
	/**
	 * 所有登记类型的数据查询
	 * @return
	 */
	public List<RegistType> findTypeByPid(){
		return registTypeDao.findTypeByPid();
	}

	/**
	 * 
	 * @param pid
	 * @param registTypeList
	 * @return
	 */
	public List<RegistType> findMenuByPid(String pid,
			List<RegistType> registTypeList) {
		List<RegistType> registTypes = new ArrayList<RegistType>();
		for(RegistType registType:registTypeList){
			if(StringUtils.isNullOrEmpty(registType.getPid())){
				registTypes.add(registType);
			}
		}
		for(RegistType registType:registTypes){
			List<RegistType> registTypeList1 = new ArrayList<RegistType>();
			
			for(RegistType registType1:registTypeList){
				if(!StringUtils.isNullOrEmpty(registType1.getPid())&&registType1.getPid().equals(registType.getId())){
					registTypeList1.add(registType1);
				
				}
				registType.setChildren(registTypeList1);
			}
		}

		return registTypes;
	};	
		
	
}

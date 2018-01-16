package kq.qh.service;

import java.util.List;

import javax.annotation.Resource;

import kq.qh.dao.dataSource0.TypeDao;
import kq.qh.entity.Type;

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
public class TypeService {
	
	@Resource
	private TypeDao typeDao;
	

	public List<Type> findAll() {
		return typeDao.findAll();
	}
	
	public Type findAll(Type type){
		return typeDao.findAll(type);
	}
}

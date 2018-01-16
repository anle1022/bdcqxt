package kq.qh.service;

import java.util.List;

import javax.annotation.Resource;

import kq.qh.dao.dataSource0.DictDao;
import kq.qh.entity.Dict;

import org.springframework.stereotype.Service;

@Service
public class DictService {

	@Resource
	private DictDao dictDao;
	/**
	 * 字轨规范查询
	 * @param key
	 * @return
	 */
	public List<Dict> findEntityList(String key) {
		return dictDao.findEntityList(key);
	}

}

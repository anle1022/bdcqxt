package kq.qh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kq.qh.dao.dataSource0.ViewDao;
import kq.qh.schedule.entity.CQxx;
import kq.qh.schedule.entity.QLRxx;
import kq.qh.schedule.entity.SPBxx;


@Service
public class ViewService {
	@Resource
	private ViewDao viewDao;
	/**
	 * 产权信息查询
	 * @param cqxx
	 * @return
	 */
	public List<CQxx> findCq(CQxx cqxx){
		return viewDao.findCq(cqxx);
	}
	/**
	 * 权利人信息查询
	 * @param cqxx
	 * @return
	 */
	public List<QLRxx> findQlr(QLRxx qlrxx){
		return viewDao.findQlr(qlrxx);
	}
	/**
	 * 审批信息查询
	 * @param cqxx
	 * @return
	 */
	public List<SPBxx> findSpb(SPBxx spbxx){
		return viewDao.findSpb(spbxx);
	}
}

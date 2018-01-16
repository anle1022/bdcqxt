package kq.qh.dao.dataSource0;

import java.util.List;

import org.springframework.stereotype.Repository;

import kq.qh.schedule.entity.CQxx;
import kq.qh.schedule.entity.QLRxx;
import kq.qh.schedule.entity.SPBxx;



 
@Repository
public interface ViewDao {
	/**
	 * 产权信息展示查询
	 * @return
	 */
	public List<CQxx> findCq(CQxx cqxx);
	/**
	 * 权利人信息展示查询
	 * @return
	 */
	public List<QLRxx> findQlr(QLRxx qlrxx);
	/**
	 * 审批信息展示查询
	 * @return
	 */
	public List<SPBxx> findSpb(SPBxx spbxx);
	
}

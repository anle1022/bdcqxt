package kq.qh.dao.dataSource0;

import java.util.List;

import kq.qh.entity.Menu;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao {

	List<Menu> findMenuListForUser(String loginname);

	
}

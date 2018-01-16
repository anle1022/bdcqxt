package kq.qh.dao.dataSource0;

import kq.qh.entity.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

	public User login(User user);
	
	public int updateEntity(User user);
}

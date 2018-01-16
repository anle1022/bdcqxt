package kq.qh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kq.qh.dao.dataSource0.UserDao;
import kq.qh.entity.User;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;

	public User login(User user) {
		return userDao.login(user);
	}
	
	/**
	 * 修改用户信息
	 * @param loginname
	 * @param password
	 * @param passwordNew
	 * @return
	 */
	public String  updateEntity(User user){
		int fale = userDao.updateEntity(user);
		if (fale >0) {
			return "�޸ĳɹ���";
		} else {
			return "�޸�ʧ�ܣ�";
		}
	}
}

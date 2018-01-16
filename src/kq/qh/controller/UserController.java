package kq.qh.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kq.qh.util.MD5Helper;
import kq.qh.entity.Menu;
import kq.qh.entity.User;
import kq.qh.service.MenuService;
import kq.qh.service.UserService;

/**
 * 
 * @Package kq.qh.controller
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 从发证系统获取用户信息
 * @author ylf
 * @date 2016年8月18日 上午11:01:09
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Autowired
	private MenuService menuService;
	/**
	 * 登陆
	 * @param resourceKey
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String login(@RequestParam(defaultValue="BDCDJTYQHXT") String resourceKey,User user,HttpServletRequest request) {
		String msg = "ok";
		User u = null;
		if (!StringUtils.isEmpty(user.getLoginname()) && !StringUtils.isEmpty(user.getPassword())) {
			String password = user.getPassword();
			user.setPassword(new MD5Helper().toMD5(password));
			try {
				u = userService.login(user);
				if (null == u) {
					msg = "用户名或密码错误！";
				}else{
					request.getSession().setAttribute("user", u);
					List<Menu> allMenuList = u.getMenuList();
					List<Menu> parent_menuList = menuService.findMenuByResourceKey(resourceKey,allMenuList);
					parent_menuList = menuService.findChildrenMenu(parent_menuList,allMenuList);
					request.getSession().setAttribute("menuList", parent_menuList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "数据库配置错误！";
			}

		}else{
			msg="用户名，密码不能为空。";
		}
		return msg;
	}
	/**
	 * 退出功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "redirect:/user/logout.do";
	}
	
	/**
	 * 修改用户信息
	 * @param loginname
	 * @param password
	 * @param passwordNew
	 * @return
	 */
	@RequestMapping(value="/updateEntity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String  updateEntity(String loginname,String password,String passwordNew){
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(new MD5Helper().toMD5(password));
		User u = userService.login(user);
		if(null == u){
			return "你输入的旧密码和用户名不匹配！";
		}
		user.setPassword(new MD5Helper().toMD5(passwordNew));
		return userService.updateEntity(user);
		
	}
	
}

package kq.qh.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kq.qh.entity.Menu;
import kq.qh.entity.User;
import kq.qh.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @Package kq.qh.controller
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 从发证系统获取菜单信息
 * @author ylf
 * @date 2016年8月18日 上午11:00:07
 * @version V1.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 左侧菜单栏配置加载
	 * @param pid
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/findMenuByPid")
	@ResponseBody
	public List<Menu> findMenuByPid(String pid,HttpServletRequest request){
		User user =  (User)request.getSession().getAttribute("user");
		List<Menu> menuList = user.getMenuList();
		if(null == pid){
			menuList = menuService.findMenuByResourceKey("BDCDJTYQHXT",menuList);
		}else{
			menuList = menuService.findMenuByPid(pid,menuList);
		}
		return menuList;
	}
	/**
	 * 左侧菜单栏配置加载
	 * @param pid
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMenuList")
	@ResponseBody
	public List<Menu> findMenuList(@RequestParam(defaultValue="BDCDJTYQHXT") String resourceKey,HttpServletRequest request){
		User user =  (User)request.getSession().getAttribute("user");
		List<Menu> allMenuList = user.getMenuList();
		List<Menu> parent_menuList = menuService.findMenuByResourceKey(resourceKey,allMenuList);
		parent_menuList = menuService.findChildrenMenu(parent_menuList,allMenuList);
		return parent_menuList;
	}


}

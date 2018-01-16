package kq.qh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import kq.qh.entity.Menu;
import kq.qh.entity.User;
import kq.qh.service.MenuService;
import kq.qh.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.StringUtils;


/**
 * @Package kq.qh.controller
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 *
 * @author ylf
 * @date 2016年8月12日 下午4:32:54
 * @version V1.0
 */
@Controller
public class MainController {
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/main")
	public String main(@RequestParam(defaultValue="BDCDJTYQHXT") String resourceKey,ModelMap map,HttpSession session){
		User user =  (User)session.getAttribute("user");
		List<Menu> allMenuList = user.getMenuList();
		List<Menu> parent_menuList = menuService.findMenuByResourceKey(resourceKey,allMenuList);
		parent_menuList = menuService.findChildrenMenu(parent_menuList,allMenuList);
		map.put("menuList", parent_menuList);
		return "main";
	}
	/**
	 * 跳转到主页
	 * @param startTime
	 * @param endTime
	 * @param map
	 * @return
	 */
	@RequestMapping("/dashboard")
	public String dashboard(String startTime,String endTime,ModelMap map){
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = DateUtil.getNowMonthBegin();
		}
		if(StringUtils.isNullOrEmpty(endTime)){
			endTime = DateUtil.getMonthEnd(startTime);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return "dashboard";
	}
	@RequestMapping("/dashboard1")
	public String dashboard1(String startTime,String endTime,ModelMap map){
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = DateUtil.getNowMonthBegin();
		}
		if(StringUtils.isNullOrEmpty(endTime)){
			endTime = DateUtil.getMonthEnd(startTime);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return "dashboard1";
	}
	
	@RequestMapping("/charts")
	public String charts(String startTime,String endTime,ModelMap map){
	 
		return "charts";
	}
	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
}

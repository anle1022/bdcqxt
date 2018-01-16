package kq.qh.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import kq.qh.entity.Menu;

import org.springframework.stereotype.Service;


/**
 * @Package kq.qh.service
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 *
 * @author ylf
 * @date 2016年8月12日 下午4:19:58
 * @version V1.0
 */
@Service
public class MenuService {
	
	
	/**
	 * @Title: MenuService.java
	 * @param menuList
	 * @return
	 * @author ylf
	 * @date 2016年8月12日 下午4:21:53
	 * @version V1.0
	 */
	public List<Menu> findMenuByResourceKey(String resourceKey,List<Menu> menuList) {
		List<Menu> menus = new ArrayList<Menu>();
		String pid = "2";
		for(Menu menu:menuList){
			if(menu.getResourceKey().equals(resourceKey)){
				pid = menu.getId();
				break;
			}
		}
		for(Menu menu:menuList){
			if(menu.getPid().equals(pid)){
				menus.add(menu);
			}
		}
		Collections.sort(menus,new Comparator<Menu>(){

			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getSortOrder().compareTo(o2.getSortOrder());
			}
			
		});
		return menus;
	}
	/**
	 * @Title: MenuService.java
	 * @param pid
	 * @param menuList
	 * @return
	 * @author ylf
	 * @date 2016年8月12日 下午4:25:46
	 * @version V1.0
	 */
	public List<Menu> findMenuByPid(String pid, List<Menu> menuList) {
		Collections.sort(menuList,new Comparator<Menu>(){
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getSortOrder().compareTo(o2.getSortOrder());
			}
		});
		List<Menu> menus = new ArrayList<Menu>();
		for(Menu menu:menuList){
			if(menu.getPid().equals(pid)){
				if(menu.getLeaf()==0){
					List<Menu> menuLists = new ArrayList<Menu>();
					menuLists.addAll(menuList);
					menuLists.remove(menu);
					menu.setChildren(findMenuByPid(menu.getId(),menuLists));
				}
				menus.add(menu);
			}
		}
		return menus;
	}
	/**
	 * @Title  MenuService.java
	 * @param parent_menuList
	 * @param allMenuList
	 * @return
	 * @author ylf
	 * @date 2016年9月1日 下午6:03:13
	 * @version V1.0
	 */
	public List<Menu> findChildrenMenu(List<Menu> parent_menuList,
			List<Menu> allMenuList) {
		for(Menu menu : parent_menuList){
			menu.setChildren(findMenuByPid(menu.getId(),allMenuList));
		}
		return parent_menuList;
	}

}

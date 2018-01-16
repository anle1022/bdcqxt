package kq.qh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kq.qh.entity.RegistType;
import kq.qh.service.MenuService;
import kq.qh.service.RegistTypeService;

@Controller
@RequestMapping("/registType")
public class RegistTypeController {

	@Resource
	private RegistTypeService registTypeService;
	@Resource
	private MenuService menuService; 
	
	
	
	/**
	 * 所有登记类型的数据查询
	 * @param pid
	 * @return
	 */
	@RequestMapping("/findTypeByPid")
	@ResponseBody
	public List<RegistType> findTypeByPid(String pid) {
		List<RegistType> registTypeList = registTypeService.findTypeByPid();
		registTypeList = registTypeService.findMenuByPid(null,registTypeList);
		return registTypeList;
	}
}

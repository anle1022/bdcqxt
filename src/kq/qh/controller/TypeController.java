package kq.qh.controller;


import java.util.List;

import javax.annotation.Resource;

import kq.qh.entity.Type;
import kq.qh.service.TypeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/type")
public class TypeController {
	
	@Resource
	private TypeService typeService;
	/**
	 * 查询   DJZH  BDCDJZMH  BDCQZSH   的ID  和   NAME
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Type> finaAll(){
		return typeService.findAll();
	}
}

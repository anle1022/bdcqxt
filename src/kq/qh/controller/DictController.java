package kq.qh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kq.qh.entity.Dict;
import kq.qh.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {

	@Resource
	private DictService dictService;
	
	/**
	 * 字轨规范查询
	 * @param key
	 * @return
	 */
	@RequestMapping("/findEntityList")
	@ResponseBody
	public List<Dict> findEntityList(String key){
		
		return dictService.findEntityList(key);
	}
}

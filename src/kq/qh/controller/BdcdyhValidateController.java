package kq.qh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import kq.qh.common.OrganizationInit;
import kq.qh.entity.Organization;
import kq.qh.entity.Validate1;
import kq.qh.entity.ValidateBdcdyh;
import kq.qh.service.BdcdyValidateService;
import kq.qh.util.ExcelUtil;
import kq.qh.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bdcdyh")
public class BdcdyhValidateController {
	
	@Resource
	private BdcdyValidateService bdcdyValidateService;
	@Resource
	private OrganizationInit organizationInit;
	/**
	 * 不动产单元验证监控
	 * @param map
	 * @return
	 */
	@RequestMapping("/monitor")
	public String monitor(ModelMap map){
		return "bdcdyh/monitor";
	}
	/**
	 * 不动产单元验证统计
	 * @param map
	 * @return
	 */
	@RequestMapping("/count")
	public String count(ModelMap map){
		return "bdcdyh/count";
	}
	/**
	 * 不动产单元验证监控数据查询
	 * @param startTime  	开始时间
	 * @param endTime		结束时间
	 * @param validateBdcdyh
	 * @param page
	 * @return
	 */
	@RequestMapping("/findEntityListInPage")
	@ResponseBody
	public Map<String,Object> findEntityListInPage(String startTime,String endTime,ValidateBdcdyh validateBdcdyh,PageUtil page){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", bdcdyValidateService.findEntityListInPage(startTime,endTime,validateBdcdyh,page));
		map.put("total",bdcdyValidateService.count(startTime,endTime,validateBdcdyh));
		return map;

	} 
	/**
	 * 不动产单元验证统计数据查询
	 * @param startTime
	 * @param endTime
	 * @param orgCode
	 * @param exist
	 * @return
	 */
	@RequestMapping("/countNum")
	@ResponseBody
	public List<ValidateBdcdyh> countNum(String startTime,String endTime,String orgCode,String exist){
		return bdcdyValidateService.countNum( startTime, endTime, orgCode, exist);
	}
	/**
	 * 不动产单元验证监控数据导出Excel文档
	 * @param validateBdcdyh
	 * @param startTime
	 * @param endTime
	 * @param response
	 */
	@RequestMapping("/exportExcels")
	public void exportExcels(ValidateBdcdyh validateBdcdyh,String startTime, String endTime,HttpServletResponse response){
		
		List<ValidateBdcdyh> list = bdcdyValidateService.findEntityListInPage(startTime,endTime,validateBdcdyh);
		List<ValidateBdcdyh> lists = new ArrayList<ValidateBdcdyh>();
		for (ValidateBdcdyh va : list) {
			ValidateBdcdyh val = new ValidateBdcdyh();
			val.setAjbh(va.getAjbh());
//			val.setDjzh(va.getDjzh());
			val.setBdcdyh(va.getBdcdyh());
			val.setExist(va.getExist().equals("0")?"不存在":"存在");
			val.setTime(va.getTime());
			val.setSystemName(va.getSystemName());
			lists.add(val);
		}
		String[] names = new String[]{"ajbh","bdcdyh","exist","time","systemName"};
		ExcelUtil excel = new ExcelUtil();
//		excel.writeExcels(lists,"不动产单元验证监控报表",response);
		excel.writeExcel(lists,"不动产单元验证监控报表",names,ValidateBdcdyh.class,response);
	}
	/**
	 * 不动产单元验证统计数据导出Excel文档
	 * @param orgCode	
	 * @param exist
	 * @param startTime
	 * @param endTime
	 * @param response
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(String orgCode,String exist,String startTime, String endTime,HttpServletResponse response){
		List<ValidateBdcdyh> list = bdcdyValidateService.countNum(startTime,endTime, orgCode, exist);
		List<Validate1> lists = new ArrayList<Validate1>();
		Map<String, String> map = new HashMap<String, String>();
		for(Organization org : organizationInit.getList()){
			map.put(org.getOrgCode(),org.getOrgName());
		}
		for (ValidateBdcdyh va : list) {
			Validate1 val = new Validate1();
			val.setOrgCode(map.get(va.getOrgCode()));
			val.setExist(va.getExist().equals("0")?"不存在":"存在");
			val.setSum(va.getSum());
			lists.add(val);
		}
		if(lists.size()==0){
			Validate1 val = new Validate1();
			val.setOrgCode("合计");
			val.setExist("");
			val.setSum(0);
			lists.add(val);
		}
//		org.apache.commons.beanutils.BeanUtils.copyProperties(Validate1, ValidateBdcdyh);
		String[] names = new String[]{"orgCode","exist","sum"};
		ExcelUtil excel = new ExcelUtil();
		excel.writeExcel(lists,"不动产单元验证统计报表",names,Validate1.class,response);
	}
}

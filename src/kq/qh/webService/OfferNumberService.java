package kq.qh.webService;

import java.util.ArrayList;
import java.util.List;

import kq.qh.common.WebContextSpringBeanFactory;
import kq.qh.util.JaxbUtil;
import kq.qh.util.JsonUtil;
import kq.qh.util.JaxbUtil.CollectionWrapper;
import kq.qh.webService.vo.CasesResult;
import kq.qh.webService.vo.DataParam;
import kq.qh.webService.vo.DataResult;
import kq.qh.webService.vo.Flag;

/**
 * @Package kq.qh.webService
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title <p>取号接口：</p>
 * 		  <p>其他系统通过该接口获取系统定义的取号类型的号码</p>
 *        <p>接收参数为json格式封装的对象</p>
 * 		  <p>返回一个json格式封装的对象</p>
 * @author ylf
 * @date 2016年8月25日 下午3:01:02
 * @version V1.0
 */
public class OfferNumberService{

	private OfferNumberServiceImpl offerNumberServiceImpl;
	
	public OfferNumberService(){
		init();
	}
	
	/**
	 * 注入需要的bean
	 */
	private void init(){
		if(null == offerNumberServiceImpl){
			offerNumberServiceImpl = (OfferNumberServiceImpl)WebContextSpringBeanFactory.getBean("offerNumberServiceImpl");
		}
	}
	
	/**
	 * 取号接口
	 * @param str 待解析数据
	 * @param type 数据格式
	 * 		<p>1、xml   2、json</p>
	 * @return
	 * @throws Exception 
	 */
	public String offerNumber(String str,String type) {
		String result = "";
		try{
			result = offerNumberServiceImpl.offerNumber(str, type);
		}catch(Exception e){
			DataResult dr = new DataResult();
			CasesResult caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("服务器内部错误。");
			List<CasesResult> caseResultList = new ArrayList<CasesResult>();
			caseResultList.add(caseResult);
			dr.setList(caseResultList);
			if(type.equals("xml")){
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class, CollectionWrapper.class);
				result = requestBinder.toXml(dr, "utf-8");
			}else{
				result = JsonUtil.toJson(dr);
			}
		}
		return result;
	}
 
	/**
	 * webService  
	 * 不动产单元验证接口：
	 */
	public String validateBDCDY (String str,String type){
		String result = "";
		try{
			offerNumberServiceImpl.validateBDCDY(str, type);
		}catch(Exception e){
			DataResult dr = new DataResult();
			CasesResult caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("服务器内部错误。");
			List<CasesResult> caseResultList = new ArrayList<CasesResult>();
			caseResultList.add(caseResult);
			dr.setList(caseResultList);
			if(type.equals("xml")){
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class, CollectionWrapper.class);
				result = requestBinder.toXml(dr, "utf-8");
			}else{
				result = JsonUtil.toJson(dr);
			}
		}
		return result;
	}
	
	public String getDJZG(){
		String result = "";
		try{
			offerNumberServiceImpl.getDJZG();
		}catch(Exception e){
			result = "服务器内部错误。";
		}
		return result;
	}
	
	/**
	 * 重新取号  
	 * 与取号接口的分别是：重新取号，不管是否绑定案件，不管是否已经取号，都直接进入同步锁取号
	 * @param str
	 * @param type
	 * @return
	 */
	public String afreshOfferNumber(String str,String type){
		String result = "";
		try{
			result = offerNumberServiceImpl.afreshOfferNumber(str, type);
		}catch(Exception e){
			DataResult dr = new DataResult();
			CasesResult caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("服务器内部错误。");
			List<CasesResult> caseResultList = new ArrayList<CasesResult>();
			caseResultList.add(caseResult);
			dr.setList(caseResultList);
			if(type.equals("xml")){
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class, CollectionWrapper.class);
				result = requestBinder.toXml(dr, "utf-8");
			}else{
				result = JsonUtil.toJson(dr);
			}
		}
		return result;
	}
}


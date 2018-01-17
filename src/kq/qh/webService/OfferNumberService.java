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
 *  <p>Company:�����ݼ�������</p>
 * 
 * @title <p>ȡ�Žӿڣ�</p>
 * 		  <p>����ϵͳͨ���ýӿڻ�ȡϵͳ�����ȡ�����͵ĺ���</p>
 *        <p>���ղ���Ϊjson��ʽ��װ�Ķ���</p>
 * 		  <p>����һ��json��ʽ��װ�Ķ���</p>
 * @author ylf
 * @date 2016��8��25�� ����3:01:02
 * @version V1.0
 */
public class OfferNumberService{

	private OfferNumberServiceImpl offerNumberServiceImpl;
	
	public OfferNumberService(){
		init();
	}
	
	/**
	 * ע����Ҫ��bean
	 */
	private void init(){
		if(null == offerNumberServiceImpl){
			offerNumberServiceImpl = (OfferNumberServiceImpl)WebContextSpringBeanFactory.getBean("offerNumberServiceImpl");
		}
	}
	
	/**
	 * ȡ�Žӿ�
	 * @param str ����������
	 * @param type ���ݸ�ʽ
	 * 		<p>1��xml   2��json</p>
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
			caseResult.setErrorMsg("�������ڲ�����");
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
	 * ��������Ԫ��֤�ӿڣ�
	 */
	public String validateBDCDY (String str,String type){
		String result = "";
		try{
			offerNumberServiceImpl.validateBDCDY(str, type);
		}catch(Exception e){
			DataResult dr = new DataResult();
			CasesResult caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("�������ڲ�����");
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
			result = "�������ڲ�����";
		}
		return result;
	}
	
	/**
	 * ����ȡ��  
	 * ��ȡ�Žӿڵķֱ��ǣ�����ȡ�ţ������Ƿ�󶨰����������Ƿ��Ѿ�ȡ�ţ���ֱ�ӽ���ͬ����ȡ��
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
			caseResult.setErrorMsg("�������ڲ�����");
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


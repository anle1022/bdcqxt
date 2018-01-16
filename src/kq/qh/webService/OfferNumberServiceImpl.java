package kq.qh.webService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;

import kq.qh.common.NumberTypeInit;
import kq.qh.dao.dataSource0.AlotNumberDao;
import kq.qh.entity.AlotNumber;
import kq.qh.entity.NumberType;
import kq.qh.entity.ValidateBdcdyh;
import kq.qh.util.DateUtil;
import kq.qh.util.JaxbUtil;
import kq.qh.util.JsonUtil;
import kq.qh.util.JaxbUtil.CollectionWrapper;
import kq.qh.webService.service.WService;
import kq.qh.webService.vo.CasesParam;
import kq.qh.webService.vo.CasesResult;
import kq.qh.webService.vo.DataParam;
import kq.qh.webService.vo.DataResult;
import kq.qh.webService.vo.Flag;

@Service(value = "offerNumberServiceImpl")
public class OfferNumberServiceImpl {

	@Autowired
	private WService wService;
	@Autowired
	private AlotNumberDao alotNumberDao;
	@Autowired
	private NumberTypeInit numberTypeInit;

	/**
	 * 取号接口
	 * 
	 * @param str
	 *            待解析数据
	 * @param type
	 *            数据格式
	 *            <p>
	 *            1、xml 2、json
	 *            </p>
	 * @return
	 */
	public String offerNumber(String str, String type) throws Exception {
		CasesResult caseResult = null;
		DataParam data = null;
		DataResult dataResult = new DataResult();
		String result = "";
		List<CasesResult> caseResultList = new ArrayList<CasesResult>();

		// 解析传递过来的参数为DataParam对象
		try {
			if (type.equals("xml")) {
				data = wService.parseXML(str);
			} else {
				data = wService.parseJSON(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("参数格式不正确-->[" + str + "]");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// 验证系统id和密码不能为空
		String selfSystem = data.getSelfSystem();
		String password = data.getPassword();
		if (StringUtils.isNullOrEmpty(selfSystem)
				|| StringUtils.isNullOrEmpty(password)) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("selfSystem 或  password 为空！");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// 验证系统id，密码是否有效
		boolean flag = wService.validateSelfSystem(selfSystem, password);
		if (!flag) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("系统编号 或 密码错误-->[" + selfSystem + ", "
					+ password + "]");
			caseResultList.clear();
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// 对解析参数后的案件集合遍历，对每个案件的信息进行处理
		List<CasesResult> listResult = new ArrayList<CasesResult>();
		for (CasesParam cases : data.getList()) {
			String ajbh = cases.getAjbh();
			int size = cases.getSize();
			// 验证参数是否合法
			CasesResult casesResult = wService.validateQhParam(cases,selfSystem);
			if (casesResult.getStatus().equals(Flag.ERROR)) {
				caseResultList.clear();
				caseResultList.add(casesResult);
				dataResult.setList(caseResultList);
				if (type.equals("xml")) {
					JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
							CollectionWrapper.class);
					result = requestBinder.toXml(dataResult, "utf-8");
				} else {
					result = JsonUtil.toJson(dataResult);
				}
				return result;
			}

			// 先查询该取号类型是否绑定案件编号
			String numberTypeId = wService.getNumberTypeId(cases);
			String[] str1 = null;
			// 查看numberTypeId 是否存在“-”,若存在，用 str[]
			// 存储，str[0]+str[1]才位真正的numberTypeId
			if (numberTypeId.contains("-")) {
				str1 = numberTypeId.split("-");
				numberTypeId = str1[0] + str1[1];
			}
			NumberType numberType = numberTypeInit.getObjMap()
					.get(numberTypeId);
			List<AlotNumber> alotNumberList = new ArrayList<AlotNumber>();
			// 判断该类型的取号是否绑定案件编号，（yes）绑定案件:同一案件，每次取号之前，需要先检查是否已取过号
			if (numberType.getBind().equals("yes")) {
				AlotNumber alotNumber1 = new AlotNumber();
				alotNumber1.setNumberTypeId(numberTypeId);
				alotNumber1.setAjbh(cases.getAjbh());
//				alotNumber1.setSelfSystemId(data.getSelfSystem());
				// 若该案件经过重新取号，则之前取的号码全部标注为废号：useless,应查询出不为废号的号码集合
				alotNumber1.setBz("useless");
				alotNumberList = alotNumberDao.findEntityByCondition(alotNumber1);
			}
			if (alotNumberList.size() > 0) {
				// 存在取号记录
				
				caseResult = wService.dealHasRecord(cases, alotNumberList,
						selfSystem);
				
			} else {
				// 没有绑定案件或者
				// 案件之间没有取号，则进入同步锁直接取号
				caseResult = wService.getNumber(cases, size, selfSystem, "");
			}
			caseResult.setAjbh(ajbh);
			listResult.add(caseResult);
		}
		dataResult.setList(listResult);
		// 根据传递过来的type，确定返回值
		if (type.equals("xml")) {
			JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
					CollectionWrapper.class);
			result = requestBinder.toXml(dataResult, "utf-8");
		} else {
			result = JsonUtil.toJson(dataResult);
		}
		return result;
	}

	/*
	 * public String addCaseProcess(String str,String type){ return null;
	 * CasesResult caseResult = null; DataParam data = null; DataResult
	 * dataResult = new DataResult(); List<CasesResult> caseResultList = new
	 * ArrayList<CasesResult>(); String result = ""; //解析传递过来的参数为DataParam对象
	 * try{ if(type.equals("xml")){ data = wService.parseXML(str); }else{ data =
	 * wService.parseJSON(str); } }catch(Exception e){ caseResult = new
	 * CasesResult(); caseResult.setStatus(Flag.ERROR);
	 * caseResult.setErrorMsg("参数格式不正确-->["+str+"]");
	 * caseResultList.add(caseResult); dataResult.setList(caseResultList);
	 * if(type.equals("xml")){ JaxbUtil requestBinder = new
	 * JaxbUtil(DataResult.class, CollectionWrapper.class); result =
	 * requestBinder.toXml(dataResult, "utf-8"); }else{ result =
	 * JsonUtil.toJson(dataResult); } return result; }
	 * 
	 * String selfSystem = data.getSelfSystem(); String password =
	 * data.getPassword();
	 * if(StringUtils.isNullOrEmpty(selfSystem)||StringUtils.
	 * isNullOrEmpty(password)){ caseResult = new CasesResult();
	 * caseResult.setStatus(Flag.ERROR);
	 * caseResult.setErrorMsg("selfSystem 或  password 为空！");
	 * caseResultList.add(caseResult); dataResult.setList(caseResultList);
	 * if(type.equals("xml")){ JaxbUtil requestBinder = new
	 * JaxbUtil(DataResult.class, CollectionWrapper.class); result =
	 * requestBinder.toXml(dataResult, "utf-8"); }else{ result =
	 * JsonUtil.toJson(dataResult); } return result; } //判断该自建系统是否合法，验证密码
	 * boolean flag = wService.validateSelfSystem(selfSystem, password);
	 * if(!flag){ caseResult = new CasesResult();
	 * caseResult.setStatus(Flag.ERROR);
	 * caseResult.setErrorMsg("系统编号 或 密码错误-->["+selfSystem+", "+password+"]");
	 * caseResultList.add(caseResult); dataResult.setList(caseResultList);
	 * if(type.equals("xml")){ JaxbUtil requestBinder = new
	 * JaxbUtil(DataResult.class, CollectionWrapper.class); result =
	 * requestBinder.toXml(dataResult, "utf-8"); }else{ result =
	 * JsonUtil.toJson(dataResult); } return result; } List<CaseProcess>
	 * caseProcessList = new ArrayList<CaseProcess>(); List<CasesResult>
	 * casesResultList = new ArrayList<CasesResult>(); //所有存储失败的 casesResult
	 * List<CasesResult> casesResultFromParam = new ArrayList<CasesResult>();
	 * for(CasesParam cases:data.getList()){ String ajbh = cases.getAjbh();
	 * String djzh = cases.getDjzh(); String djlx = cases.getDjlx(); String
	 * operators = cases.getOperatorName(); String time =
	 * cases.getOperateTime(); String caseOrgCode = cases.getXzqhdm(); String
	 * operatorOrgCode = cases.getOperateOrgCode();
	 * //存储所以得casesProcess，以防批量插入失败，返回所有插入的案件List CasesResult result1 = new
	 * CasesResult(); result1.setAjbh(ajbh); result1.setStatus(Flag.ERROR);
	 * result1.setErrorMsg("服务器插入数据错误"); //验证参数是否合法 CasesResult casesResult =
	 * wService
	 * .validateParam(ajbh,djzh,djlx,operators,time,caseOrgCode,operatorOrgCode
	 * ); //合法参数的CaseProcess将存储到caseProcessList中，等待被批量插入到数据库中
	 * if(casesResult.getStatus().equals(Flag.SUCCESS)){ CaseProcess caseProcess
	 * = new
	 * CaseProcess(ajbh,djzh,djlx,operators,time,caseOrgCode,operatorOrgCode );
	 * caseProcessList.add(caseProcess); casesResultFromParam.add(result1);
	 * }else{ //不合法的参数，存在casesResultList集合中 casesResultList.add(casesResult); }
	 * 
	 * } int rows = 0; //批量插入caseProcess if(caseProcessList.size()>0){ rows =
	 * wService.addProcessList(caseProcessList); } dataResult = new
	 * DataResult(); if(rows<=0){//插入失败
	 * casesResultFromParam.addAll(casesResultList);
	 * dataResult.setList(casesResultFromParam); }else if(rows>0 ){
	 * //rows大于0且casesResultList中的size>0,则返回错误参数数据 if(casesResultList.size()>0){
	 * dataResult.setList(casesResultFromParam); }else{ List<CasesResult> list =
	 * new ArrayList<CasesResult>(); for(CaseProcess cases : caseProcessList){
	 * CasesResult casesResult = new CasesResult();
	 * casesResult.setAjbh(cases.getAjbh());
	 * casesResult.setStatus(Flag.SUCCESS);
	 * casesResult.setProcessNode(cases.getProcessNode());
	 * list.add(casesResult); } dataResult.setList(list); //return Flag.SUCCESS;
	 * } } if(type.equals("xml")){ JaxbUtil requestBinder = new
	 * JaxbUtil(DataResult.class, CollectionWrapper.class); result =
	 * requestBinder.toXml(dataResult, "utf-8"); }else{ result =
	 * JsonUtil.toJson(dataResult); } return result; }
	 */

	/**
	 * webService 不动产单元验证接口：
	 */
	public String validateBDCDY(String str, String type) throws Exception{
		CasesResult caseResult = null;
		DataParam data = null;
		DataResult dataResult = new DataResult();
		List<CasesResult> caseResultList = new ArrayList<CasesResult>();
		String result = "";
		// 解析传递过来的参数为DataParam对象
		try {
			if (type.equals("xml")) {
				data = wService.parseXML(str);
			} else {
				data = wService.parseJSON(str);
			}
		} catch (Exception e) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("参数格式不正确-->[" + str + "]");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);

			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// String selfSystemId,String password,String ajbh,String djzh,String
		// bdcdyh
		String selfSystem = data.getSelfSystem();
		String password = data.getPassword();
		if (StringUtils.isNullOrEmpty(selfSystem)
				|| StringUtils.isNullOrEmpty(password)) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("selfSystem 或  password 为空！");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// 判断该自建系统是否合法，验证密码
		boolean flag = wService.validateSelfSystem(selfSystem, password);
		if (!flag) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("系统编号 或 密码错误-->[" + selfSystem + ", "
					+ password + "]");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}

		List<CasesResult> casesResultList = new ArrayList<CasesResult>();
		List<ValidateBdcdyh> bdcdyyzList = new ArrayList<ValidateBdcdyh>();
		for (CasesParam casesParam : data.getList()) {
			CasesResult casesResult = wService.validateBDCDYYZParam(casesParam);
			if (casesResult.getStatus().equals(Flag.SUCCESS)) {
				String bdcdyh = casesParam.getBdcdyh();
				String ajbh = casesParam.getAjbh();
				String djzh = casesParam.getDjzh();
				casesResult = wService.validateBDCDY(bdcdyh);
				casesResult.setAjbh(ajbh);
				casesResult.setBdcdyh(bdcdyh);
				casesResultList.add(casesResult);
				ValidateBdcdyh bdc = new ValidateBdcdyh();
				bdc.setAjbh(ajbh);
				bdc.setDjzh(djzh);
				bdc.setBdcdyh(bdcdyh);
				bdc.setTime(DateUtil.dateToString(new Date(),
						"yyyy-MM-dd HH:mm:dd"));
				bdc.setSystemName(selfSystem);
				if (casesResult.getStatus().equals(Flag.SUCCESS)) {
					bdc.setExist("1");
				} else {
					bdc.setExist("0");
				}
				bdcdyyzList.add(bdc);
			}
		}
		if (bdcdyyzList.size() > 0) {
			wService.addbdcdyyzList(bdcdyyzList);
		}
		dataResult.setList(casesResultList);
		if (type.equals("xml")) {
			JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
					CollectionWrapper.class);
			result = requestBinder.toXml(dataResult, "utf-8");
		} else {
			result = JsonUtil.toJson(dataResult);
		}
		return result;
	}

	public String getDJZG() throws Exception{
		return wService.getDJZGLB();
	}

	/**
	 * 重新取号 与取号接口的分别是：重新取号，不管是否绑定案件，不管是否已经取号，都直接进入同步锁取号
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public String afreshOfferNumber(String str, String type) throws Exception{
		CasesResult caseResult = null;
		DataParam data = null;
		DataResult dataResult = new DataResult();
		String result = "";
		List<CasesResult> caseResultList = new ArrayList<CasesResult>();
		// 解析传递过来的参数为DataParam对象
		try {
			if (type.equals("xml")) {
				data = wService.parseXML(str);
			} else {
				data = wService.parseJSON(str);
			}
		} catch (Exception e) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("参数格式不正确-->[" + str + "]");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		// 验证密码
		String selfSystem = data.getSelfSystem();
		String password = data.getPassword();
		if (StringUtils.isNullOrEmpty(selfSystem)
				|| StringUtils.isNullOrEmpty(password)) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("selfSystem 或  password 为空！");
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}
		boolean flag = wService.validateSelfSystem(selfSystem, password);
		if (!flag) {
			caseResult = new CasesResult();
			caseResult.setStatus(Flag.ERROR);
			caseResult.setErrorMsg("系统编号 或 密码错误-->[" + selfSystem + ", "
					+ password + "]");
			caseResultList.clear();
			caseResultList.add(caseResult);
			dataResult.setList(caseResultList);
			if (type.equals("xml")) {
				JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
						CollectionWrapper.class);
				result = requestBinder.toXml(dataResult, "utf-8");
			} else {
				result = JsonUtil.toJson(dataResult);
			}
			return result;
		}

		List<CasesResult> listResult = new ArrayList<CasesResult>();
		for (CasesParam cases : data.getList()) {
			String ajbh = cases.getAjbh();
			int size = cases.getSize();
			// 验证参数是否合法
			CasesResult casesResult = wService.validateQhParam(cases,selfSystem);
			if (casesResult.getStatus().equals(Flag.ERROR)) {
				caseResultList.clear();
				caseResultList.add(casesResult);
				dataResult.setList(caseResultList);
				if (type.equals("xml")) {
					JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
							CollectionWrapper.class);
					result = requestBinder.toXml(dataResult, "utf-8");
				} else {
					result = JsonUtil.toJson(dataResult);
				}
				return result;
			}
			// 先查询该取号类型是否绑定案件编号
			String numberTypeId = wService.getNumberTypeId(cases);
			String[] str1 = null;
			// 查看numberTypeId 是否存在“-”,若存在，用 str[]
			// 存储，str[0]+str[1]才位真正的numberTypeId
			if (numberTypeId.contains("-")) {
				str1 = numberTypeId.split("-");
				numberTypeId = str1[0] + str1[1];
			}
			NumberType numberType = numberTypeInit.getObjMap()
					.get(numberTypeId);
			List<AlotNumber> alotNumberList = new ArrayList<AlotNumber>();
			// 判断该类型的取号是否绑定案件编号
			if (numberType.getBind().equals("yes")) {
				AlotNumber alotNumber1 = new AlotNumber();
				alotNumber1.setNumberTypeId(numberTypeId);
				alotNumber1.setAjbh(cases.getAjbh());
				alotNumber1.setSelfSystemId(data.getSelfSystem());
				alotNumberList = alotNumberDao
						.findEntityByCondition(alotNumber1);
			}
			if (alotNumberList.size() > 0) {
				// 存在取号记录,将备注改成无效
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("alotNumber", alotNumberList);
				map.put("bz", "useless");
				alotNumberDao.updateBZ(map);
			}
			
			caseResult = wService.getNumber(cases, size, selfSystem, "重新取号");
			caseResult.setAjbh(ajbh);
			listResult.add(caseResult);

		}
		dataResult.setList(listResult);
		// dataResult.setDjzglb(wService.getDJZGLB());
		if (type.equals("xml")) {
			JaxbUtil requestBinder = new JaxbUtil(DataResult.class,
					CollectionWrapper.class);
			result = requestBinder.toXml(dataResult, "utf-8");
		} else {
			result = JsonUtil.toJson(dataResult);
		}
		return result;
	}
}

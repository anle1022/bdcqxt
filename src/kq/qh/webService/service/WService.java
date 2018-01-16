package kq.qh.webService.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

import kq.qh.common.DeptInit;
import kq.qh.common.NumberTypeInit;
import kq.qh.dao.dataSource0.DictDao;
import kq.qh.dao.dataSource0.AlotNumberDao;
import kq.qh.dao.dataSource0.CaseProcessDao;
import kq.qh.dao.dataSource0.NumberTypeDao;
import kq.qh.dao.dataSource0.SelfSystemDao;
import kq.qh.dao.dataSource0.SerialNumberDao;
import kq.qh.dao.dataSource0.UselessNumberDao;
import kq.qh.dao.dataSource0.ValidateBdcdyhDao;
import kq.qh.entity.AlotNumber;
import kq.qh.entity.CaseProcess;
import kq.qh.entity.Dept;
import kq.qh.entity.Dict;
import kq.qh.entity.NumberType;
import kq.qh.entity.SelfSystem;
import kq.qh.entity.SerialNumber;
import kq.qh.entity.Type;
import kq.qh.entity.UselessNumbers;
import kq.qh.entity.ValidateBdcdyh;
import kq.qh.service.SerialNumberService;
import kq.qh.service.TypeService;
import kq.qh.util.CollectionUtil;
import kq.qh.util.DateUtil;
import kq.qh.util.JaxbUtil;
import kq.qh.util.JsonUtil;
import kq.qh.util.TransformUtils;
import kq.qh.util.JaxbUtil.CollectionWrapper;
import kq.qh.webService.vo.CasesParam;
import kq.qh.webService.vo.CasesResult;
import kq.qh.webService.vo.DataParam;
import kq.qh.webService.vo.Flag;

@Service(value="wService")
public class WService {
	
	private static Map<String,Object> keymap = Collections.synchronizedMap( new HashMap<String,Object>());
	
	@Autowired
	private AlotNumberDao alotNumberDao;
	
	@Autowired
	private NumberTypeDao numberTypeDao;
	
	@Autowired
	private SelfSystemDao selfSystemDao;
	
	@Autowired
	private SerialNumberDao serialNumberDao;
	
	@Autowired
	private UselessNumberDao uselessNumberDao;
	
	@Autowired
	private CaseProcessDao caseProcessDao;
	
	@Autowired
	private DeptInit deptInit;
	
	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private NumberTypeInit numberTypeInit;
	
	@Autowired
	private ValidateBdcdyhDao validateBdcdyhDao;
	
	@Autowired
	private SerialNumberService serialNumberService;
	
	@Autowired
	private TypeService typeService;
	
	/**
	 * 验证系统id和密码的有效性
	 * @param selfSystemId
	 * @param password
	 * @return
	 */
	public boolean validateSelfSystem(String selfSystemId,String password){
		SelfSystem selfSystem = new SelfSystem();
		selfSystem.setId(selfSystemId);
		selfSystem.setPassword(password);
		selfSystem.setStatus("1");
		int i = selfSystemDao.validateEntity(selfSystem);
		if(i==0){
			return false;
		}else{
			return true; 
		}
		
	}
	
	/**
	 * 将xml格式参数成  @DataParam 实体类
	 * @param xmlStr
	 * @return
	 * @throws JAXBException
	 */
	public DataParam parseXML(String xmlStr) throws JAXBException{
		JaxbUtil resultBinder = new JaxbUtil(DataParam.class, CollectionWrapper.class);		
		DataParam data = resultBinder.fromXml(xmlStr);		
		return data;
	}

	/**
	 * 将json格式参数成  @DataParam 实体类
	 * @param xmlStr
	 * @return
	 * @throws JAXBException
	 */
	public DataParam parseJSON(String str) {
		return (DataParam) JsonUtil.toObj(str, DataParam.class);	
	}
	
	/**
	 * title 同步锁取号
	 * @param casesParam 案件信息
	 * @param size 取号个数
	 * @param 取号系统id
	 * @return 
	 */
	@Transactional
	public CasesResult getNumber(CasesParam casesParam,int size,String selfSystemId,String bz){
		Type type = new Type();
		type.setId(casesParam.getNumberType());
		type = typeService.findAll(type);
		Integer onlyOne = type.getIsdistinctebySystem();
		String sysid  = null;
		CasesResult result = new CasesResult();
		List<String> numbers = new ArrayList<String>();
		//当前年
		int year = Integer.parseInt( DateUtil.getYear() );
		String numberTypeId = getNumberTypeId(casesParam);
		String[] str = null;
		//查看numberTypeId 是否存在“-”,若存在，用 str[] 存储，str[0]+str[1]才位真正的numberTypeId
		if(numberTypeId.contains("-")){
			str = numberTypeId.split("-");
			numberTypeId = str[0]+str[1];
			String numberType = casesParam.getNumberType();
			/* 萝岗特殊处理，萝岗和黄埔号段一致
			 * 		登记字号用11+6位流水号
			 *      证书号用 用06+6位流水号
			 */
			if(numberType.contains("DJZH")&&str[1].equals("05")){
				str[1] = "11";
			}else if(( casesParam.getNumberType().contains("BDCQZSH") || casesParam.getNumberType().contains("BDCDJZMH")) && str[1].equals("12") ){
				str[1] = "06";
			}
		}
		//流水号id是同步锁对象
		String serialNumberId = numberTypeId+"_"+year;
		SerialNumber seria = new SerialNumber();
		seria.setIsRunOut(0);//号段没有用完
		seria.setId(serialNumberId);
		if (onlyOne.equals(1)) {//索取号码分配到系统
			seria.setSelfSystemId(selfSystemId);
		}
		else {
			try {
				SerialNumber serialNumber = serialNumberDao.findEntityById(seria);
			} catch (Exception e) {
				result.setStatus(Flag.ERROR);
				result.setErrorMsg("系统不可以同时存在两条相同类型记录！");
				return result;
			}
		}
		if(null == keymap.get(serialNumberId)){ 
			keymap.put(serialNumberId, seria);
		}
		synchronized(keymap.get(serialNumberId)){//同步锁
			NumberType numberType = numberTypeDao.findEntityById(numberTypeId);
			//获取流水号信息
			SerialNumber serialNumber = serialNumberDao.findEntityById(seria);
			if(null == serialNumber){
				result.setStatus(Flag.ERROR);
				result.setErrorMsg("系统：["+selfSystemId+"] 没有配置 取号类型["+serialNumberId+"] 或号段号码已用完！");
				return result;
			}
			sysid = serialNumber.getPkid();
			int curNumber = serialNumber.getCurNumber();//流水号当前值
			//流水号最大值是否超过当前值+当前取号数量 
			if(serialNumber.getMaxNumber()<curNumber+size){ 
				result.setStatus(Flag.ERROR);
				result.setErrorMsg("所取号码已经超过最大值！请通知维护人员重新在配置。");
				//修改数据表  状态由    0    变    1
				serialNumber.setIsRunOut(1);
				serialNumberService.updateEntity(serialNumber);
				return result;
			}
			
			List<AlotNumber> alotNumberlist = new ArrayList<AlotNumber>();
			//流水号长度
			int numberLength = numberType.getNumberLength();
			for(int i=0;i<size;i++){
				int number = curNumber+i;
				//左补零
				String numberStr = leftContactZore(number,numberLength); 
				if(null != str){
					numberStr = str[1]+numberStr;
				}
				//将numberStr转换为需要返回的号码
				String no = TransformUtils.getQHNumber(numberType.getRule(), year, numberStr);
				numbers.add( no );
				AlotNumber alotNumber = new AlotNumber();
				alotNumber.setAjbh(casesParam.getAjbh());
				alotNumber.setNumbers( no );
				alotNumber.setNumberTypeId(numberTypeId);
				if (onlyOne.equals(1)) {
					alotNumber.setSelfSystemId(selfSystemId);
				}else {
					alotNumber.setSelfSystemId("");
				}
				alotNumber.setCaseOrgCode(casesParam.getXzqhdm());
				alotNumber.setOperateOrgCode(casesParam.getOperateOrgCode());
				alotNumber.setOperatorName(casesParam.getOperatorName());
				alotNumber.setBz(bz);
				alotNumberlist.add(alotNumber);
			}
			//将取号数据存入到取号表中
			try{
				alotNumberDao.addEntityInBatch( alotNumberlist );
			}catch(Exception e){
				e.printStackTrace();
				result.setStatus(Flag.ERROR);
				result.setErrorMsg("流水号当前值在系统中已取号，请联系管理员修改流水号当前值！");
				return result;
			}
			//返回数据
			SerialNumber serialNumber1 = new SerialNumber();
			serialNumber1.setId(serialNumberId);
			serialNumber1.setCurNumber(serialNumber.getCurNumber()+size);
			serialNumber1.setPkid(sysid);
			serialNumberDao.updateEntity(serialNumber1);
			result.setAjbh(casesParam.getAjbh());
			result.setStatus(Flag.SUCCESS);
			result.setNumbers(CollectionUtil.joinSplitStr(numbers, ","));
			return result;
		}
	}
	
	/**
	 * 
	 * @Title 同一案件处理第二次或多次取号
	 * @param param
	 * @param alotNumberList
	 * @return
	 * @author ylf
	 * @version V1.0
	 */
	@Transactional
	public CasesResult dealHasRecord(CasesParam casesParam,List<AlotNumber> alotNumberList,String selfSystemId){
		int paramSize = casesParam.getSize();
		String numberTypeId = getNumberTypeId(casesParam);
		String[] str = null;
		//查看numberTypeId 是否存在“，”,若存在，用 str[] 存储，str[0]+str[1]才位真正的numberTypeId
		if(numberTypeId.contains("-")){
			str = numberTypeId.split("-");
			numberTypeId = str[0]+str[1];
		}
		String year = DateUtil.getYear();
		CasesResult caseResult = new CasesResult();
		//案件在数据库中存在记录数
		int size = alotNumberList.size();
		List<String> numbers = new ArrayList<String>();
		for(AlotNumber value : alotNumberList){
			numbers.add(value.getNumbers());
		}
		if(size==paramSize){
			caseResult.setNumbers( CollectionUtil.joinSplitStr(numbers, ",") );
			//result.setReturnMsg("success: 锟截革拷锟结交;锟斤拷锟斤拷锟斤拷锟�+paramSize+" 锟斤拷锟斤拷  锟斤拷录锟斤拷锟斤拷"+size+";  锟斤拷锟斤拷菘锟斤拷谢锟饺�"+ArrayUtil.joinSplitStr(numbers, ",") );
		}else if(size > paramSize){
			List<UselessNumbers> list = new ArrayList<UselessNumbers>();
			List<String> numbers1 = new ArrayList<String>();
			List<String> uselessNumberList = new ArrayList<String>();
			for(int i=0;i<size;i++){
				if(i>paramSize-1){
					//alotNumber表中的数据多余要取得数据时，将numbers 放到useless表中
					UselessNumbers uselessNumber = new UselessNumbers();
					uselessNumber.setUselessNumber(numbers.get(i));
					uselessNumber.setNumberTypeId(numberTypeId);
					uselessNumber.setYear(year);
					UselessNumbers uselessNumbers = uselessNumberDao.findEntityByCondition(uselessNumber);
					if(null == uselessNumbers){
						list.add(uselessNumber);
					}
					//锟斤拷询alotNumber锟斤拷锟斤拷锟角凤拷锟斤拷锟斤拷锟斤拷
					AlotNumber alotNumber = new AlotNumber();
					alotNumber.setNumbers(numbers.get(i));
					AlotNumber alotNumbers = alotNumberDao.findEntityByCondition(alotNumber).get(0);
					if(null != alotNumbers){
						uselessNumberList.add(numbers.get(i));
					}
					//uselessNumberDao.addEntity(uselessNumber);
				}else{
					numbers1.add( numbers.get(i) );
				}
			}
			if(list.size()>0){
				uselessNumberDao.addEntityInBatch(list);
				
			}
			if(uselessNumberList.size()>0){
				//锟斤拷alotNumber锟斤拷锟斤拷删锟斤拷锟窖废筹拷暮锟斤拷锟�
				alotNumberDao.deleteEntityInBatch(uselessNumberList);
			}
			caseResult.setNumbers(CollectionUtil.joinSplitStr(numbers1, ","));
		}else{
			//小锟节诧拷锟斤拷锟絪ize锟斤拷锟斤拷要取param.getSize-size 锟斤拷锟斤拷锟斤拷
			//String serialNumberId = numberTypeId+"_"+year;
			caseResult = getNumber(casesParam,paramSize-size,selfSystemId,"");
			if(caseResult.getStatus().equals("error")){
				return caseResult;
			}
			String[] numbers1 = caseResult.getNumbers().split(",");
			List<String> numbers2 = new ArrayList<String>();
			//String numbers2[] = new String[paramSize];
			for(int i=0;i<numbers.size();i++){
				numbers2.add(numbers.get(i));
				
			}
			for(int i=0;i<numbers1.length;i++){
				numbers2.add(numbers1[i]);
			}
			caseResult.setNumbers(CollectionUtil.joinSplitStr(numbers2, ","));
			caseResult.setStatus(Flag.SUCCESS);
			//result.setResult(numbers2);
			//result.setReturnMsg("success: 锟截革拷锟结交;锟斤拷锟斤拷锟斤拷锟�+paramSize+"  锟斤拷锟斤拷   锟斤拷录锟斤拷锟斤拷"+size+";  锟斤拷锟斤拷菘锟斤拷谢锟饺�"+ArrayUtil.joinSplitStr(numbers1, ",") );			
		}
		caseResult.setStatus(Flag.SUCCESS);
		return caseResult;
	}
	
	/**
	 * 1	DJZH	登记字号	 2	BDCQZSH	不动产权证书号	3	BDCDJZMH	不动产登记证明号
	 * @param casesParam
	 * @return
	 */
	public String getNumberTypeId(CasesParam casesParam){
		String numberType = casesParam.getNumberType();
		Type type = new Type();
		type.setId(numberType);
		type = typeService.findAll(type);
		Integer numq = type.getDistinctebyXzqh();
		String orgCode = casesParam.getOperateOrgCode();
		Dept dept = deptInit.getDeptMap().get(orgCode);
		
		if(numberType.contains("DJZH")){	
			//取登记字号时，登记字轨(djzg)不能为空
			String djzg = casesParam.getDjzg();
			if(StringUtils.isNullOrEmpty(djzg)){
				return Flag.ERROR+":[取登记字号时，登记字轨(djzg)不能为空]";		
			}
			if(numq.equals(1)){
				if(null == dept){
					return Flag.ERROR+":[行政区-"+orgCode+"没有配置登记字轨部门编号]";		
				}
				numberType = numberType+casesParam.getDjzg()+"-"+dept.getDjzgNo();
			}else {
				numberType = numberType+casesParam.getDjzg();
			}
		}else if(casesParam.getNumberType().contains("BDCQZSH") || casesParam.getNumberType().contains("BDCDJZMH")){
			if(numq.equals(1)){
				if(null == dept){
					return Flag.ERROR+":[行政区-"+orgCode+"没有配置证书部门编号]";		
				}
				numberType = casesParam.getNumberType()+"-"+dept.getZsNo();
			}
		}else{
			if(numq.equals(1)){
				if(null == dept){
					return Flag.ERROR+":[行政区-"+orgCode+"没有配置部门编号]";		
				}
				numberType = numberType+"-"+dept.getDjzgNo();
			}
		}
		return numberType;
	}
	
	/**
	 * 
	 * @Title 左边补0 
	 * @param number  
	 * @param length  
	 * @return
	 * @author ylf
	 * @version V1.0
	 */
	public String leftContactZore(int number,int length){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(length);
        nf.setMinimumIntegerDigits(length);
        return nf.format(number);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("list1");
		list.add("list2");
		list.add("list3");
		
		String str = JsonUtil.toJson(list);
		System.out.println(str);
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) JsonUtil.toObj(str, List.class);
		for(String v:list1){
			System.out.println(v);
		}
	}

	public CasesResult validateParam(String ajbh, String djzh, String djlx,
			String operators, String time, String caseOrgCode, String operatorOrgCode) {
		CasesResult result = new CasesResult();
		result.setStatus("SUCCESS");
		return result;
	}

	@Transactional
	public int addProcessList(List<CaseProcess> caseProcessList) {
		return caseProcessDao.addEntityInBatch(caseProcessList);
	}

	public String getDJZGLB() {
		List<Dict> dictList = dictDao.findEntityList("ZGGF");
		StringBuffer str = new StringBuffer();
		for(int i=0;i<dictList.size();i++){
			String value = dictList.get(i).getNameCN();
			str.append(value);
			if(i<dictList.size()){
				str.append(",");
			}
		}
		String value = str.toString();
		if(!StringUtils.isNullOrEmpty(value)){
			value = value.substring(0,value.length()-1);
		}
		return value;
	}

	/**
	 * 验证取号参数合法性
	 * @param cases 案件实体类
	 * @return
	 */
	public CasesResult validateQhParam(CasesParam cases,String selfSystem) {
		Type type = new Type();
		type.setId(cases.getNumberType());
		type = typeService.findAll(type);
		Integer num = type.getIsdistinctebySystem();
		Integer numq = type.getDistinctebyXzqh();
		CasesResult casesResult = new CasesResult();
		casesResult.setStatus(Flag.ERROR);
		//验证案件编号
		String ajbh = cases.getAjbh();
		if(StringUtils.isNullOrEmpty(ajbh)){
			casesResult.setErrorMsg("[案件编号(ajbh)不能为空！]");
			return casesResult;
		}
		//取号类型不能为空
		String numberTypeId = cases.getNumberType(); 
		if(StringUtils.isNullOrEmpty(numberTypeId)){
			casesResult.setErrorMsg("[取号类型(numberType)不存在!]");
			return casesResult;
		}
		//operateOrgCode不能为空
		if(numq.equals(1)){
			String operateOrgCode = cases.getOperateOrgCode();
			if(StringUtils.isNullOrEmpty( operateOrgCode ) ){
				casesResult.setErrorMsg("[办理登记机构代码(operateOrgCode)不能为空！]");
				return casesResult;		
			}
		}	
		//验证取号类型是否存在
		numberTypeId = getNumberTypeId(cases);
		String[] str = null;
		//查看numberTypeId 是否存在“-”,若存在，用 str[] 存储，str[0]+str[1]才位真正的numberTypeId
		if(numberTypeId.contains("-")){
			str = numberTypeId.split("-");
			numberTypeId = str[0]+str[1];
		}
		if(numberTypeId.contains(Flag.ERROR)){
			casesResult.setErrorMsg(numberTypeId);
			return casesResult;
		}
		NumberType numberType = numberTypeInit.getObjMap().get(numberTypeId);
		if(null == numberType){
			casesResult.setErrorMsg("[地区： "+cases.getXzqhdm()+" 取号类型为： "+numberTypeId+" 的取号类型不存在!]");
			return casesResult;
		}
		
		//取号个数必须大于0
		int size = cases.getSize();
		if(size<=0){
			casesResult.setErrorMsg("[取号个数："+size+" 必须为大于0的整数！]");
			return casesResult;		
		}
		
		//caseOrgCode不能为空
		String xzqhdm = cases.getXzqhdm();
		if(StringUtils.isNullOrEmpty( xzqhdm )){
			casesResult.setErrorMsg("[行政区域编码(xzqhdm)不能为空！]");
			return casesResult;		
		}
		//operatorTime办理时间不能为空
		/*if (StringUtils.isNullOrEmpty(cases.getOperateTime())) {
			casesResult.setErrorMsg("[办理时间(operatorTime)不能为空!]");
			return casesResult;		
		}*/
		//operatorName业务办理人不能为空
		if (StringUtils.isNullOrEmpty(cases.getOperatorName())) {
			casesResult.setErrorMsg("[业务办理人(operatorName)不能为空!]");
			return casesResult;		
		}
		//验证取号类型的流水号serialNumberId是否已经设置
		String serialNumberId =numberTypeId+"_"+DateUtil.getYear();
		SerialNumber serailNum = new SerialNumber();
		serailNum.setId(serialNumberId);
		if (num.equals(1)) {
			serailNum.setSelfSystemId(selfSystem);
		}
//		else {
//			List<SerialNumber> serailNumbers = serialNumberDao.findEntityList(serailNum);
//			for (SerialNumber serber : serailNumbers) {
//				if (null == serber.getSelfSystemId()) {
//					serailNum.setPkid(serber.getPkid());
//				}
//			}
//		}
		serailNum.setIsRunOut(0);
		SerialNumber serailNumber = serialNumberDao.findEntityById(serailNum);
//		List<SerialNumber> serailNumber = serialNumberDao.findEntityById(serailNum);
		if(null == serailNumber){
			casesResult.setErrorMsg("[系统(selfSystem):["+selfSystem+"],取号类型(numberType):["+numberTypeId+"]在"+ DateUtil.getYear() +"年未配置流水号！");
			return casesResult;		
		}
		casesResult.setStatus(Flag.SUCCESS);
		return casesResult;
	}
	
	
	
	/**
	 * webService  
	 * 不动产单元验证接口：
	 */
	public CasesResult validateBDCDY(String bdcdyh){
		CasesResult result = new CasesResult();
		try{
			int status = validateBdcdyhDao.validateBDCDY(bdcdyh);
			if (status > 0) {
				result.setStatus(Flag.SUCCESS);
			} else {
				result.setStatus(Flag.ERROR);
				result.setErrorMsg("不动产单元号:["+bdcdyh+"]没有纳入权籍系统中。");
			}
		}catch(Exception e){
			result.setStatus(Flag.ERROR);
			result.setErrorMsg("系统正忙。");
		}
		return result;
	}

	public void deleteUselessNumber(List<AlotNumber> alotNumberList, DataParam data) {
		List<UselessNumbers> uselessNumberList = new ArrayList<UselessNumbers>();
		List<String> numbersList = new ArrayList<String>();
		for(AlotNumber v :alotNumberList){
			UselessNumbers useless = new UselessNumbers();
			useless.setUselessNumber(v.getNumbers());
			useless.setNumberTypeId(v.getNumberTypeId());
			useless.setYear(DateUtil.getYear());
			useless.setAjbh(v.getAjbh());
			useless.setSelfSystemId(data.getSelfSystem());
			useless.setReason("重新取号，上次取得号码作废!");
			uselessNumberList.add(useless);
			numbersList.add(v.getNumbers());
		}
		if(uselessNumberList.size()>0){
			uselessNumberDao.addEntityInBatch(uselessNumberList);
			
		}
		if(numbersList.size()>0){
			//锟斤拷alotNumber锟斤拷锟斤拷删锟斤拷锟窖废筹拷暮锟斤拷锟�
			alotNumberDao.deleteEntityInBatch(numbersList);
		}
	}

	public void addbdcdyyzList(List<ValidateBdcdyh> bdcdyyzList) {
		validateBdcdyhDao.addEntityListInBatch(  bdcdyyzList);
	}

	public CasesResult validateBDCDYYZParam(CasesParam casesParam) {
		CasesResult casesResult = new CasesResult();
		casesResult.setStatus(Flag.ERROR);
		String ajbh = casesParam.getAjbh();
		if(StringUtils.isNullOrEmpty(ajbh)){
			casesResult.setErrorMsg("[案件编号(ajbh)不能为空！]");
			return casesResult;
		}
		String djzh = casesParam.getDjzh();
		if(StringUtils.isNullOrEmpty(djzh)){
			casesResult.setErrorMsg("[登记字号(djzh)不能为空！]");
			return casesResult;
		}
		String bdcdyh = casesParam.getBdcdyh();
		if(StringUtils.isNullOrEmpty(bdcdyh)){
			casesResult.setErrorMsg("[不动产单元号(bdcdyh)不能为空！]");
			return casesResult;
		} 
		casesResult.setStatus(Flag.SUCCESS);
		return casesResult;
	}
	
}

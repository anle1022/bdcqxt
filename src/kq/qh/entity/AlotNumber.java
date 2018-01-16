package kq.qh.entity;

import java.util.Date;

import kq.qh.util.DateUtil;

/**
 * @Package kq.qh.entity
 *          <p>
 *          Copyright: Copyright (c) 2016
 *          </p>
 *          <p>
 *          Company:�����ݼ�������
 *          </p>
 * 
 * @title ȡ����Ϣ��
 * @author ylf
 * @date 2016��8��18�� ����1:54:33
 * @version V1.0
 */
public class AlotNumber {
	// id,ajbh,numbers,numberTypeId,orgCode,selfSystemId,time,status,failureReason
	private String id;
	private String ajbh;
	private String numbers;
	private String numberTypeId;
	private String selfSystemId;
	private String time;
	//行政区划代码
	private String caseOrgCode;
	//办理机构代码
	private String operateOrgCode;
	private String bz;
	private String operatorName;
	private SelfSystem selfSystem;
	private Organization organization;
	private NumberType numberType;
	private NumberType numberType1;
	private SerialNumber serialNumber;
	private SetNumber setNumber;
	private String num;
	private String name1;
	private String name;
	
	public AlotNumber() {
		this.time = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getNumberTypeId() {
		return numberTypeId;
	}

	public void setNumberTypeId(String numberTypeId) {
		this.numberTypeId = numberTypeId;
	}

	public String getSelfSystemId() {
		return selfSystemId;
	}

	public void setSelfSystemId(String selfSystemId) {
		this.selfSystemId = selfSystemId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCaseOrgCode() {
		return caseOrgCode;
	}

	public void setCaseOrgCode(String caseOrgCode) {
		this.caseOrgCode = caseOrgCode;
	}

	public String getOperateOrgCode() {
		return operateOrgCode;
	}

	public void setOperateOrgCode(String operateOrgCode) {
		this.operateOrgCode = operateOrgCode;
	}

	public SelfSystem getSelfSystem() {
		return selfSystem;
	}

	public void setSelfSystem(SelfSystem selfSystem) {
		this.selfSystem = selfSystem;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public NumberType getNumberType() {
		return numberType;
	}
	public void setNumberType(NumberType numberType) {
		this.numberType = numberType;
		this.numberType1 = numberType;
	}
	public NumberType getNumberType1() {
		return numberType;
	}
	public void setNumberType1(NumberType numberType1) {
		this.numberType1 = this.numberType;
	}

	public SerialNumber getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(SerialNumber serialNumber) {
		this.serialNumber = serialNumber;
	}

	public SetNumber getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(SetNumber setNumber) {
		this.setNumber = setNumber;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

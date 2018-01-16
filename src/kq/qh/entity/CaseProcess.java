package kq.qh.entity;


import kq.qh.common.Excel;

public class CaseProcess {

	private String id;
	//案件编号
	@Excel(name = "ajbh", value = "案件编号") 
	private String ajbh;
	//登记字号
	@Excel(name = "djzh", value = "登记字号") 
	private String djzh;
	
	//登记类型
	private String djlx;
	private String djlx1;//一级分类
	
	//案件办理人
	@Excel(name = "operator", value = "流程办理人员") 
	private String operator;
	
	//流程办理时间
	@Excel(name = "operateTime", value = "办理时间") 
	private String operateTime;
	
	private String bdcdyh;
	//自建系统id
	private String selfSystemId;
	private String caseOrgCode;
	private String operateOrgCode;
	
	@Excel(name = "processNode", value = "办理环节") 
	private String processNode;
	private int orderBy;
	private Integer sum;
	private Organization organization1;
//	private Organization organization2;
	private Dept dept;
	private String orgCode;
	
	@Excel(name = "extendedTime", value = "应办结时间") 
	private String extendedTime;//超期时间
	
	@Excel(name = "slsj", value = "受理时间") 
	private String slsj;
	public String getDjzh() {
		return djzh;
	}

	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}

	public Organization getOrganization1() {
		return organization1;
	}

	public void setOrganization1(Organization organization1) {
		this.organization1 = organization1;
	}
 
	public CaseProcess(){}
	
	public CaseProcess(String ajbh,String djzh,String djlx,String operator,
			String operateTime,String caseOrgCode,String operateOrgCode){
		this.ajbh = ajbh;
		this.djzh = djzh;
		this.djlx = djlx;
		this.operator = operator;
		this.operateTime = operateTime;
		this.caseOrgCode = caseOrgCode;
		this.operateOrgCode = operateOrgCode;
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
	public String getDjlx() {
		return djlx;
	}
	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperateTime() {
		
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		if(operateTime.length()>=10){
			operateTime = operateTime.substring(0, 10);
		}
		this.operateTime = operateTime;
	}
	public String getSelfSystemId() {
		return selfSystemId;
	}
	public void setSelfSystemId(String selfSystemId) {
		this.selfSystemId = selfSystemId;
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
	public String getProcessNode() {
		return processNode;
	}
	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getBdcdyh() {
		return bdcdyh;
	}

	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}

	public String getDjlx1() {
		return djlx1;
	}

	public void setDjlx1(String djlx1) {
		this.djlx1 = djlx1;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getExtendedTime() {
		return extendedTime;
	}

	public void setExtendedTime(String extendedTime) {
		this.extendedTime = extendedTime;
	}

	public String getSlsj() {
		return slsj;
	}

	public void setSlsj(String slsj) {
		this.slsj = slsj;
	}
	
	

}

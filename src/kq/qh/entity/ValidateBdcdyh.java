package kq.qh.entity;

import kq.qh.common.Excel;

public class ValidateBdcdyh {
	
	private String id;
	@Excel(name = "ajbh", value = "案件编号") 
	private String ajbh;
	private String djzh;
	@Excel(name = "bdcdyh", value = "不动产单元号") 
	private String bdcdyh;
	@Excel(name = "exist", value = "是否存在") 
	private String exist;
	@Excel(name = "time", value = "验证时间") 
	private String time;
	@Excel(name = "systemName", value = "系统名称") 
	private String systemName;
	private String orgCode;
	private int sum;
	private Organization organization;
	
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
	public String getDjzh() {
		return djzh;
	}
	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}
	public String getBdcdyh() {
		return bdcdyh;
	}
	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	
}

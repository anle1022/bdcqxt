package kq.qh.entity;

public class Dept {

	private String id;
	private String orgCode;
	private String orgName;
	private String djzgNo;
	private String zsNo;
	private Organization organization;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDjzgNo() {
		return djzgNo;
	}
	public void setDjzgNo(String djzgNo) {
		this.djzgNo = djzgNo;
	}
	public String getZsNo() {
		return zsNo;
	}
	public void setZsNo(String zsNo) {
		this.zsNo = zsNo;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}

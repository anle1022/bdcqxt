package kq.qh.entity;

/**
 * @Package kq.qh.entity
 *  <p>Copyright: Copyright (c) 2016 </p>
 *  <p>Company:苍穹广州技术中心</p>
 * 
 * @title 自建系统entity
 * @author ylf
 * @date 2016年8月18日 上午11:43:50
 * @version V1.0
 */
public class SelfSystem {
	
	private String id;
	private String name;
	private Integer orgCode;
	private String password;
	private String status;
	private Organization organization;
	/*
	 * 该自建系统能对哪些取号类型的进行取号操作
	 */
	private String numberTypes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getNumberTypes() {
		return numberTypes;
	}
	public void setNumberTypes(String numberTypes) {
		this.numberTypes = numberTypes;
	}
	
	
	
	
}

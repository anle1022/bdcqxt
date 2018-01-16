package kq.qh.entity;


/**
 * @Title:组织
 * @Package kq.dh.entity
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:苍穹广州技术中心
 * 
 * @author ylf
 * @date 2016年8月1日 上午10:07:12
 * @version V1.0
 */
public class Organization {
	private String orgCode;
	private String orgName;
	
	private String id;
	private String text;
	private String url;
	
	
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.id = orgCode;
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.text = orgName;
		this.orgName = orgName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

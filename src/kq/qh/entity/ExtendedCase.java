package kq.qh.entity;

import kq.qh.common.Excel;
 

public class ExtendedCase {

	
	private String id;
	
	@Excel(name = "djzh", value = "登记字号") 
	private String djzh;
	
	private String status;
	
	@Excel(name = "djzh", value = "应办结时间") 
	private String time;
	
	@Excel(name = "startTime", value = "受理时间") 
	private String startTime;
	private String orgCode;
	private int sum;
	//private Organization organization;
	private String orgName;
	private Integer total;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDjzh() {
		return djzh;
	}
	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}

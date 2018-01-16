package kq.qh.entity;

import kq.qh.common.Excel;

public class Validate1 {
	
	@Excel(name = "orgCode", value = "地区") 
	private String orgCode;
	@Excel(name = "exist", value = "是否存在") 
	private String exist;
	@Excel(name = "sum", value = "数量") 
	private int sum;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
}

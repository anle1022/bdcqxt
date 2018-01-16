package kq.qh.webService.vo;

import javax.xml.bind.annotation.XmlElement;

public class CasesResult {
	
	//案件编号
	private String ajbh;
	
	//返回状态
	private String status;
	
	//返回号码集合
	private String numbers;
	
	//返回信息
	private String errorMsg;
	
	//private String processNode;
	//不动产单元号
	private String bdcdyh;
	
	@XmlElement(name="ajbh")
	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	@XmlElement(name="numbers")
	public String getNumbers() {
		return numbers;
	}

	public void setNumbers( String numbers) {
		this.numbers = numbers;
	}

	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlElement(name="errorMsg")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
/*	@XmlElement(name="processNode")
	public String getProcessNode() {
		return processNode;
	}

	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}
*/
	@XmlElement(name="bdcdyh")
	public String getBdcdyh() {
		return bdcdyh;
	}

	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	
	
	
}

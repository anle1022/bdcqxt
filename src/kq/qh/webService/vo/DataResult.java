package kq.qh.webService.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DataResult")
public class DataResult {
	
	//自建系统id
	private String selfSystem;
	
	//自建系统密码
	private String password;
	
	//返回信息案件参数集合
	private List<CasesResult> list;

	//登记字轨集合
	private String djzglb;
	
	@XmlElement(name="selfSystem")
	public String getSelfSystem() {
		return selfSystem;
	}

	public void setSelfSystem(String selfSystem) {
		this.selfSystem = selfSystem;
	}
	
	@XmlElement(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElementWrapper(name="list")
	@XmlElement(name="CasesResult")
	public List<CasesResult> getList() {
		return list;
	}

	public void setList(List<CasesResult> list) {
		this.list = list;
	}

	@XmlElement(name="djzglb")
	public String getDjzglb() {
		return djzglb;
	}

	public void setDjzglb( String  djzglb) {
		this.djzglb = djzglb;
	}
	
}
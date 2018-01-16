package kq.qh.webService.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>ȡ�Žӿڴ���xmlת����</p>
 * @author Administrator
 *
 */
@XmlRootElement(name = "DataParam")
public class DataParam {
	
	//自建系统id
	private String selfSystem;
	
	//自建系统密码
	private String password;
	
	//案件参数集合
	private List<CasesParam> list;

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
	@XmlElement(name="CasesParam")
	public List<CasesParam> getList() {
		return list;
	}

	public void setList(List<CasesParam> list) {
		this.list = list;
	}
	
}
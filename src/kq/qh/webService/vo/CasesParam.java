package kq.qh.webService.vo;
 
import javax.xml.bind.annotation.XmlElement;

public class CasesParam {
	
	//案件编号
	private String ajbh;//1   2
	
	//登记字号
	private String djzh;//2
	
	//登记字轨
	private String djzg;
	
	//登记类型
	//private String djlx;//2    
	
	//取号个数
	private int size;
	
	//流程办理时间
	private String operateTime;//2
	
	//流程办理人员
	private String operatorName;//2
	
	//行政区划代码
	private String xzqhdm;//2
	
	//案件办理登记点代码
	private String operateOrgCode;//2
	
	//�������̽ڵ�
	//private String processNode;//2
	
	//取号类型
	private String numberType;//1
	
	//不动产单元号
	private String bdcdyh;
	
	@XmlElement(name="ajbh")
	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	@XmlElement(name="djzh")
	public String getDjzh() {
		return djzh;
	}

	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}

/*	@XmlElement(name="djlx")
	public String getDjlx() {
		return djlx;
	}

	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}*/

/*	@XmlElement(name="year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}*/
	
	@XmlElement(name="operateTime")
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	@XmlElement(name="operatorName")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	@XmlElement(name="xzqhdm")
	public String getXzqhdm() {
		return xzqhdm;
	}

	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}

	@XmlElement(name="operateOrgCode")
	public String getOperateOrgCode() {
		return operateOrgCode;
	}

	public void setOperateOrgCode(String operateOrgCode) {
		this.operateOrgCode = operateOrgCode;
	}

/*	@XmlElement(name="processNode")
	public String getProcessNode() {
		return processNode;
	}

	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}*/
	
	@XmlElement(name="numberType")
	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}
	
	@XmlElement(name="size")
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@XmlElement(name="djzg")
	public String getDjzg() {
		return djzg;
	}

	public void setDjzg(String djzg) {
		this.djzg = djzg;
	}

	@XmlElement(name="bdcdyh")
	public String getBdcdyh() {
		return bdcdyh;
	}

	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}

	
 
	
}

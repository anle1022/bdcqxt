package kq.qh.schedule.entity;

import kq.qh.common.DictMap;

/**
 * 权利人信息
 * @author Administrator
 *
 */
public class QLRxx {
	private String ssxt;//所属系统			SSXT	Char
	private String ywbh;//案件编号			YWBH	Char
	private String bdcdyh;//不动产单元号	BDCDYH	Char
	private String bdcqzh;//不动产产权证号	BDCQZH	Varchar
	private String qlr;//权利人			QLR	Char
	private String qlrlb;//权利人类别		QLRLB	Char
	private String qlrzjlx;//权利人证件类型	QLRZJLX	Char
	private String qlrzjbh;//权利人证件编号	QLRZJBH	Char
	private String szfe;//占有份额			SZFE	
	private String zt;//状态				ZT	Char
	public String getSsxt() {
		return ssxt;
	}
	public void setSsxt(String ssxt) {
		this.ssxt = ssxt;
	}
	public String getYwbh() {
		return ywbh;
	}
	public void setYwbh(String ywbh) {
		this.ywbh = ywbh;
	}
	public String getBdcdyh() {
		return bdcdyh;
	}
	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	public String getBdcqzh() {
		return bdcqzh;
	}
	public void setBdcqzh(String bdcqzh) {
		this.bdcqzh = bdcqzh;
	}
	public String getQlr() {
		return qlr;
	}
	public void setQlr(String qlr) {
		this.qlr = qlr;
	}
	public String getQlrlb() {
		return qlrlb;
	}
	public void setQlrlb(String qlrlb) {
		this.qlrlb = DictMap.dictMap.get("qlrlb").get(qlrlb);
	}
	public String getQlrzjlx() {
		return qlrzjlx;
	}
	public void setQlrzjlx(String qlrzjlx) {
		this.qlrzjlx = DictMap.dictMap.get("qlrzjlx").get(qlrzjlx);
	}
	public String getQlrzjbh() {
		return qlrzjbh;
	}
	public void setQlrzjbh(String qlrzjbh) {
		this.qlrzjbh = qlrzjbh;
	}
	public String getSzfe() {
		return szfe;
	}
	public void setSzfe(String szfe) {
		this.szfe = szfe;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		
		this.zt = DictMap.dictMap.get("zt").get(zt);
	}
	

}

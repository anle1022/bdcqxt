package kq.qh.schedule.entity;
/**
 * 审批信息
 * @author Administrator
 *
 */
public class SPBxx {
	private String ssxt;//所属系统			SSXT	Char
	private String ywbh;//案件编号			YWBH	Char
	private String djzh;//登记字（轨）号		DJZH	Char
	private String csryxm;//初审人员姓名	SHRYXM	Char
	private String csjssj;//初审结束时间	SHJSSJ	Date
	private String csyj;//初审意见			SHYJ	Varchar
	private String shryxm;//审核人员姓名	SHRYXM	Char
	private String shjssj;//审核结束时间	SHJSSJ	Date
	private String shyj;//审核意见			SHYJ	Varchar
	private String spryxm;//审批人员姓名	SPRYXM	Char
	private String spjssj;//审批结束时间	SPJSSJ	Date
	private String spyj;//审批意见			SPYJ	Varchar
	private String bz;//备注				BZ		Varchar
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
	public String getDjzh() {
		return djzh;
	}
	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}
	public String getCsryxm() {
		return csryxm;
	}
	public void setCsryxm(String csryxm) {
		this.csryxm = csryxm;
	}
	public String getCsjssj() {
		return csjssj;
	}
	public void setCsjssj(String csjssj) {
		this.csjssj = csjssj;
	}
	public String getCsyj() {
		return csyj;
	}
	public void setCsyj(String csyj) {
		this.csyj = csyj;
	}
	public String getShryxm() {
		return shryxm;
	}
	public void setShryxm(String shryxm) {
		this.shryxm = shryxm;
	}
	public String getShjssj() {
		return shjssj;
	}
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getSpryxm() {
		return spryxm;
	}
	public void setSpryxm(String spryxm) {
		this.spryxm = spryxm;
	}
	public String getSpjssj() {
		return spjssj;
	}
	public void setSpjssj(String spjssj) {
		this.spjssj = spjssj;
	}
	public String getSpyj() {
		return spyj;
	}
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

	
}

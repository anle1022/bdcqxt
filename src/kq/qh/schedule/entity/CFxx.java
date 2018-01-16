package kq.qh.schedule.entity;
/**
 * 查封信息
 * @author Administrator
 *
 */
public class CFxx {
	private String ssxt;//所属系统			SSXT	Char
	private String ywbh;//案件编号			YWBH	Char
	private String djzh;//登记字号			DJZH	Char
	private String bdcdyh;//不动产单元号	BDCDYH	Char
	private String cfjg;//查封机关			CFJG	Char
	private String cflx;//查封类型			CFLX	Char
	private String cfwh;//查封文号			CFWH	Char
	private String cfqssj;//查封起始时间	CFQSSJ	Date
	private String cfjssj;//查封结束时间	CFJSSJ	Date
	private String cffw;//查封范围			CFFW	Varchar
	private String cfdjsj;//查封登记时间	CFDJSJ	Date
	private String jfjg;//解封机关			JFJG	Char
	private String jfwj;//解封文件			JFWJ	Varbin
	private String jfwh;//解封文号			JFWH	Char
	private String jfdjsj;//解封登记时间	JFDJSJ	Date
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
	public String getBdcdyh() {
		return bdcdyh;
	}
	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	public String getCfjg() {
		return cfjg;
	}
	public void setCfjg(String cfjg) {
		this.cfjg = cfjg;
	}
	public String getCflx() {
		return cflx;
	}
	public void setCflx(String cflx) {
		this.cflx = cflx;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCfqssj() {
		return cfqssj;
	}
	public void setCfqssj(String cfqssj) {
		this.cfqssj = cfqssj;
	}
	public String getCfjssj() {
		return cfjssj;
	}
	public void setCfjssj(String cfjssj) {
		this.cfjssj = cfjssj;
	}
	public String getCffw() {
		return cffw;
	}
	public void setCffw(String cffw) {
		this.cffw = cffw;
	}
	public String getCfdjsj() {
		return cfdjsj;
	}
	public void setCfdjsj(String cfdjsj) {
		this.cfdjsj = cfdjsj;
	}
	public String getJfjg() {
		return jfjg;
	}
	public void setJfjg(String jfjg) {
		this.jfjg = jfjg;
	}
	public String getJfwj() {
		return jfwj;
	}
	public void setJfwj(String jfwj) {
		this.jfwj = jfwj;
	}
	public String getJfwh() {
		return jfwh;
	}
	public void setJfwh(String jfwh) {
		this.jfwh = jfwh;
	}
	public String getJfdjsj() {
		return jfdjsj;
	}
	public void setJfdjsj(String jfdjsj) {
		this.jfdjsj = jfdjsj;
	}

	
}

package kq.qh.schedule.entity;
/**
 * 产权信息
 * @author Administrator
 *
 */
public class CQxx {
	private String ssxt;//所属系统			SSXT	Char    
	private String ywbh;//案件编号			YWBH	Char
	private String cqzh;//不动产产权证号 		cqzh 	char
	private String bdcdyh;//不动产单元号   	bdcdyh  char
	private String djzh;//登记字号		Char
	private String djlx;//登记类型			DJLX	Char
	private String ydjlx;//原登记类型		YDJLX	Char
	private String qllx;//权利类型			QLLX	Char
	private String yqllx;//原权利类型		YQLLX	Char
	private String tdzl;//坐落			TDZL	Char
	private Float cqmj;//产权面积			CQMJ	Float
	private Integer sfdqlr;//是否共用（有）	SFDQLR	Int
	private String tfdh;//图幅地号			TFDH	Char
	private String txql;//抵押情况			TXQL	Char
	private String cfqk;//查封情况			CFQK	Char
	private String qtx;//其他他项			TXQL	Char
	private String fzqk;//附注情况			FZQK	Char
	private String qzlx;//群众来信			QZLX	Char
	private String gdn;//公代逆			GDN		Char
	private String qtxzsm;//其他他项说明	QTXZSM	Varchar
	private String fzrq;//发证日期			FZRQ	Date
	private String qszt;//权属状态			QSZT	Char
	private String zhaj;//是否组合案件		ZHAJ	Char
	private String fwyt;//房屋用途			FWYT	Varchar
	private String zddm;//宗地地号			ZDDM	Char
	private Float zdfzmj;//宗地发证面积		ZDFZMJ	Float
	private Float fcfzmj;//房产发证面积		FCFZMJ	Float
	private String qsxz;//土地权属性质		QSXZ	Char
	private String syqlx;//土地使用权类型	SYQLX	Char
	private String tdyt;//土地用途			TDYT	Char
	private String tdsyqx;//土地使用期限	TDSYQX	Char
	private String tdzzrq;//土地终止日期	TDZZRQ	Date
	private String fwxz;//房屋性质			FWXZ	Char
	private String fwjg;//房屋结构			FWJG	Char
	private String fwyszh;//房屋预售证号	FWYSZH	Char
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
	public String getCqzh() {
		return cqzh;
	}
	public void setCqzh(String cqzh) {
		this.cqzh = cqzh;
	}
	public String getBdcdyh() {
		return bdcdyh;
	}
	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	public String getDjzh() {
		return djzh;
	}
	public void setDjzh(String djzh) {
		this.djzh = djzh;
	}
	public String getDjlx() {
		return djlx;
	}
	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}
	public String getYdjlx() {
		return ydjlx;
	}
	public void setYdjlx(String ydjlx) {
		this.ydjlx = ydjlx;
	}
	public String getQllx() {
		return qllx;
	}
	public void setQllx(String qllx) {
		this.qllx = qllx;
	}
	public String getYqllx() {
		return yqllx;
	}
	public void setYqllx(String yqllx) {
		this.yqllx = yqllx;
	}
	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	public Float getCqmj() {
		return cqmj;
	}
	public void setCqmj(Float cqmj) {
		this.cqmj = cqmj;
	}
	public Integer getSfdqlr() {
		return sfdqlr;
	}
	public void setSfdqlr(Integer sfdqlr) {
		this.sfdqlr = sfdqlr;
	}
	public String getTfdh() {
		return tfdh;
	}
	public void setTfdh(String tfdh) {
		this.tfdh = tfdh;
	}
	public String getTxql() {
		return txql;
	}
	public void setTxql(String txql) {
		this.txql = txql;
	}
	public String getCfqk() {
		return cfqk;
	}
	public void setCfqk(String cfqk) {
		this.cfqk = cfqk;
	}
	public String getQtx() {
		return qtx;
	}
	public void setQtx(String qtx) {
		this.qtx = qtx;
	}
	public String getFzqk() {
		return fzqk;
	}
	public void setFzqk(String fzqk) {
		this.fzqk = fzqk;
	}
	public String getQzlx() {
		return qzlx;
	}
	public void setQzlx(String qzlx) {
		this.qzlx = qzlx;
	}
	public String getGdn() {
		return gdn;
	}
	public void setGdn(String gdn) {
		this.gdn = gdn;
	}
	public String getQtxzsm() {
		return qtxzsm;
	}
	public void setQtxzsm(String qtxzsm) {
		this.qtxzsm = qtxzsm;
	}
	public String getFzrq() {
		return fzrq;
	}
	public void setFzrq(String fzrq) {
		this.fzrq = fzrq;
	}
	public String getQszt() {
		return qszt;
	}
	public void setQszt(String qszt) {
		this.qszt = qszt;
	}
	public String getZhaj() {
		return zhaj;
	}
	public void setZhaj(String zhaj) {
		this.zhaj = zhaj;
	}
	public String getFwyt() {
		return fwyt;
	}
	public void setFwyt(String fwyt) {
		this.fwyt = fwyt;
	}
	public String getZddm() {
		return zddm;
	}
	public void setZddm(String zddm) {
		this.zddm = zddm;
	}
	public Float getZdfzmj() {
		return zdfzmj;
	}
	public void setZdfzmj(Float zdfzmj) {
		this.zdfzmj = zdfzmj;
	}
	public Float getFcfzmj() {
		return fcfzmj;
	}
	public void setFcfzmj(Float fcfzmj) {
		this.fcfzmj = fcfzmj;
	}
	public String getQsxz() {
		return qsxz;
	}
	public void setQsxz(String qsxz) {
		this.qsxz = qsxz;
	}
	public String getSyqlx() {
		return syqlx;
	}
	public void setSyqlx(String syqlx) {
		this.syqlx = syqlx;
	}
	public String getTdyt() {
		return tdyt;
	}
	public void setTdyt(String tdyt) {
		this.tdyt = tdyt;
	}
	public String getTdsyqx() {
		return tdsyqx;
	}
	public void setTdsyqx(String tdsyqx) {
		this.tdsyqx = tdsyqx;
	}
	public String getTdzzrq() {
		return tdzzrq;
	}
	public void setTdzzrq(String tdzzrq) {
		this.tdzzrq = tdzzrq;
	}
	public String getFwxz() {
		return fwxz;
	}
	public void setFwxz(String fwxz) {
		this.fwxz = fwxz;
	}
	public String getFwjg() {
		return fwjg;
	}
	public void setFwjg(String fwjg) {
		this.fwjg = fwjg;
	}
	public String getFwyszh() {
		return fwyszh;
	}
	public void setFwyszh(String fwyszh) {
		this.fwyszh = fwyszh;
	}

	
}

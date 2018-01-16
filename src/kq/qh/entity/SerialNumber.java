package kq.qh.entity;

public class SerialNumber {
	
	private String id;
	private String numberTypeId;
	private String selfSystemId;
	private Integer minNumber;
	private Integer curNumber;
	private Integer maxNumber;
	private Integer year;
	private NumberType numberType;
	private Integer isRunOut;
	private SelfSystem selfSystem;
	private String pkid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}
	public Integer getCurNumber() {
		return curNumber;
	}
	public void setCurNumber(Integer curNumber) {
		this.curNumber = curNumber;
	}
	public Integer getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getNumberTypeId() {
		return numberTypeId;
	}
	public void setNumberTypeId(String numberTypeId) {
		this.numberTypeId = numberTypeId;
	}
	public NumberType getNumberType() {
		return numberType;
	}
	public void setNumberType(NumberType numberType) {
		this.numberType = numberType;
	}
	public String getSelfSystemId() {
		return selfSystemId;
	}
	public void setSelfSystemId(String selfSystemId) {
		this.selfSystemId = selfSystemId;
	}
	public SelfSystem getSelfSystem() {
		return selfSystem;
	}
	public void setSelfSystem(SelfSystem selfSystem) {
		this.selfSystem = selfSystem;
	}
	public Integer getIsRunOut() {
		return isRunOut;
	}
	public void setIsRunOut(Integer isRunOut) {
		this.isRunOut = isRunOut;
	}
	
	
}

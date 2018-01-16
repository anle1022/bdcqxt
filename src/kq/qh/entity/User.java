package kq.qh.entity;

import java.util.List;

public class User {
	//userid,loginname,username,t.loginpassword,orgcode,invaliddate
	
	
	private String userid;
	private String loginname;
	private String username;
	private String password;
	private Integer orgCode;
	private String invaliddate;
	private List<Menu> menuList;
	private List<RegistType> registTypeList;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	public String getInvaliddate() {
		return invaliddate;
	}
	public void setInvaliddate(String invaliddate) {
		this.invaliddate = invaliddate;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public List<RegistType> getRegistTypeList() {
		return registTypeList;
	}
	public void setRegistTypeList(List<RegistType> registTypeList) {
		this.registTypeList = registTypeList;
	}
	
	
	
	
}

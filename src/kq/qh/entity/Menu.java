package kq.qh.entity;

import java.util.List;

public class Menu {

	private String id;
	private String text;
	private String url;
	private String iconCls;
	private List<Menu> children;
	private Integer leaf; 
	private String pid;
	private String resourceKey;
	private String state = "closed";  
	private String function;
	private Integer sortOrder;
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		if(leaf == 1){
			this.state = "open";
		}
		this.leaf = leaf;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String toString(){
		return this.id+" "+this.text+" "+this.url+" "+this.leaf;
	}
	
}

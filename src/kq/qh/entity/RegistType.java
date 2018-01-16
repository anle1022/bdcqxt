package kq.qh.entity;

import java.util.List;

public class RegistType {
	
	private String id;
	private String pid;
	private String text;
	private Integer leaf;
	private List<RegistType> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	public List<RegistType> getChildren() {
		return children;
	}
	public void setChildren(List<RegistType> children) {
		this.children = children;
	}
	
	
}

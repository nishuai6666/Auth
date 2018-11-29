package com.imooc.auth.common.tree;

import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node>{

	private Long id;
	private Long parentId;
	private String text;
	private String state;
	private NodeAttribute attribute;
	private List<Node> children = new LinkedList<>();
	private Integer order;
	
	public Node(Long id,Long parentId,String text,String state,NodeAttribute attribute,Integer order){
		this.id = id;
		this.parentId = parentId;
		this.text = text;
		this.state = state;
		this.attribute = attribute;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public NodeAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(NodeAttribute attribute) {
		this.attribute = attribute;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

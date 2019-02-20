package com.zs.pms.po;

import java.io.Serializable;

public class TRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 725105010813073408L;
	
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TRole [id=" + id + ", name=" + name + "]";
	}
	
}

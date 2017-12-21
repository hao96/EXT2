package com.yh.domian;

/**
 * inode
 * @author hao
 *
 */
public class Inode {
	
	public Inode(int id, String fname, String type, int size, String time, int[] location, String supdir, int used) {
		super();
		this.id = id;
		this.fname = fname;
		this.type = type;
		this.size = size;
		this.time = time;
		this.location = location;
		this.supdir = supdir;
		this.used = used;
	}
	/*
	 * inode的id
	 */
	private int id;
	/*
	 * 文件的名字
	 */
	private String fname;
	/*
	 * 文件的类型
	 */
	private String type;
	/*
	 * 文件大小
	 */
	private int size;
	/*
	 * 创建时间
	 */
	private String time;
	/*
	 * 在块中的位置
	 */
	private int[] location;
	/*
	 * 上一级目录
	 */
	private String supdir;
	/*
	 * 是否被使用
	 * 0 没被使用
	 * 1 被使用
	 */
	private int used;
	
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public String getType() {
		return type;
	}
	public int getSize() {
		return size;
	}
	public String getTime() {
		return time;
	}
	public int[] getLocation() {
		return location;
	}
	public String getSupdir() {
		return supdir;
	}
	public int getUsed() {
		return used;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setLocation(int[] location) {
		this.location = location;
	}
	public void setSupdir(String supdir) {
		this.supdir = supdir;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	
	
}

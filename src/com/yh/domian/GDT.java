package com.yh.domian;

/**
 * 组块描述表
 * @author hao
 *
 */
public class GDT{

	/*
	 * inode 开始的位置
	 */
	private int Ilocalting = 2;
	/**
	 * block 开始的位置
	 */
	private int Blocalting = 10;
	/**
	 * 还有多少空闲的inode
	 */
	private static int Inumleft = 12;
	/**
	 * 还有多少剩余的block
	 */
	private static int Bnumleft = 100;
	
	
	public int getIlocalting() {
		return Ilocalting;
	}
	public int getBlocalting() {
		return Blocalting;
	}

	public static int getInumleft() {
		return Inumleft;
	}
	public static int getBnumleft() {
		return Bnumleft;
	}
	public static void setInumleft(int inumleft) {
		Inumleft = inumleft;
	}
	public static void setBnumleft(int bnumleft) {
		Bnumleft = bnumleft;
	}


	
	
	
	
}

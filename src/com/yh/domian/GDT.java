package com.yh.domian;

/**
 * ���������
 * @author hao
 *
 */
public class GDT{

	/*
	 * inode ��ʼ��λ��
	 */
	private int Ilocalting = 2;
	/**
	 * block ��ʼ��λ��
	 */
	private int Blocalting = 10;
	/**
	 * ���ж��ٿ��е�inode
	 */
	private static int Inumleft = 12;
	/**
	 * ���ж���ʣ���block
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

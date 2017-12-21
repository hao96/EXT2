package com.yh.domian;

/**
 * ������
 * @author hao
 *
 */
public class SuperBlock {

	/**
	 * ���С��/kb��
	 */
	private static final int bsize = 1;
	
	/*
	 * ������С(/b)
	 */
	private static final int indexsize = 64;
	
	/*
	 * �ļ�ϵͳ�İ汾��
	 */
	private static final String fsversion = "1.1";
	
	/*
	 * ����С(/kb)
	 */
	private static final int GBsize = 100;
	
	/*
	 * inode��С(/kb)
	 */
	private static final int isize = 8;

	public static int getBsize() {
		return bsize;
	}

	public static int getIndexsize() {
		return indexsize;
	}

	public static String getFsversion() {
		return fsversion;
	}

	public static int getGbsize() {
		return GBsize;
	}

	public static int getIsize() {
		return isize;
	}
	
	
}

package com.yh.domian;

/**
 * 超级块
 * @author hao
 *
 */
public class SuperBlock {

	/**
	 * 块大小（/kb）
	 */
	private static final int bsize = 1;
	
	/*
	 * 索引大小(/b)
	 */
	private static final int indexsize = 64;
	
	/*
	 * 文件系统的版本号
	 */
	private static final String fsversion = "1.1";
	
	/*
	 * 组块大小(/kb)
	 */
	private static final int GBsize = 100;
	
	/*
	 * inode大小(/kb)
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

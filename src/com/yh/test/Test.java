package com.yh.test;

public class Test {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("aaaaaa");
		sb.delete(0, 10);
		System.out.println(sb.toString());
		sb.append("fsaf");
		System.out.println(sb.toString());
	}
}

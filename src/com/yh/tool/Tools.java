package com.yh.tool;

public class Tools {

	/*
	 * ������ת�����ַ���
	 */
	public static String arrayTurn2Str(int[] a){
		if(a != null){
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < a.length ; i++){
				sb.append(a[i]+" ");
			}
			return sb.toString();
		}else{
			return "null";
		}
	}
}

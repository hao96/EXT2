package com.yh.service;

/*
 * 有关bitmap的业务逻辑
 */
public class BitmapService {

	/*
	 * 初始化blockbitmap
	 */
	public static int[][] InitBlockBitmap(){
		int[][] bitmap = new int[10][10];
		for(int i = 0 ; i < 10 ; i++){
			for(int j = 0 ; j < 10 ; j++){
				bitmap[i][j] = 0;
			}
		}
		
		bitmap[0][0] = 1;
		
		return bitmap;
	}
	
	/*
	 * 初始化inodebitmap
	 */
	public static int[][] InitInodeBitmap(){
		int[][] bitmap = new int[3][4];
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 4 ; j++){
				bitmap[i][j] = 0;
			}
		}
		
		bitmap[0][0] = 1;
		
		return bitmap;
	}
	
	/*
	 * 显示blockbitmap
	 */
	public static void ShowBlockBitmap(int[][] bitmap){
		System.out.println("------------block bitmap--------------------");
		for(int i = 0 ; i < 10 ; i++){
			for(int j = 0 ; j < 10 ; j++){
				System.out.print("\t" + bitmap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}
	
	/*
	 * 显示Initbitmap
	 */
	public static void ShowIdoneBitmap(int[][] bitmap){
		System.out.println("------------idone bitmap--------------------");
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 4 ; j++){
				System.out.print("\t" + bitmap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}

	/*
	 * 标记被使用的inode
	 */
	public static void identifyInode(int id, int[][] inodeBitmap) {
		System.out.println(id);
		inodeBitmap[(id-1)/4][(id-1)%4] = 1;
	}

	/*
	 * 标记被使用过的block
	 */
	public static int[] identifyBlock(int i, int[][] blockBitmap) {
		int[] unused = new int[i];
		
		int temp = 1;
		
		for(int k = 1 ; k < 10 ; k++){
			for(int j = 0 ; j < 10 ; j++){
				if(blockBitmap[k][j] == 0 && i >= temp){
					unused[temp - 1] = k * 10 + j + 1;
					blockBitmap[k][j] = 1;
					temp++;
				}
			}
		}
		
		return unused;
	}

	public static void freeBlock(int[] location, int[][] blockBitmap) {
		for(int i = 0 ; i < location.length ; i++){
			blockBitmap[location[i]/10][location[i]%10 - 1] = 0;
		}
	}

	public static void freeInode(int id, int[][] inodeBitmap) {
		inodeBitmap[id/4][id%4-1] = 0;
	}

	public static int[] identifyBlockDir() {
		int[] a = {1};
		return a;
	}
}

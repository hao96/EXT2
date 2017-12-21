package com.yh.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yh.domian.GDT;
import com.yh.domian.Inode;
import com.yh.domian.SuperBlock;
import com.yh.tool.Tools;

/*
 * 有关inode业务逻辑
 */
public class InodeService {

	/*
	 * 初始化inode表
	 */
	public static List<Inode> initInodeTable(){
		List<Inode> inodeTable = new ArrayList<>();
		
		int[] a = new int[1];
		a[0] = 2;
		inodeTable.add(new Inode(1, "/", "dir", 1, LocalDate.now().toString().trim(), a, "$", 1));
		
		for(int i = 2 ; i < 12 ; i++){
			inodeTable.add(new Inode(i, "-", "-", 0, "-", null, "-", 0));
		}
		
		return inodeTable;
	}
	
	/*
	 * 显示inode表
	 */
	public static void showInodeTable(List<Inode> table){
		System.out.println("--------inode table----------");
		System.out.println("id\tname\ttype\tsize\tsupdir\tused\tlocal\ttime");
		for(Inode i : table){
			System.out.println(i.getId()+"\t"+i.getFname()+"\t"+i.getType()+"\t"+i.getSize()+"\t"+i.getSupdir()+"\t"+i.getUsed()+"\t"+Tools.arrayTurn2Str(i.getLocation())+"\t"+i.getTime());
		}
		System.out.println("-----------end-----------------");
	}

	/*
	 * 查看是否有这个文件
	 */
	public static boolean hasThisFile(String fn, List<Inode> inodes) {
		for(Inode i : inodes){
			if(fn.equals(i.getFname())){
				return true;
			}
		}
		return false;
	}

	/*
	 * 创建一个文件
	 */
	public static List<Inode> touching(String name, String size,List<Inode> list , Inode Gf, int[][] inodeBitmap, int[][] blockBitmap) {
		if(!hasThisFile(name, list) && GDT.getInumleft() > 0 && GDT.getBnumleft() > (Integer.parseInt(size)/SuperBlock.getBsize())){
			Inode inode = InodeService.getAFreeInode(list,inodeBitmap);
			
			inode.setFname(name);
			inode.setSize(Integer.parseInt(size));
			inode.setSupdir(Gf.getFname());
			inode.setTime(LocalDate.now().toString());
			inode.setType("file");
			inode.setUsed(1);
			inode.setLocation(BitmapService.identifyBlock(Integer.parseInt(size)/SuperBlock.getBsize() , blockBitmap));
			
			GDT.setInumleft(GDT.getInumleft() - 1);
			GDT.setBnumleft(GDT.getBnumleft() - Integer.parseInt(size)/SuperBlock.getBsize());
		}
		return list;
	}

	/*
	 * 得到一个空闲的inode
	 */
	private static Inode getAFreeInode(List<Inode> list, int[][] inodeBitmap) {
		Inode inode = null;
		for(Inode d : list){
			if(d.getUsed() == 0){
				inode = d;
				BitmapService.identifyInode(d.getId() , inodeBitmap);
				break;
			}
		}
		return inode;
	}

	/*
	 * 删除文件
	 */
	public static void rmFile(String fn, List<Inode> inodes, Inode gfile, int[][] inodeBitmap,
			int[][] blockBitmap) {
		for(Inode i : inodes){
			if(fn.equals(i.getFname())){
				GDT.setInumleft(GDT.getInumleft() + 1);
				GDT.setBnumleft(GDT.getBnumleft() + i.getSize()/SuperBlock.getBsize());
				clearInodeAndBlock(i,inodeBitmap,blockBitmap);
				break;
			}
		}
		
	}

	/*
	 * 清空这个文件信息
	 */
	private static Inode clearInodeAndBlock(Inode i, int[][] inodeBitmap, int[][] blockBitmap) {
		
		i.setFname("-");
		i.setSize(0);
		i.setSupdir("-");
		i.setTime("-");
		i.setType("-");
		i.setUsed(0);
		
		BitmapService.freeBlock(i.getLocation(),blockBitmap);
		BitmapService.freeInode(i.getId(),inodeBitmap);
		
		i.setLocation(null);
		
		return i;
	}

	/*
	 * 创建文件夹
	 */
	public static void mkdir(String dirname, List<Inode> inodes, Inode gfile, int[][] inodeBitmap, int[][] blockBitmap) {
		if(!hasThisFile(dirname, inodes) && GDT.getInumleft() > 0){
		Inode inode = getAFreeInode(inodes, inodeBitmap);
		inode.setFname(dirname);
		inode.setSize(1);
		inode.setSupdir(gfile.getFname());
		inode.setTime(LocalDate.now().toString());
		inode.setType("dir");
		inode.setUsed(1);
		inode.setLocation(BitmapService.identifyBlockDir());
		
		GDT.setInumleft(GDT.getInumleft() - 1);
	}
		}

	/*
	 * 判断是否是目录
	 */
	public static boolean isDir(String fn, List<Inode> inodes) {
		
		for(Inode i : inodes){
			if(fn.equals(i.getFname()) && i.getType().equals("dir")){
				return true;
			}
		}
		return false;
	}

	/*
	 * 初始化当前目录
	 */
	public static List<Inode> initCurrentDir(List<Inode> inodes, Inode gfile) {
		List<Inode> dir = new ArrayList<>();
		for(Inode i : inodes){
			if(i.getSupdir().equals(gfile.getFname())){
				System.out.println(i.getSupdir() + " " + gfile.getFname());
				dir.add(i);
			}
		}
		return dir;
	}

	/*
	 * 进入下一级目录
	 */
	public static Inode cd(String fn, List<Inode> inodes, StringBuffer sb) {
		
		Inode gfile = null;
		
		//全局变量
		for(Inode i : inodes){
			if(i.getFname().equals(fn)){
				gfile = i;
				break;
			}
		}
		
		//控制目录
		String str = sb.toString().split(":")[0];
		sb.delete(0, 100);
		sb.append(str + "/" + fn + ":");
		
		return gfile;
	}

	/*
	 * 展示当前目录
	 */
	public static void lsCrrentDir(List<Inode> dir, List<Inode> inodes, Inode gfile) {

		dir.clear();
		for(Inode i : inodes){
			if(i.getSupdir().equals(gfile.getFname())){
				dir.add(i);
			}
		}
	}

	/*
	 * cd..
	 */
	public static Inode cdBack(Inode gfile, List<Inode> inodes, StringBuffer sb) {
		Inode g = null;
		
		for(Inode i : inodes){
			if(i.getFname().equals(gfile.getSupdir())){
				g = i;
				break;
			}
		}
		
		
		String[] str = sb.toString().split("/");
		sb.delete(0, 100);
		for(int i = 0 ; i < str.length ; i++){
			if(i == str.length - 1){
				sb.append(":");
				break;
			}
			sb.append(str[i]);			
		}
		
		return g;
	}

	/*
	 * 删除目录
	 */
	public static void rmdir(String fn, List<Inode> inodes, Inode gfile, int[][] inodeBitmap, int[][] blockBitmap) {
		for(Inode i : inodes){
			if(fn.equals(i.getFname())){
				GDT.setInumleft(GDT.getInumleft() + 1);
				clearInodeAndBlock(i,inodeBitmap,blockBitmap);
				break;
			}
		}
		
	}

	/*
	 * 打印目录
	 */
	public static void showDir(List<Inode> dir) {
		System.out.println("name\ttype\tsize\ttime\t\t");
		for(Inode i : dir){
			System.out.println(i.getFname()+"\t"+i.getType()+"\t"+i.getSize()+"\t"+i.getTime());
		}
	}
	
}

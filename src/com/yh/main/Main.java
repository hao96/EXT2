package com.yh.main;

import java.util.List;
import java.util.Scanner;

import com.yh.domian.Inode;
import com.yh.service.BitmapService;
import com.yh.service.InodeService;

/**
 * 程序入口
 * @author hao
 *
 */
public class Main {

	public static void main(String[] args) {
		//全局变量
		Inode Gfile;

		System.out.println("欢迎使用EXT2文件系统!");
		//初始化bitmap
		int[][] blockBitmap = BitmapService.InitBlockBitmap();
		int[][] inodeBitmap = BitmapService.InitInodeBitmap();
		//初始化inode表
		List<Inode> inodes = InodeService.initInodeTable();
		//将根目录设置成全局变量
		Gfile = inodes.get(0);
		//当前目录
		List<Inode> dir = InodeService.initCurrentDir(inodes,Gfile);
		
		StringBuffer sb = new StringBuffer();
		sb.append("localhost@yh$"+"\\:");
		//功能区
		while(true){
			System.out.print(sb.toString());
			//输入的内容
			String inputStr = new Scanner(System.in).nextLine();
			String firstStr = inputStr.split(" ")[0];
			//选择语句
			switch (firstStr) {
			case "cd":
				if(inputStr.split(" ").length != 2){
					System.out.println("error：命令格式错误！");
				}else{
					if(!InodeService.isDir(inputStr.split(" ")[1] , inodes)){
						System.out.println("error:目录不存在.");
						continue;
					}else{
						//cd
						Gfile = InodeService.cd(inputStr.split(" ")[1] , inodes, sb);
					}
				}
				break;
				
			case "cd..":
				if(inputStr.split(" ").length != 1){
					System.out.println("error：命令格式错误！");
				}else{
					//cd..
					Gfile = InodeService.cdBack(Gfile,inodes,sb);
				}
				break;

			case "ls":
				if(inputStr.split(" ").length == 2){
					InodeService.showInodeTable(inodes);
				}else{
					//显示当前目录的文件信息
					InodeService.lsCrrentDir(dir,inodes,Gfile);
					InodeService.showDir(dir);
				}
				
				break;
				
			case "touch":
				if(inputStr.split(" ").length != 3){
					System.out.println("error：命令格式错误！");
				}else{
					if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
						System.out.println("error:文件名已存在.");
						continue;
					}
					inodes = InodeService.touching(inputStr.split(" ")[1],inputStr.split(" ")[2], inodes , Gfile , inodeBitmap , blockBitmap);
					System.out.println("创建成功！");
				}
				break;
				
			case "rm":
				if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
					InodeService.rmFile(inputStr.split(" ")[1] , inodes , Gfile , inodeBitmap , blockBitmap);
				}else{
					System.out.println("此文件不存在");
				}     
				break;
			
			case "mkdir":
				if(inputStr.split(" ").length != 2){
					System.out.println("error：命令格式错误！");
				}else{
					if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
						System.out.println("error：文件名已存在.");
						continue;
					}else{
						InodeService.mkdir(inputStr.split(" ")[1] , inodes, Gfile , inodeBitmap , blockBitmap);
					}
				}
				break;
			
			case "rmdir":
				if(inputStr.split(" ").length == 2 ||InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
					InodeService.rmdir(inputStr.split(" ")[1] , inodes, Gfile , inodeBitmap , blockBitmap);				
				}else{
					System.out.println("此目录不存在");
				}
				break;
				
			case "show":
				if(inputStr.split(" ")[1].equals("blockBitmap")){
					//显示块的位视图
					BitmapService.ShowBlockBitmap(blockBitmap);
				}else if(inputStr.split(" ")[1].equals("inodeBitmap")){
					//显示inode的位视图
					BitmapService.ShowIdoneBitmap(inodeBitmap);
				}else{
					System.out.println("error：命令格式错误！");
				}
				break;
			
			
			}
		}
	}
}

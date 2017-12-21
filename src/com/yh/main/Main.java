package com.yh.main;

import java.util.List;
import java.util.Scanner;

import com.yh.domian.Inode;
import com.yh.service.BitmapService;
import com.yh.service.InodeService;

/**
 * �������
 * @author hao
 *
 */
public class Main {

	public static void main(String[] args) {
		//ȫ�ֱ���
		Inode Gfile;

		System.out.println("��ӭʹ��EXT2�ļ�ϵͳ!");
		//��ʼ��bitmap
		int[][] blockBitmap = BitmapService.InitBlockBitmap();
		int[][] inodeBitmap = BitmapService.InitInodeBitmap();
		//��ʼ��inode��
		List<Inode> inodes = InodeService.initInodeTable();
		//����Ŀ¼���ó�ȫ�ֱ���
		Gfile = inodes.get(0);
		//��ǰĿ¼
		List<Inode> dir = InodeService.initCurrentDir(inodes,Gfile);
		
		StringBuffer sb = new StringBuffer();
		sb.append("localhost@yh$"+"\\:");
		//������
		while(true){
			System.out.print(sb.toString());
			//���������
			String inputStr = new Scanner(System.in).nextLine();
			String firstStr = inputStr.split(" ")[0];
			//ѡ�����
			switch (firstStr) {
			case "cd":
				if(inputStr.split(" ").length != 2){
					System.out.println("error�������ʽ����");
				}else{
					if(!InodeService.isDir(inputStr.split(" ")[1] , inodes)){
						System.out.println("error:Ŀ¼������.");
						continue;
					}else{
						//cd
						Gfile = InodeService.cd(inputStr.split(" ")[1] , inodes, sb);
					}
				}
				break;
				
			case "cd..":
				if(inputStr.split(" ").length != 1){
					System.out.println("error�������ʽ����");
				}else{
					//cd..
					Gfile = InodeService.cdBack(Gfile,inodes,sb);
				}
				break;

			case "ls":
				if(inputStr.split(" ").length == 2){
					InodeService.showInodeTable(inodes);
				}else{
					//��ʾ��ǰĿ¼���ļ���Ϣ
					InodeService.lsCrrentDir(dir,inodes,Gfile);
					InodeService.showDir(dir);
				}
				
				break;
				
			case "touch":
				if(inputStr.split(" ").length != 3){
					System.out.println("error�������ʽ����");
				}else{
					if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
						System.out.println("error:�ļ����Ѵ���.");
						continue;
					}
					inodes = InodeService.touching(inputStr.split(" ")[1],inputStr.split(" ")[2], inodes , Gfile , inodeBitmap , blockBitmap);
					System.out.println("�����ɹ���");
				}
				break;
				
			case "rm":
				if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
					InodeService.rmFile(inputStr.split(" ")[1] , inodes , Gfile , inodeBitmap , blockBitmap);
				}else{
					System.out.println("���ļ�������");
				}     
				break;
			
			case "mkdir":
				if(inputStr.split(" ").length != 2){
					System.out.println("error�������ʽ����");
				}else{
					if(InodeService.hasThisFile(inputStr.split(" ")[1] , inodes)){
						System.out.println("error���ļ����Ѵ���.");
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
					System.out.println("��Ŀ¼������");
				}
				break;
				
			case "show":
				if(inputStr.split(" ")[1].equals("blockBitmap")){
					//��ʾ���λ��ͼ
					BitmapService.ShowBlockBitmap(blockBitmap);
				}else if(inputStr.split(" ")[1].equals("inodeBitmap")){
					//��ʾinode��λ��ͼ
					BitmapService.ShowIdoneBitmap(inodeBitmap);
				}else{
					System.out.println("error�������ʽ����");
				}
				break;
			
			
			}
		}
	}
}

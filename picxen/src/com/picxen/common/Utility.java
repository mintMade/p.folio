package com.picxen.common;

import java.io.File;

public class Utility {
	public static final String UPLOAD_PATH
	="G:\\JSP\\javaHyundai\\eclips\\PN2\\picxen\\WebContent\\pt_upload";
	//String upPath="pt_upload";
	
	/*="/usr/share/tomcat8/webapps/picxen/pt_upload";*/ //EC2
	
	public static final String PT_IMG_PATH
	/*="G:\\JSP\\javaHyundai\\eclips\\PN\\picxen\\WebContent\\pt_images";*/
	="G:\\JSP\\javaHyundai\\eclips\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\pt_images";
	
	/*="/usr/share/tomcat8/webapps/picxen/pt_images";*/ //EC2
	
	public static final String MY_ICON
	/*="G:\\JSP\\javaHyundai\\eclips\\PN\\picxen\\WebContent\\my_icon";*/
	="G:\\JSP\\javaHyundai\\eclips\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\my_icon";
	
	/*="/usr/share/tomcat8/webapps/picxen/my_icon";*/ //EC2
	
	public static final String MY_BG
	="G:\\JSP\\javaHyundai\\eclips\\PN2\\picxen\\WebContent\\my_bg";
	
	/*="/usr/share/tomcat8/webapps/picxen/my_bg";*/ //EC2
	
	public static String getUniqueFileName(String uploadPath, String fileName){
		//���� �̸��� ��� �Ϸù�ȣ�� �ٿ��� �̸��� �������ִ� �޼���
		//test.txt => test_1.txt => test_2.txt =>test_3.txt
		//Ȯ���ڸ� ������ �̸��� ���� test
		int idx = fileName.lastIndexOf(".");
		String fName = fileName.substring(0, idx);//0=test, 1=est
		System.out.println("lastIndexOf. fName="+fName);
				
		//.Ȯ���� ����=>.txt
		String ext = fileName.substring(idx);//.txt
		System.out.println("fName="+fName+",ext="+ext);
		
		//���ε� ������ �ش� ������ �����ϴ��� üũ , ����� ��?
		boolean bExist=true;
		int count=0;
		while(bExist){
			File myfile=new File(uploadPath, fileName);
			if(myfile.exists()){
				count++;
				fileName=fName+"_"+count+ext;//test_1.txt
				System.out.println("����� ���ϸ�:"+fileName);
				
				//���� �����
				/*if(myfile.delete()){
					count--;
					fileName=fName+"_"+count+ext;
					System.out.println("���� ���ϸ�:"+fileName);
				}else{
					System.out.println("����� ����");
				}*/

			}else{
				bExist=false;
			}
	}//while
		return fileName;
		
		
		
	}//getUniqueFileName
}/////
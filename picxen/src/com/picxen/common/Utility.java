package com.picxen.common;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Utility {
	public static final String UPLOAD_PATH
	="G:\\project\\PN2\\picxen\\WebContent\\pt_upload";
	//String upPath="pt_upload";
	
	/*="/usr/share/tomcat8/webapps/picxen/pt_upload";*/ //EC2
	
	public static final String PT_IMG_PATH
	/*="G:\project\PN2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\picxen\pt_images*/
	="G:\\project\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\pt_images";
	
	/*="/usr/share/tomcat8/webapps/picxen/pt_images";*/ //EC2
	
	public static final String MY_ICON
	/*="G:\\JSP\\javaHyundai\\eclips\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\my_icon";*/
	="G:\\project\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\my_icon";
	
	/*="/usr/share/tomcat8/webapps/picxen/my_icon";*/ //EC2
	
	public static final String MY_BG
	="G:\\project\\PN2\\picxen\\WebContent\\my_bg";
	
	/*="/usr/share/tomcat8/webapps/picxen/my_bg";*/ //EC2
	
	public static final String JUNK_FILES_PATH
	="G:\\project\\PN2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picxen\\junk_Files";
	
	public static String getUniqueFileName(String uploadPath, String fileName, String uid){
		
		//���� �̸��� ��� �Ϸù�ȣ�� �ٿ��� �̸��� �������ִ� �޼���
		//test.txt => test_1.txt => test_2.txt =>test_3.txt
		//Ȯ���ڸ� ������ �̸��� ���� test
		int idx = fileName.lastIndexOf(".");//29
		System.out.println("idx="+idx);
		String fName = fileName.substring(0, idx);//0=test, 1=est
		System.out.println("lastIndexOf. fName="+fName);
				
		//.Ȯ���� ����=>.txt
		String ext = fileName.substring(idx);//.txt �ڿ� string
		System.out.println("fName="+fName+",ext="+ext);
		
		//�����α� 
		/*ArrayList<String> logList = new ArrayList<String>();*/
		
		//originalFName: FilenameFilter��
		String oFName = fName;
		
		//���ε� ������ �ش� ������ ���� üũ
		boolean bExist=true;
		int count=0;
		while(bExist){
			File myfile=new File(uploadPath, fileName);
			if(myfile.exists()){
				count++;
				fileName=fName+"_"+uid+"_"+count+ext;//test_userid_1.jpg
				System.out.println("����� ���ϸ�:"+fileName);
				
				/*logList.add(fileName);
				System.out.println("log="+logList.size());*/
			}else{
				count++;
				fileName=fName+"_"+uid+"_"+count+ext;//test_userid_1.jpg
				bExist=false;
			}
			
	}//while
		
		//junkFile delete
		//���ε��� ����� exists�� �������ϹǷ� ���� ���ϸ� �Է�
		/*String delFileName = logList.get(logList.size() -2);*/
		
		//���� ������ ���丮
		File delFileUrl = new File(uploadPath);
		
		//���� ���� ���͸�
		String[] delList = delFileUrl.list(new FilenameFilter() {
			@Override
			public boolean accept(File delFileUrl, String name) {
				// TODO Auto-generated method stub
				String regex =  oFName;
				return name.matches(regex+".*");
			}
		} );
		
		System.out.println("delList.len="+delList.length);
		
		if(delList.length >= 1) {
			for(int i=0; i < delList.length; i++ ) {
				System.out.println("���� ���� �Ϸ�="+delList[i]);
				//logFile[i]
				File delFiles = new File(delFileUrl, delList[i]);
				delFiles.delete();
			}
		}
		
		System.out.println("fileName="+fileName);
		return fileName;
			
	}//getUniqueFileName

}/////public class Utility
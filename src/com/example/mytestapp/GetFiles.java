package com.example.mytestapp;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GetFiles {

	public ArrayList<String> GetFilesFromPath(File[] lstOfFiles){
		String fileText="";
	//	File folder=new File(path);
		ArrayList<String> lstOfAllFiles=new ArrayList<String>();
		
		for (int i = 0; i < lstOfFiles.length; i++) {
		      if (lstOfFiles[i].isFile()) {
		    	  
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	  lstOfAllFiles.add(fileText);
		        System.out.println("File " + lstOfFiles[i].getName());
		      } else if (lstOfFiles[i].isDirectory()) {
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	  lstOfAllFiles.add(fileText);
		    	 lstOfAllFiles= GetSubFiles(lstOfFiles[i],lstOfAllFiles);
		        System.out.println("Directory " + lstOfFiles[i].getName());
		      }
		    }
		return lstOfAllFiles;
		
	}
public ArrayList<String> GetSubFiles(File subFile,ArrayList<String> lstOfFileExist){
	ArrayList<String> lstOfAllFiles=new ArrayList<String>();
	lstOfAllFiles=lstOfFileExist;
	File[] lstOfFiles=subFile.listFiles();
	String fileText="";
	for (int i = 0; i < lstOfFiles.length; i++) {
	      if (lstOfFiles[i].isFile()) {
	    	 
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	  lstOfAllFiles.add(fileText);
	        System.out.println("File " + lstOfFiles[i].getName());
	      } else if (lstOfFiles[i].isDirectory()) {
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	  lstOfAllFiles.add(fileText);
	      }
}
	return lstOfAllFiles;
	}

public String GetConvertedFormatDate(long val){
	Date date=new Date(val);
	  SimpleDateFormat smpDateFor=new SimpleDateFormat("dd/MM/yy");
	String strDateFormat=smpDateFor.format(date);
	return strDateFormat;
}
}
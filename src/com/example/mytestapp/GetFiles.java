package com.example.mytestapp;

import java.io.File;
import java.util.ArrayList;

public class GetFiles {

	public ArrayList<String> GetFilesFromPath(File folder){
		String fileText="";
	//	File folder=new File(path);
		ArrayList<String> lstOfAllFiles=new ArrayList<String>();
		File[] lstOfFiles=folder.listFiles();
		for (int i = 0; i < lstOfFiles.length; i++) {
		      if (lstOfFiles[i].isFile()) {
		    	 
		    	  fileText=lstOfFiles[i].getName() + " : "+lstOfFiles[i].lastModified();
		    	  lstOfAllFiles.add(fileText);
		        System.out.println("File " + lstOfFiles[i].getName());
		      } else if (lstOfFiles[i].isDirectory()) {
		    	  fileText=lstOfFiles[i].getName() + " : "+lstOfFiles[i].lastModified();
		    	  lstOfAllFiles.add(fileText);
		    	  GetFilesFromPath(lstOfFiles[i]);//getting files from directory
		        System.out.println("Directory " + lstOfFiles[i].getName());
		      }
		    }
		return lstOfAllFiles;
		
	}

}

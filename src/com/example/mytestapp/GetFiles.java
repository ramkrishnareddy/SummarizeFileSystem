package com.example.mytestapp;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

public class GetFiles {

	public List<FolderSys> GetFilesFromPath(File[] lstOfFiles,Context context){
		String fileText="";
	//	File folder=new File(path);
		ArrayList<String> lstOfAllFiles=new ArrayList<String>();
		List<FolderSys> liFolderSys =new ArrayList<FolderSys>();
		for (int i = 0; i < lstOfFiles.length; i++) {
		      if (lstOfFiles[i].isFile()) {
		    	  
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	 // lstOfAllFiles.add(fileText);
		    	  Uri uri = Uri.fromFile(new File(lstOfFiles[i].getPath()));
		    	  String fileName = lstOfFiles[i].getName();
		    	  String fullPath = lstOfFiles[i].getAbsolutePath();
		    	  //String subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
		    	  Drawable drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context);
		    	  liFolderSys.add(new FolderSys(fileText,drawableIcon ,fullPath/*,subFilesCount*/));
		        System.out.println("File " + lstOfFiles[i].getName());
		      } else if (lstOfFiles[i].isDirectory()) {
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	  //lstOfAllFiles.add(fileText);
		    	  Drawable drawableIcon =MyIcons.GetFolderIcon(context);
		    	  //String subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
		    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath()/*,subFilesCount*/));
		    	// lstOfAllFiles= GetSubFiles(lstOfFiles[i],lstOfAllFiles);*/
		        System.out.println("Directory " + lstOfFiles[i].getName());
		      }
		    }
		return liFolderSys;
		
	}
public List<FolderSys> GetSubFiles(File subFile,ArrayList<String> lstOfFileExist,Context context){
	ArrayList<String> lstOfAllFiles=new ArrayList<String>();
	lstOfAllFiles=lstOfFileExist;
	File[] lstOfFiles=subFile.listFiles();
	List<FolderSys> liFolderSys =new ArrayList<FolderSys>();
	String fileText="";
	if(lstOfFiles==null)
		return liFolderSys;
	for (int i = 0; i < lstOfFiles.length; i++) {
	      if (lstOfFiles[i].isFile()) {
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	  Uri uri = Uri.fromFile(new File(lstOfFiles[i].getPath()));
	    	  String fileName = lstOfFiles[i].getName();
	    	  String fullPath = lstOfFiles[i].getAbsolutePath();
	    	  //String subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
	    	  Drawable drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context);
	    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,fullPath/*,subFilesCount*/));
	        System.out.println("File " + lstOfFiles[i].getName());
	      } else if (lstOfFiles[i].isDirectory()) {
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	 // lstOfAllFiles.add(fileText);
	    	  Drawable drawableIcon =  MyIcons.GetFolderIcon(context);
	    	  //String subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
	    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath()/*,subFilesCount*/));
	      }
}
	return liFolderSys;
	}

public String GetConvertedFormatDate(long val){
	Date date=new Date(val);
	  SimpleDateFormat smpDateFor=new SimpleDateFormat("dd/MM/yy");
	String strDateFormat=smpDateFor.format(date);
	return strDateFormat;
}
}
package com.example.mytestapp;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.http.entity.FileEntity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

public class GetFiles {

	public static long fileOrFolderSize=0;
	public List<FolderSys> GetFilesFromPath(File[] lstOfFiles,Context context,boolean boolSDCard){
		String fileText="";
	//	File folder=new File(path);
		ArrayList<String> lstOfAllFiles=new ArrayList<String>();
		List<FolderSys> liFolderSys =new ArrayList<FolderSys>();
		
		for (int i = 0; i < lstOfFiles.length; i++) {
			if(!boolSDCard){
		      if (lstOfFiles[i].isFile()) {
		    	  
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	 // lstOfAllFiles.add(fileText);
		    	  Uri uri = Uri.fromFile(new File(lstOfFiles[i].getPath()));
		    	  String fileName = lstOfFiles[i].getName();
		    	  String fullPath = lstOfFiles[i].getAbsolutePath();
		    	  String fileSize=humanReadableByteCount(lstOfFiles[i].length(), false);
		    	  FolderSys objFolderSys=new FolderSys(fileText,fullPath,fileSize,fileOrFolderSize,lstOfFiles[i].lastModified());
		    	  Bitmap drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context,objFolderSys);
		    	  objFolderSys.setIconId(drawableIcon);
		    	  if(objFolderSys!=null)
		    	  liFolderSys.add(objFolderSys);
		        System.out.println("File " + lstOfFiles[i].getName());
		      } else if (lstOfFiles[i].isDirectory()) {
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	  //lstOfAllFiles.add(fileText);
		    	  
		    	  Bitmap drawableIcon =MyIcons.GetFolderIcon(context);
		    	  String subFilesCount ="Empty";
		    	  long fileSize=0;
		     	  if(lstOfFiles[i].listFiles()!=null && lstOfFiles[i].listFiles().length!=0){
			    	   subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
			    	  // humanReadableByteCount(folderSize(lstOfFiles[i]), false);
			    	   fileSize=FileUtils.sizeOfDirectory(lstOfFiles[i]);
			    	  }
			    	  else
			    		  fileOrFolderSize=0;
		     	  FolderSys objFolderSys=new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath(),subFilesCount,fileSize,lstOfFiles[i].lastModified());
		     	 if(objFolderSys!=null)
			    	  liFolderSys.add(objFolderSys);
		    	 
		    	  }
		    	// lstOfAllFiles= GetSubFiles(lstOfFiles[i],lstOfAllFiles);*/
		        System.out.println("Directory " + lstOfFiles[i].getName());
		      }
		 
  	  else{
  		  String name=lstOfFiles[i].getName();
	    	  if(lstOfFiles[i].getName().equals("storage"))
	    	  {
	    		  File[] lstOfSDCardFiles=lstOfFiles[i].listFiles();
	    		 for(int j=0;j<lstOfFiles[i].listFiles().length;j++){
	    			if((j==0 && lstOfFiles[i].listFiles().length==2) || j==2)    			{
	    				liFolderSys =new ArrayList<FolderSys>();
	    				liFolderSys=  GetSubFiles(lstOfSDCardFiles[j] ,new ArrayList<String>(), context);
	    			}
	    			// if()
	    		//  liFolderSys=  GetSubFiles( ,new ArrayList<String>(), context)
	    		 }
	    	  }
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
	    	  String fileSize=humanReadableByteCount(lstOfFiles[i].length(), false);
	    	  FolderSys objFolderSys=new FolderSys(fileText,fullPath,fileSize,fileOrFolderSize,lstOfFiles[i].lastModified());
	    	  Bitmap drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context,objFolderSys);
	    	  objFolderSys.setIconId(drawableIcon);
	    	  if(objFolderSys!=null)
	    	  liFolderSys.add(objFolderSys);
	        System.out.println("File " + lstOfFiles[i].getName());
	      } else if (lstOfFiles[i].isDirectory()) {
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	 // lstOfAllFiles.add(fileText);
	    	  Bitmap drawableIcon =  MyIcons.GetFolderIcon(context);
	    	  String subFilesCount ="Empty";
	    	  long fileSize=0;
	    	  if(lstOfFiles[i].listFiles()!=null && lstOfFiles[i].listFiles().length!=0){
	    	   subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
	    	  // humanReadableByteCount(folderSize(lstOfFiles[i]), false);
	    	   fileSize=FileUtils.sizeOfDirectory(lstOfFiles[i]);
	    	  }
	    	  else
	    		  fileOrFolderSize=0;
	    	  FolderSys objFolderSys=new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath(),subFilesCount,fileSize,lstOfFiles[i].lastModified());
	    	  if(objFolderSys!=null)
	    	  liFolderSys.add(objFolderSys);
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

public static String humanReadableByteCount(long bytes, boolean si) {
    int unit = si ? 1000 : 1024;
    if (bytes < unit)
    	
    	{
    	fileOrFolderSize=bytes;
    	return bytes + " B";
    	
    	}
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "");
    fileOrFolderSize= (long) ((long)bytes / Math.pow(unit, exp));
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
}
public static long folderSize(File directory) {
    long length = 0;
    for (File file : directory.listFiles()) {
        if (file.isFile())
            length += file.length();
        else
        {
        	if(file.listFiles()!=null)
            length += folderSize(file);
        }
    }
    return length;
}
}
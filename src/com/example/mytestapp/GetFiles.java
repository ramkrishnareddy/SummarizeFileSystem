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
		    	  String fileSize=humanReadableByteCount(lstOfFiles[i].length(), false);
		    	  Drawable drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context);
		    	  liFolderSys.add(new FolderSys(fileText,drawableIcon ,fullPath,fileSize));
		        System.out.println("File " + lstOfFiles[i].getName());
		      } else if (lstOfFiles[i].isDirectory()) {
		    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
		    	  //lstOfAllFiles.add(fileText);
		    	  Drawable drawableIcon =MyIcons.GetFolderIcon(context);
		    	  String subFilesCount ="Empty";
		    	  if(lstOfFiles[i].listFiles()!=null)
		    	   subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
		    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath(),subFilesCount));
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
	    	  String fileSize=humanReadableByteCount(lstOfFiles[i].length(), false);
	    	  Drawable drawableIcon =  MyIcons.GetFileIcon(fileName .substring(fileName.lastIndexOf(".")+1),fullPath,uri,context);
	    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,fullPath,fileSize));
	        System.out.println("File " + lstOfFiles[i].getName());
	      } else if (lstOfFiles[i].isDirectory()) {
	    	  fileText=lstOfFiles[i].getName() + " : "+GetConvertedFormatDate(lstOfFiles[i].lastModified());
	    	 // lstOfAllFiles.add(fileText);
	    	  Drawable drawableIcon =  MyIcons.GetFolderIcon(context);
	    	  String subFilesCount ="Empty";
	    	  if(lstOfFiles[i].listFiles()!=null)
	    	   subFilesCount =  lstOfFiles[i].listFiles().length + " Items";
	    	  liFolderSys.add(new FolderSys(fileText,drawableIcon,lstOfFiles[i].getAbsolutePath(),subFilesCount));
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
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "");
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
}
}
package com.example.mytestapp;

import java.util.concurrent.locks.Lock;

import com.example.mytestapp.MainActivity.MyFolderSysArrayAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.appcompat.R.string;
import android.util.Log;

public class FolderSys {
	private String text;
	private Bitmap iconId;
	private String fullPath;
	private String itemsCount;
	private long fileOrFolderSize;
	private long dateOfModified;
	private MyFolderSysArrayAdapter myFolderSysAdapter;
	
	public FolderSys(String text,String fullPath,String itemsCount,long fileOrFolderSize,long dateOfModified) {
		super();
		this.text = text;
		this.fullPath=fullPath;
		this.itemsCount = itemsCount;
		this.fileOrFolderSize=fileOrFolderSize;
		this.dateOfModified=dateOfModified;
	}
	
	public FolderSys(String text, Bitmap iconId,String fullPath,String itemsCount,long fileOrFolderSize,long dateOfModified) {
		super();
		this.text = text;
		this.iconId = iconId;
		this.fullPath=fullPath;
		this.itemsCount = itemsCount;
		this.fileOrFolderSize=fileOrFolderSize;
		this.dateOfModified=dateOfModified;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Bitmap getIconId() {
		return iconId;
	}
	
	public void setIconId(Bitmap iconId) {
		this.iconId = iconId;
	}
	public String getFullPath(){
		return fullPath;
	}
	public void setFullPath(String fullPath){
		this.fullPath=fullPath;
	}
	public String getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(String itemsCount) {
		this.itemsCount= itemsCount;
	}
	public long getFileSize() {
		return fileOrFolderSize;
	}
	public void setFileOrFolderSize(long fileOrFoldderSize) {
		this.fileOrFolderSize= fileOrFoldderSize;
	}public long getDateOfModified() {
		return dateOfModified;
	}
	public void setDateOfModified(long dateOfModified) {
		this.dateOfModified= dateOfModified;
	}
	
	public void loadImage(MyFolderSysArrayAdapter myFolderSysAdapter) {
        // HOLD A REFERENCE TO THE ADAPTER
		 
        this.myFolderSysAdapter = myFolderSysAdapter;
        //iconId = Drawable.createFromPath(fullPath);
		  myFolderSysAdapter.notifyDataSetChanged();
		  //new ImageLoadTask().execute(fullPath);
    }
	
	
	public  class ImageLoadTask extends AsyncTask<String, String,Void> {
		 @Override
	     //protected void onPreExecute() {
	       //  Log.i("ImageLoadTask", "Loading image...");
	     //}

	     // PARAM[0] IS IMG URL
	     protected Void doInBackground(String... param) {
	    	 
	    	 //Log.i("ImageLoadTask", "Attempting to load image URL: " + param[0]);
	         
	         try {
	        	 if(param[0]!=null){
				 //iconId = Drawable.createFromPath(param[0]);
				  myFolderSysAdapter.notifyDataSetChanged();
	        	 }
	         } catch (OutOfMemoryError e) {
	             e.printStackTrace();
      	         //    return myDrawable;
	         }
	         //System.gc();
	         return null;
	     }

	    // protected void onProgressUpdate(String... progress) {
	         // NO OP
	    // }

	     /*
	     protected void onPostExecute(Drawable ret) {
	         if (ret != null) {
	             Log.i("ImageLoadTask", "Successfully loaded " + text + " image");
	             iconId = ret;
	                 // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
	           //  myFolderSysAdapter.notifyDataSetChanged();
	             
	           
	         } else {
	             Log.e("ImageLoadTask", "Failed to load " + text + " image");
	         }
	         System.gc();
	     }*/

	}
}

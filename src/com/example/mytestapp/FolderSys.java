package com.example.mytestapp;

import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.string;

public class FolderSys {
	private String text;
	private Drawable iconId;
	private String fullPath;
	private String itemsCount;
	private long fileOrFolderSize;
	private long dateOfModified;
	
	
	public FolderSys(String text, Drawable iconId,String fullPath,String itemsCount,long fileOrFolderSize,long dateOfModified) {
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
	public Drawable getIconId() {
		return iconId;
	}
	
	public void setIconId(Drawable iconId) {
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
}

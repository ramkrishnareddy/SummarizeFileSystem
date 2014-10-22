package com.example.mytestapp;

import android.graphics.drawable.Drawable;

public class FolderSys {
	private String text;
	private Drawable iconId;
	private String fullPath;
	
	
	public FolderSys(String text, Drawable iconId,String fullPath) {
		super();
		this.text = text;
		this.iconId = iconId;
		this.fullPath=fullPath;
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

}

package com.example.mytestapp;

public class FolderSys {
	private String text;
	private int iconId;
	private String fullPath;
	
	
	public FolderSys(String text, int iconId,String fullPath) {
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
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	public String getFullPath(){
		return fullPath;
	}
	public void setFullPath(String fullPath){
		this.fullPath=fullPath;
	}

}

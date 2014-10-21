package com.example.mytestapp;

public class FolderSys {
	private String text;
	private int iconId;
	
	
	public FolderSys(String text, int iconId) {
		super();
		this.text = text;
		this.iconId = iconId;
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

}

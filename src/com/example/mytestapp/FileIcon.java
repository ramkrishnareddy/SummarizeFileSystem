package com.example.mytestapp;

import java.util.List;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.content.pm.PackageManager;;

 class MyIcons {
	
 public static Drawable GetFileIcon(String fileType,Uri fileUri,Context context)
 {
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon); 
	
	     if(fileType.equals("doc"))
		 myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon);
	     else  if(fileType.equals("exe"))    
		 myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon);
	     else
	     myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon);   
	
	 return myDrawable;
 }
 public static Drawable GetFolderIcon(Context context)
 {
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.android_foldericon72crosss72);
	 return myDrawable;
 }
 
 public static Drawable GetFileIconFromPackage(String fileType,Uri fileUri,Context context)
 {
	 
	 //return R.drawable.ic_launcher;
	 final Intent intent = new Intent(Intent.ACTION_VIEW);
	 intent.setData(fileUri);
	 intent.setType(fileType);

	 final List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon);
	 for (ResolveInfo match : matches) {
		 myDrawable = match.loadIcon(context.getPackageManager());
	
	 }
	 return myDrawable;
	 
 }
 
 
}

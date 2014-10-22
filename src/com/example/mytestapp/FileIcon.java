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
	
 public static Drawable GetFileIcon(Uri fileUri,Context context)
 {
	 
	 //return R.drawable.ic_launcher;
	 final Intent intent = new Intent(Intent.ACTION_VIEW);
	 intent.setData(fileUri);
	 intent.setType("image/png");

	 final List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.ic_launcher);
	 for (ResolveInfo match : matches) {
		 myDrawable = match.loadIcon(context.getPackageManager());
	 }
	 return myDrawable;
	 
	 
 }
 public static Drawable GetFolderIcon(Context context)
 {
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.ic_launcher);
	 return myDrawable;
 }
 
 
}

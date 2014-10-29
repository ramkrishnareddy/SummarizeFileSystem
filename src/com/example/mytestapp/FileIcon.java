package com.example.mytestapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.mytestapp.R.drawable;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.content.pm.PackageManager;;

 class MyIcons {
	
	public static Bitmap GetFileIcon(String fileType,String fullPath,Uri fileUri,Context context,FolderSys objFolderSys)
 {
	      List<String> lstFileTypes =Arrays.asList("jpeg","aac","ai","aiff","android_foldericon","android_foldericon72crosss72","asp","avi","bmp","c","cpp","css","dat","dmg","doc","docx","dot","dotx","dwg","dxf","eps","exe","flv","gif","h","html","ics","ic_launcher","iso","java","jpg","key","m4v","mid","mov","mp3","mp4","mpg","odp","ods","odt","otp","ots","ott","pdf","php","png","pps","ppt","psd","py","qt","rar","rb","rtf","sql","tga","tgz","tiff","txt","unknown_icon","wav","xls","xlsx","xml","yml","zip");
	 
	     Bitmap  myDrawable = HandleBitmaps.decodeSampledBitmapFromResource(context.getResources(),R.drawable.unknown_icon, 20, 20);

	     if(lstFileTypes.contains(fileType))
	     {
	    	 //LazyLoad Images Later
	    	 //if(fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg"))
	    	 //{
	    		 //For Sampled Image
	    		 //myDrawable = HandleBitmaps.decodeSampledBitmapFromFile(fullPath, 50, 50);
	    		 
	    		 //myDrawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.jpg);
	    		 //myDrawable = context.getResources().getDrawable(R.drawable.jpg); ; 
	    	    //ImageLoadTask objImageLoadTask=objFolderSys.new ImageLoadTask(); 
	    		//objImageLoadTask.execute(fullPath);
	    		 
	    		// myDrawable = Drawable.createFromPath(fullPath);
	    	 //}
	    	 //else
	    	 //{
	    	 String uri = "@drawable/"+fileType;
	    	 int imageResourceId =context.getResources().getIdentifier(uri, null,context.getPackageName());
		     //myDrawable = context.getResources().getDrawable(imageResource);
	    	 myDrawable = HandleBitmaps.decodeSampledBitmapFromResource(context.getResources(),imageResourceId, 20, 20);
	    	 //}
	     }	
	 return myDrawable;
 }
 
 
 
 public static Bitmap GetFolderIcon(Context context)
 {
	 return HandleBitmaps.decodeSampledBitmapFromResource(context.getResources(),R.drawable.android_foldericon72crosss72, 20, 20);
 }
 
 /*
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
 }*/
 
 
}

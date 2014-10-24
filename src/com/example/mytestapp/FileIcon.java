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
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.content.pm.PackageManager;;

 class MyIcons {
	
 public static Drawable GetFileIcon(String fileType,String fullPath,Uri fileUri,Context context)
 {
	 List<String> lstFileTypes =Arrays.asList("jpeg","aac","ai","aiff","android_foldericon","android_foldericon72crosss72","asp","avi","bmp","c","cpp","css","dat","dmg","doc","docx","dot","dotx","dwg","dxf","eps","exe","flv","gif","h","html","ics","ic_launcher","iso","java","jpg","key","m4v","mid","mov","mp3","mp4","mpg","odp","ods","odt","otp","ots","ott","pdf","php","png","pps","ppt","psd","py","qt","rar","rb","rtf","sql","tga","tgz","tiff","txt","unknown_icon","wav","xls","xlsx","xml","yml","zip");
	 Drawable myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon); 

	     if(lstFileTypes.contains(fileType))
	     {
	    	 if(fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg"))
	    	 {
	    		 myDrawable = Drawable.createFromPath(fullPath);
	    		 //Bitmap btmpa=convertToBitmap(myDrawable, 10, 10);
	    		// myDrawable= (Drawable)new BitmapDrawable(btmpa);
	    		// Bitmap mutableBitmap = Bitmap.createBitmap(5, 5, Bitmap.Config.ARGB_8888);
	    		  //  Canvas canvas = new Canvas(mutableBitmap);
	    		// myDrawable.setBounds(0, 0,5 ,5);	
	    		// myDrawable.draw(canvas);
	    		 
	    		 
	    	 }
	    	 else
	    	 {
	    	 String uri = "@drawable/"+fileType;
	    	 int imageResource =context.getResources().getIdentifier(uri, null,context.getPackageName());
		     myDrawable = context.getResources().getDrawable(imageResource);
	    	 }
	     }
	     else
	     myDrawable = context.getResources().getDrawable(R.drawable.unknown_icon);   
	
	 return myDrawable;
 }
 
 
 public static Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
	    Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(mutableBitmap);
	    drawable.setBounds(0, 0, widthPixels, heightPixels);
	    drawable.draw(canvas);

	    return mutableBitmap;
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

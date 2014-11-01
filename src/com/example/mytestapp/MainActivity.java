package com.example.mytestapp;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import android.support.v4.widget.SearchViewCompatIcs.MySearchView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.appcompat.R.bool;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemSelectedListener;
import android.support.v7.widget.LinearLayoutCompat.OrientationMode;
import android.content.Context;
import android.location.GpsStatus.Listener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private List<FolderSys> liFolderSys = new ArrayList<FolderSys>();
	String[] sortType = { "Name", "Size", "LastModified" };
	String[] arrayImageTypes = {".jpg",".png",".jpeg"};

	ListView lvFileSystem;
	MyFolderSysArrayAdapter myFolderSysArrayAdapter;
	String selectedStorageSpinnerText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final GetFiles objGetFiles = new GetFiles();

		// liFolderSys=objGetFiles.GetFilesFromPath(lstOfFiles,this.getApplicationContext());

		final Spinner spinnerStorage = PopulateSpinner();
		selectedStorageSpinnerText = spinnerStorage.getSelectedItem()
				.toString();
		String absPath = GetAbsPath(selectedStorageSpinnerText);
		final File[] lstOfFiles = GetAllFilesDependSpinner(objGetFiles,
				absPath, false);
		myFolderSysArrayAdapter = new MyFolderSysArrayAdapter();
		BindAdapterToListview();
		registerThisCallBack(this.getApplicationContext());
		addOnSelectClickListenerForStorageSpinner(objGetFiles);
		addOnSelectClickListenerForSortSpinner(objGetFiles);
		addListenerOnGridButton();
		/*
		liFolderSys.get(0).setMyFolderSysArrayAdapter(myFolderSysArrayAdapter);
		liFolderSys.get(0).lazyloadImage();
		liFolderSys.get(1).setMyFolderSysArrayAdapter(myFolderSysArrayAdapter);
		liFolderSys.get(1).lazyloadImage();
		liFolderSys.get(2).setMyFolderSysArrayAdapter(myFolderSysArrayAdapter);
		liFolderSys.get(2).lazyloadImage();*/
		BitmapWorkerTask task = new BitmapWorkerTask();

		task.execute();
		

	}

	public class MyFolderSysArrayAdapter extends ArrayAdapter<FolderSys> {
		public MyFolderSysArrayAdapter() {
			super(MainActivity.this, R.layout.inner_activity_main, liFolderSys);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(
						R.layout.inner_activity_main, parent, false);
			}
			//TODO Sagar: Multi Bindings 
        if(liFolderSys.size()>position)
        {
			FolderSys currentFSys = liFolderSys.get(position);
			try
			{
			ImageView imgView = (ImageView) itemView
					.findViewById(R.id.imageView1);
			imgView.setImageBitmap(currentFSys.getIconId());
			TextView txtView = (TextView) itemView.findViewById(R.id.textView1);
			txtView.setText(currentFSys.getText());
			TextView txtViewPath = (TextView) itemView
					.findViewById(R.id.textView2);
			txtViewPath.setText(currentFSys.getFullPath());
			TextView txtViewItemsCount = (TextView) itemView
					.findViewById(R.id.textView3);
			txtViewItemsCount.setText(currentFSys.getItemsCount());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
        }
			return itemView;

		}

	}

	public MyFolderSysArrayAdapter BindAdapterToListview() {

		ListView lvFileSystem = (ListView) findViewById(R.id.listview);
		lvFileSystem.setAdapter(myFolderSysArrayAdapter);
		return myFolderSysArrayAdapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidMa nifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void registerThisCallBack(final Context context) {
		ListView lvFileSystem = (ListView) findViewById(R.id.listview);
		lvFileSystem
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						TextView txtViewFullPath = (TextView) view
								.findViewById(R.id.textView2);
						TextView txtView = (TextView) view
								.findViewById(R.id.textView1);
						File fileSysFiles = new File(txtViewFullPath.getText()
								.toString());
						GetFiles objGetFiles = new GetFiles();
						// uncomment this line
						ArrayList<String> lstFiles = new ArrayList<String>();
						liFolderSys = objGetFiles.GetSubFiles(fileSysFiles,
								lstFiles, context);
						if (!liFolderSys.isEmpty()) {
							SortFolderSys("");
							BindAdapterToListview();
						} else {
							// String
							// fileName=txtView.getText().toString().substring(0,
							// txtView.getText().toString().lastIndexOf(":"));
							File myFile = new File(txtViewFullPath.getText()
									.toString());
							try {
								FileOpen.openFile(context, myFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// Toast.makeText(MainActivity.this,
						// "Clicked at positon = " + 0,
						// Toast.LENGTH_SHORT).show();
					}

				});
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ListView myList = (ListView) findViewById(R.id.listview);
			View listItem = (View) myList.getChildAt(0);
			TextView txtViewFullPath = (TextView) listItem
					.findViewById(R.id.textView2);
			int endId = txtViewFullPath.getText().toString().lastIndexOf("/");
			String path = txtViewFullPath
					.getText()
					.toString()
					.substring(
							0,
							txtViewFullPath.getText().toString()
									.lastIndexOf("/"));
			if (endId == 0) {
				this.finish();
				return super.onKeyDown(keyCode, event);
			}
			path = path.substring(0, path.lastIndexOf("/"));
			if (path.length() == 0)
			{
				Spinner spinnerStorage=(Spinner) findViewById(R.id.storagetype_spinner);
				path = GetAbsPath(spinnerStorage.getSelectedItem().toString());
			}

			File fileSysFiles = new File(path);
			GetFiles objGetFiles = new GetFiles();
			// uncomment this line
			ArrayList<String> lstFiles = new ArrayList<String>();
			Context context = this.getApplicationContext();
			liFolderSys = objGetFiles.GetSubFiles(fileSysFiles, lstFiles,
					context);
			if (!liFolderSys.isEmpty()) {
				SortFolderSys("");
				BindAdapterToListview();
			}

		}

		return false;
	}

	public Spinner PopulateSpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.storagetype_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.storage_types,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		return spinner;
	}

	public File[] GetAllFilesDependSpinner(GetFiles objGetFiles,
			String absPath, boolean boolSDCard) {
		File[] lstOfFiles = new File(absPath).listFiles();
		liFolderSys = objGetFiles.GetFilesFromPath(lstOfFiles,
				this.getApplicationContext(), boolSDCard);
		SortFolderSys("");
		return lstOfFiles;
	}

	public String GetAbsPath(String selectedSpinnerText) {
		String absPath = "";
		if (selectedSpinnerText.equals("root")) {
			absPath = Environment.getRootDirectory().getParent();
		} else if (selectedSpinnerText.equals("InternalStorage")) {
			absPath = Environment.getExternalStorageDirectory().getPath();
		} else {
            //TODO Sagar: Give ExternalStorage 
			absPath = Environment.getRootDirectory().getParent();
		}
		return absPath;
	}

	public void addListenerOnGridButton() {

		ImageButton ib = (ImageButton) findViewById(R.id.imgBtnGridSort);
		ib.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// liFolderSys.get(0).loadImage(myFolderSysArrayAdapter);
				// liFolderSys.get(1).loadImage(myFolderSysArrayAdapter);
				// liFolderSys.get(2).loadImage(myFolderSysArrayAdapter);
				// liFolderSys.get(3).loadImage(myFolderSysArrayAdapter);
				// for (FolderSys fSys : liFolderSys) {
				// if (fSys.getFullPath().endsWith(".jpg")) {
				// loadImage(myFolderSysArrayAdapter);

				// }
				// }
				/*
				for (int i = 0; i < liFolderSys.size(); i++) {
					//TODO : Sagar Proper Way of Finding Extension
					liFolderSys.get(i).setMyFolderSysArrayAdapter(myFolderSysArrayAdapter);
					String fullPath = liFolderSys.get(i).getFullPath();
					if(fullPath.endsWith("png") || fullPath.endsWith("jpg") || fullPath.endsWith("jpeg"))
						liFolderSys.get(i).lazyloadImage();
				}*/
				liFolderSys.get(0).setMyFolderSysArrayAdapter(myFolderSysArrayAdapter);
				liFolderSys.get(0).lazyloadImage();
			}
		});
	}

	public void addOnSelectClickListenerForStorageSpinner(
			final GetFiles objGetFiles) {
		final Spinner spinnerStorage = (Spinner) findViewById(R.id.storagetype_spinner);
		spinnerStorage
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView,
							View view, int i, long l) {
						// Your code here
						String absPath = "";
						boolean boolsdCard = false;
						String currentSelectedSpinnerText = spinnerStorage
								.getSelectedItem().toString();
						if(selectedStorageSpinnerText != currentSelectedSpinnerText)
						{
							selectedStorageSpinnerText = currentSelectedSpinnerText;
						if (selectedStorageSpinnerText.equals("SDCard")) {
							// if(isExternalStorageReadable())
							// BindAdapterToListview();
							boolsdCard = true;
						}
						absPath = GetAbsPath(selectedStorageSpinnerText);
						GetAllFilesDependSpinner(objGetFiles, absPath,
								boolsdCard);
						BindAdapterToListview();
						}

					}

					public void onNothingSelected(AdapterView<?> adapterView) {
						return;
					}
				});
	}

	public void addOnSelectClickListenerForSortSpinner(
			final GetFiles objGetFiles) {
		final Spinner spinnerSortType = (Spinner) findViewById(R.id.spinner_SortType);
		spinnerSortType
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView,
							View view, int i, long l) {
						String selectedSpinnerText = spinnerSortType
								.getSelectedItem().toString();
						SortFolderSys(selectedSpinnerText);
						BindAdapterToListview();
					}

					public void onNothingSelected(AdapterView<?> adapterView) {
						return;
					}
				});
	}

	public void SortFolderSys(String sortType) {
		Spinner spinnerSortType = (Spinner) findViewById(R.id.spinner_SortType);
		sortType = spinnerSortType.getSelectedItem().toString();
		if (sortType.equals("Size")) {
			Collections.sort(liFolderSys, new Comparator<FolderSys>() {
				@Override
				public int compare(final FolderSys object1,
						final FolderSys object2) {
					return Long.valueOf(object1.getFileSize()).compareTo(
							object2.getFileSize());
				}
			});
		} else if (sortType.equals("LastModified")) {
			Collections.sort(liFolderSys, new Comparator<FolderSys>() {
				@Override
				public int compare(final FolderSys object1,
						final FolderSys object2) {
					return Long.valueOf(object1.getDateOfModified()).compareTo(
							object2.getDateOfModified());
				}
			});
		} else {
			Collections.sort(liFolderSys, new Comparator<FolderSys>() {
				@Override
				public int compare(final FolderSys object1,
						final FolderSys object2) {
					return object1.getText().compareTo(object2.getText());
				}
			});
		}
	}
	
	public  class BitmapWorkerTask extends AsyncTask<Void,Void,Void> {
		 @Override
	     protected void onPreExecute() {
	         //Log.i("ImageLoadTask", "Loading image...");
	     }

	     // PARAM[0] IS IMG URL
	     protected Void doInBackground(Void... param) {
	    	      for(int i=0;i<liFolderSys.size();i++)
	    	      {
	    	    	  if(liFolderSys.get(i).getFullPath().contains(".jpg"))
	    	    	  {
	    	    		 
	    	    	  liFolderSys.get(i).setIconId(HandleBitmaps.decodeSampledBitmapFromFile(liFolderSys.get(i).getFullPath(), 50, 50));
	    	    	   
	    	    	//  if(i==5)
	    	    		//  break;
	    	    	  }
					  
	    	      }
	    	  //    myFolderSysArrayAdapter.notifyDataSetChanged();
	    	     
	    	    	  
				  return null;
	     }

	    // protected void onProgressUpdate(String... progress) {
	         // NO OP
	    // }

	     
	     protected void onPostExecute() {
	        //if (ret != null) {
	        //     Log.i("ImageLoadTask", "Successfully loaded " + text + " image");
	       //      iconId = ret;
	                 // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
	            
	    	
	           
	         //} 
	             //else {
	       //    Log.e("ImageLoadTask", "Failed to load " + text + " image");
	        // }
	       //  System.gc();
	     }

	}

	
	
	

}

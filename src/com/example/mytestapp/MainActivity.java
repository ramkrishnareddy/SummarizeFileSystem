package com.example.mytestapp;

import java.io.Externalizable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import android.support.v4.widget.SearchViewCompatIcs.MySearchView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutCompat.OrientationMode;
import android.content.Context;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	String[] days = {"sun","mon","tues","wed","thus","fri"};
	private List<FolderSys> liFolderSys =new ArrayList<FolderSys>();
	
	ListView lvFileSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<String>();
        
        
        for (int i = 0; i < days.length; ++i) {
        	list.add(days[i]);
        }
        
       /* for (int i = 0; i < days.length; ++i) {
        	liFolderSys.add(new FolderSys(days[i], R.drawable.ic_launcher));
        }*/
        
        
        GetFiles objGetFiles=new GetFiles();
        
        File[] lstOfFiles=Environment.getExternalStorageDirectory().listFiles();
       // liFolderSys=objGetFiles.GetFilesFromPath(lstOfFiles,this.getApplicationContext());
        
         lstOfFiles=new File(Environment.getRootDirectory().getParent()).listFiles();
          liFolderSys=objGetFiles.GetFilesFromPath(lstOfFiles,this.getApplicationContext());
          Collections.sort(liFolderSys, new Comparator<FolderSys>() {
              @Override
              public int compare(final FolderSys object1, final FolderSys object2) {
                  return object1.getText().compareTo(object2.getText());
              }
             } );
     /*ListView  lvFileSystem = (ListView)findViewById(R.id.listview);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    		 android.R.layout.simple_list_item_1, list);
       lvFileSystem.setAdapter(adapter);*/
        
        // ArrayAdapter<FolderSys> adapter = new MyFolderSysArrayAdapter();
     PopulateListView();
     registerThisCallBack(this.getApplicationContext());
        
    }
    public class MyFolderSysArrayAdapter extends ArrayAdapter<FolderSys> {
    	public MyFolderSysArrayAdapter() {
    		super(MainActivity.this,R.layout.inner_activity_main,liFolderSys);
    	}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView  =  convertView;
			if(itemView   == null)
			{
				itemView  = getLayoutInflater().inflate(R.layout.inner_activity_main, parent,false);
			}
			FolderSys currentFSys  =  liFolderSys.get(position);
			ImageView imgView = (ImageView)itemView.findViewById(R.id.imageView1);
			imgView.setImageDrawable(currentFSys.getIconId());
			
			TextView txtView = (TextView)itemView.findViewById(R.id.textView1);
			txtView.setText(currentFSys.getText());
			TextView txtViewPath=(TextView)itemView.findViewById(R.id.textView2);
			txtViewPath.setText(currentFSys.getFullPath());
			return itemView;
			
			}
    	
    }
    
    public void PopulateListView()
    {
    	  ArrayAdapter<FolderSys> adapter = new MyFolderSysArrayAdapter();
    	  ListView  lvFileSystem = (ListView)findViewById(R.id.listview);
    	  lvFileSystem.setAdapter(adapter);
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
        // as you specify a parent activity in AndroidMa	nifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void registerThisCallBack(final Context context){
    	ListView  lvFileSystem = (ListView)findViewById(R.id.listview);
    	lvFileSystem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View view,
    	              int position, long id) {
    			
    			TextView txtViewFullPath=(TextView) view.findViewById(R.id.textView2);
    			//TextView txtView=(TextView) view.findViewById(R.id.textView1);
    			File fileSysFiles=new File(txtViewFullPath.getText().toString());
    			GetFiles objGetFiles=new GetFiles();
    			//uncomment this line
    			ArrayList<String> lstFiles=new ArrayList<String>();
    			liFolderSys=objGetFiles.GetSubFiles(fileSysFiles,lstFiles,context);
    			if(!liFolderSys.isEmpty()){
    				Collections.sort(liFolderSys, new Comparator<FolderSys>() {
    		              @Override
    		              public int compare(final FolderSys object1, final FolderSys object2) {
    		                  return object1.getText().compareTo(object2.getText());
    		              }
    		             } );
    		    PopulateListView();
    			}
    			Toast.makeText(MainActivity.this, "Clicked at positon = " + 0, Toast.LENGTH_SHORT).show();
    		}
    		
		});
    }
    
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}

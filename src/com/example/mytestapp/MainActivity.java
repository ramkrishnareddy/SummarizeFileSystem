package com.example.mytestapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import android.support.v4.widget.SearchViewCompatIcs.MySearchView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutCompat.OrientationMode;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


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
        
        for (int i = 0; i < days.length; ++i) {
        	liFolderSys.add(new FolderSys(days[i], R.drawable.ic_launcher));
        }
        
        
        GetFiles objGetFiles=new GetFiles();
        File[] lstOfFiles=Environment.getExternalStorageDirectory().listFiles();
        list=objGetFiles.GetFilesFromPath(lstOfFiles);
        
       // ArrayAdapter<FolderSys> adapter = new MyFolderSysArrayAdapter();
     ListView  lvFileSystem = (ListView)findViewById(R.id.listview);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    		 android.R.layout.simple_list_item_1, list);
       lvFileSystem.setAdapter(adapter);
     
        
    }
  /*  public class MyFolderSysArrayAdapter extends ArrayAdapter<FolderSys> {
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
			ImageView imgView = (ImageView)itemView.findViewById(R.id.icon);
			imgView.setImageResource(currentFSys.getIconId());
			return itemView;
			
			}
    	
    }*/


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
}

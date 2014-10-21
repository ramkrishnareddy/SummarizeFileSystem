package com.example.mytestapp;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	String[] days = {"sun","mon","tues","wed","thus","fri"};
	ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < days.length; ++i) {
        	list.add(days[i]);
        }
        GetFiles objGetFiles=new GetFiles();
        list=objGetFiles.GetFilesFromPath(Environment.getExternalStorageDirectory());
       ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1,list); 
        
       
       lv1 = (ListView)findViewById(R.id.listview);
       lv1.setAdapter(adapter);
        
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
}

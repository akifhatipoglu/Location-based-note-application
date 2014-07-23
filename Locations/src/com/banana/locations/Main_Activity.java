package com.banana.locations;

import com.mkyong.android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main_Activity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
      //sharedPrefs.getBoolean("perform_updates", false);
        int yaricap=  Integer.parseInt(sharedPrefs.getString("yaricap","1500").toString());
       int yenileme =Integer.parseInt(sharedPrefs.getString("yenileme","20"));  
       
       Toast.makeText(Main_Activity.this, yaricap+" yaricaplı , yenilemesi "+yenileme, Toast.LENGTH_SHORT).show();
       //  sharedPrefs.getString("welcome_message", "NULL");
      //  
        
        
        ImageButton btn_shop=(ImageButton) findViewById(R.id.imageButton1);
        btn_shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				intent.putExtra("color","red");
				intent.putExtra("choose","shop");
				startActivity(intent);
			}
		});
        ImageButton btn_shop1=(ImageButton) findViewById(R.id.imageButton2);
        btn_shop1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);		
				intent.putExtra("color","blue");
				intent.putExtra("choose","shop1");
				startActivity(intent);
			}
		});
        ImageButton btn_atm=(ImageButton) findViewById(R.id.imageButton3);
        btn_atm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);
				intent.putExtra("color","yellow");
				intent.putExtra("choose","atm");
				startActivity(intent);
			}
		});
        ImageButton btn_book=(ImageButton) findViewById(R.id.imageButton4);
        btn_book.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);
				intent.putExtra("color","purple");
				intent.putExtra("choose","book");
				startActivity(intent);
			}
		});
        ImageButton btn_event=(ImageButton) findViewById(R.id.imageButton5);
        btn_event.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);	
				intent.putExtra("color","orange");
				intent.putExtra("choose","event");
				startActivity(intent);
			}
		});
        ImageButton btn_pharmacy=(ImageButton) findViewById(R.id.imageButton6);
        btn_pharmacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);
				intent.putExtra("color","green");
				intent.putExtra("choose","pharmacy");
				startActivity(intent);
			}
		});
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
     
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.menu_Hatirlatmalari_listele:
            Toast.makeText(Main_Activity.this, "Hatırlatmaları listele is Selected", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Main_Activity.this,List_Activity.class);
			startActivity(intent);
            return true;
 
        case R.id.menu_settings:
            Toast.makeText(Main_Activity.this, "Settings is Selected", Toast.LENGTH_SHORT).show();
            Intent intent2=new Intent(Main_Activity.this,Settings.class);
			startActivity(intent2);
            return true;
 
        case R.id.menu_exit:
            Toast.makeText(Main_Activity.this, "Exit is Selected", Toast.LENGTH_SHORT).show();
            Main_Activity.this.finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
    
    
    
    
    
    
    
    
    
}
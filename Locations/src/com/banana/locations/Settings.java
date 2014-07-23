package com.banana.locations;

import com.mkyong.android.R;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Settings extends PreferenceActivity{

	
	 @SuppressWarnings("deprecation")
	@Override
	    public void onCreate(Bundle savedInstanceState) {        
	        super.onCreate(savedInstanceState);        
	        addPreferencesFromResource(R.xml.settings);        
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        menu.add(Menu.NONE, 0, 0, "Kaydet");
	        return super.onCreateOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case 0:
	                startActivity(new Intent(this, Main_Activity.class));
	                return true;
	        }
	        return false;
	    }
	
}

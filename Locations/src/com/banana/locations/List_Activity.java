package com.banana.locations;

import java.util.ArrayList;
import java.util.List;

import com.banana.messagedatabase.RecordOperations;
import com.banana.messagedatabase.RecordText;
import com.mkyong.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class List_Activity extends Activity{
	public RecordOperations op;
	public ListView listview;
	private static final int ID_Duzenle=Menu.FIRST;
	private static final int ID_sil=Menu.FIRST+1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		 op = new RecordOperations(this);
		 op.open();
		
		final	List<String>	cities=getAll();
		ArrayAdapter<String> as=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
		listview= (ListView) findViewById(R.id.listView_list);
		listview.setAdapter(as);
		registerForContextMenu(listview);
		
		
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				int itemPosition     = position;
                String  itemValue    = (String) listview.getItemAtPosition(position);                         
          
				
			}
		
		});
		 
	}

	private List<String> getAll() {
		List<String>	cities=new ArrayList<String>();
		List<RecordText> a= op.getAllPuan();
        for (RecordText recordText : a) {
        	cities.add("Etiket: "+recordText.getReminderTicket()+" Not: "+recordText.getReminderTextRecord());
		}
		return cities;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		menu.add(Menu.NONE,ID_sil,0,"Sil");
		menu.add(Menu.NONE,ID_Duzenle,1,"Duzenle");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo	info=(AdapterContextMenuInfo)	item.getMenuInfo();
		if(item.getItemId()==ID_sil)
		{
			
			String blabla=listview.getItemAtPosition(info.position).toString().substring(listview.getItemAtPosition(info.position).toString().indexOf(" Not: ")+6,listview.getItemAtPosition(info.position).toString().length()).trim();
			op.deleteNotify(blabla);
			Intent intent=new Intent(List_Activity.this,List_Activity.class);	
			startActivity(intent);
			return true;
		}
		
		if(item.getItemId()==ID_Duzenle)
		{
			Toast.makeText(List_Activity.this, "duzenle is Selected"+listview.getItemAtPosition(info.position), Toast.LENGTH_SHORT).show();
			return true;
		}	
		return false;
		
	}
	
}

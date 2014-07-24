package com.banana.locations;

import java.util.List;
import com.banana.messagedatabase.RecordOperations;
import com.banana.messagedatabase.RecordText;
import com.banana.service.LocationService;
import com.mkyong.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Messagerecord  extends Activity{
	private RecordOperations op;
	private RecordText p;
	private  String choose;
	private EditText text;
	public static Intent intent2 ;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.messagerecord);
	        op = new RecordOperations(this);
	        String colorname = getIntent().getExtras().get("color").toString();
	        choose= getIntent().getExtras().get("choose").toString();
	        System.out.println(getIntent().getExtras().get("color").toString());
	        System.out.println(getIntent().getExtras().get("choose").toString());
	        Button buton_record = (Button) findViewById(R.id.button_record);
	        LinearLayout layout = (LinearLayout) findViewById(R.id.notebackground);
	         text = (EditText) findViewById(R.id.editText1);
	        //layout.setBackgroundColor(color)
	        if(colorname.equals("red")){
	        	layout.setBackgroundColor(Color.RED);
	        	buton_record.setBackgroundColor(Color.rgb(255,153,0));
	        }else if(colorname.equals("blue")){
	        	layout.setBackgroundColor(Color.BLUE);
	        	buton_record.setBackgroundColor(Color.rgb(51,153,255));
	        }else if(colorname.equals("yellow")){
	        	layout.setBackgroundColor(Color.YELLOW);
	        	buton_record.setBackgroundColor(Color.rgb(102,153,51));
	        }else if(colorname.equals("purple")){
	        	layout.setBackgroundColor(Color.rgb(185, 22, 250));
	        	buton_record.setBackgroundColor(Color.rgb(204,102,5));
	        }else if(colorname.equals("orange")){
	        	layout.setBackgroundColor(Color.rgb(253, 208, 23));
	        	buton_record.setBackgroundColor(Color.rgb(255,153,102));
	        	
	        }else if(colorname.equals("green")){
	        	layout.setBackgroundColor(Color.GREEN);
	        	buton_record.setBackgroundColor(Color.rgb(102,153,255));
	        }
	        
	        
	        buton_record.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					p= new RecordText(choose,text.getText().toString());	
					op.open();
					   	   op.addPuan(p.getReminderTicket(), p.getReminderTextRecord());
					   	   Toast.makeText(Messagerecord.this, "Hatırlatma Kaydedildi.", Toast.LENGTH_SHORT).show();
					   	   /*List<RecordText> a= op.getAllPuan();
					        for (RecordText recordText : a) {
					        	 Toast.makeText(Messagerecord.this, recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord()+"-"+recordText.getId(), Toast.LENGTH_SHORT).show();
					        	System.out.println(recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord());
							}
					      */ intent2 = new Intent(Messagerecord.this, LocationService.class); 
					        startService(intent2);
					        
					        //startService(intent);
					        //Toast.makeText(Messagerecord.this, "service started..", Toast.LENGTH_SHORT).show();
					        System.out.println("service started.");
					        
					        Intent intent=new Intent(Messagerecord.this,Main_Activity.class);
					        					        
					        Messagerecord.this.finish();
					        startActivity(intent);
						
				}
			});
	        
	       
	        }
}

package com.banana.locations;

import java.util.List;

import com.banana.messagedatabase.RecordOperations;
import com.banana.messagedatabase.RecordText;
import com.mkyong.android.R;

import android.app.Activity;
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
	        	buton_record.setBackgroundColor(Color.RED);
	        }else if(colorname.equals("blue")){
	        	layout.setBackgroundColor(Color.BLUE);
	        	buton_record.setBackgroundColor(Color.BLUE);
	        }else if(colorname.equals("yellow")){
	        	layout.setBackgroundColor(Color.YELLOW);
	        	buton_record.setBackgroundColor(Color.YELLOW);
	        }else if(colorname.equals("purple")){
	        	layout.setBackgroundColor(Color.rgb(185, 22, 250));
	        	buton_record.setBackgroundColor(Color.rgb(185, 22, 250));
	        }else if(colorname.equals("orange")){
	        	layout.setBackgroundColor(Color.rgb(253, 208, 23));
	        	buton_record.setBackgroundColor(Color.rgb(253, 208, 23));
	        }else if(colorname.equals("green")){
	        	layout.setBackgroundColor(Color.GREEN);
	        	buton_record.setBackgroundColor(Color.GREEN);
	        }
	        
	        
	        buton_record.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					p= new RecordText(choose,text.getText().toString());	
					op.open();
					   	   op.addPuan(p.getReminderTicket(), p.getReminderTextRecord());
					   	   Toast.makeText(Messagerecord.this, "record.", Toast.LENGTH_SHORT).show();
					   	 List<RecordText> a= op.getAllPuan();
					        for (RecordText recordText : a) {
					        	 Toast.makeText(Messagerecord.this, recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord(), Toast.LENGTH_SHORT).show();
					        	System.out.println(recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord());
							}
				}
			});
	        
	       
	        }
}

package com.banana.locations;

import com.mkyong.android.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Messagerecord  extends Activity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.messagerecord);
	        String colorname = getIntent().getExtras().get("color").toString();
	        System.out.println(getIntent().getExtras().get("color").toString());
	        System.out.println(getIntent().getExtras().get("choose").toString());
	        Button buton_record = (Button) findViewById(R.id.button_record);
	        LinearLayout layout = (LinearLayout) findViewById(R.id.notebackground);
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
	        
	        
	    }
}

package com.banana.locations;

import com.mkyong.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main_Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageButton btn_shop=(ImageButton) findViewById(R.id.imageButton1);
        btn_shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
				Main_Activity.this.finish();
			}
		});
        ImageButton btn_shop1=(ImageButton) findViewById(R.id.imageButton2);
        btn_shop1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
			}
		});
        ImageButton btn_atm=(ImageButton) findViewById(R.id.imageButton3);
        btn_atm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
			}
		});
        ImageButton btn_book=(ImageButton) findViewById(R.id.imageButton4);
        btn_book.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
			}
		});
        ImageButton btn_event=(ImageButton) findViewById(R.id.imageButton5);
        btn_event.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
			}
		});
        ImageButton btn_pharmacy=(ImageButton) findViewById(R.id.imageButton6);
        btn_pharmacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Main_Activity.this,Messagerecord.class);				
				startActivity(intent);
			}
		});
        
    }
}
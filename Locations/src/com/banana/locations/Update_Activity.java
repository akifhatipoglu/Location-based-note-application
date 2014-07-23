package com.banana.locations;

import com.banana.messagedatabase.RecordOperations;
import com.mkyong.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update_Activity extends Activity{
	
	private RecordOperations op;
	 String text;
	 String ticket;
	 EditText text_update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.update);
		 op = new RecordOperations(this);
		 text=getIntent().getExtras().get("updatetext").toString();
		 ticket=getIntent().getExtras().get("updateticket").toString();
		 text_update=(EditText) findViewById(R.id.textView_update);
		 text_update.setText(text);
		 
		 Button buton_update=(Button) findViewById(R.id.button_update);
		 buton_update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				op.open();
				op.deleteNotify(text);
				op.addPuan(ticket, text_update.getText().toString());		
				 Toast.makeText(Update_Activity.this, "Yeni hatırlatma kaydedildi.", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(Update_Activity.this,Main_Activity.class);	
				startActivity(intent);
				
			}
		});
		 
		 
	}
	
}

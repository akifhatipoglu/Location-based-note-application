package com.banana.locations;

import java.util.List;

import com.banana.messagedatabase.RecordOperations;
import com.banana.messagedatabase.RecordText;
import com.mkyong.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

public class Notifyalert_Activity extends Activity {
	final Context context = this;
	public RecordOperations op;
	public String delete_record;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.notifyalert);
	        op = new RecordOperations(this);
	         delete_record = getIntent().getExtras().get("delete").toString();
	        System.out.println(delete_record);
	        TextView view = (TextView) findViewById(R.id.textView_deleterecord);
	        view.setText("Kayıtlı Hatırlatma : "+delete_record);
	        op.open();
	        
	        
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

			alertDialogBuilder.setTitle("Hatırlatma");

			alertDialogBuilder
					.setMessage("Hatırlatma silinsin mi ?")
					.setCancelable(false)
					.setPositiveButton("Evet",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									op.deleteNotify(delete_record);
								   	   Toast.makeText(Notifyalert_Activity.this, "record.", Toast.LENGTH_SHORT).show();
								   	   List<RecordText> a= op.getAllPuan();
								        for (RecordText recordText : a) {
								        	 Toast.makeText(Notifyalert_Activity.this, recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord(), Toast.LENGTH_SHORT).show();
								        	System.out.println(recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord());
										}
								        Intent intent=new Intent(Notifyalert_Activity.this,Main_Activity.class);				
										startActivity(intent);   
								}
							})
					.setNegativeButton("Hayır",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									Notifyalert_Activity.this.finish();
								}
							});

			AlertDialog alertDialog = alertDialogBuilder.create();

			alertDialog.show();
	        
	        
	        
	        
	 }
}

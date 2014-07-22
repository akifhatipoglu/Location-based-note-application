package com.banana.locations;

import com.mkyong.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

public class Notifyalert_Activity extends Activity {
	final Context context = this;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.notifyalert);
	        String delete_record = getIntent().getExtras().get("delete").toString();
	        System.out.println(delete_record);
	        TextView view = (TextView) findViewById(R.id.textView_deleterecord);
	        view.setText("Kayıtlı Hatırlatma : "+delete_record);
	        
	        
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

			alertDialogBuilder.setTitle("Hatırlatma");

			alertDialogBuilder
					.setMessage("Hatırlatma silinsin mi ?")
					.setCancelable(false)
					.setPositiveButton("Evet",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									//MainActivity.this.finish();
									
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

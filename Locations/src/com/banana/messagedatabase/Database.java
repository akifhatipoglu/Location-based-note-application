package com.banana.messagedatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
	
	
	public static final String DATABASE_NAME = "ReminderRecord";
	public static final String TABLE_NAME = "remindertext";
	public static final String TABLE_ID = "_id";
	public static final String ReminderTextRecord = "remindertextrecord";
	public static final String ReminderTicket = "reminderticket";
	public static final int DATABASE_VERSION = 1;
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		String sql = "CREATE TABLE " + TABLE_NAME + " ( "
				+ " "+TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + ReminderTicket
				+ " TEXT, " + ReminderTextRecord + " TEXT "  + ");";
		arg0.execSQL(sql);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		Log.w(Database.class.getName(),
		        "Upgrading database from version " + arg1 + " to "
		            + arg2 + ", which will destroy all old data");
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
		    onCreate(arg0);
		
	}
	
	

}

package com.banana.messagedatabase;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class RecordOperations {
	
	private Database dbHelper;
	 private String[] DataBase_table_names = { Database.TABLE_ID,Database.ReminderTicket, Database.ReminderTextRecord };
	 private SQLiteDatabase database;
	 public RecordOperations(Context context) {	 
		         dbHelper = new Database(context);
		     }
	 public void open() throws SQLException {
		         database = dbHelper.getWritableDatabase();
		     }
	 public void close() {
		         dbHelper.close();
		     }

	 public RecordText addPuan(String  ReminderTicket,String ReminderTextRecord) {
         ContentValues values = new ContentValues();
         values.put(Database.ReminderTicket, ReminderTicket);
         values.put(Database.ReminderTextRecord, ReminderTextRecord);
         long insertId = database.insert(Database.TABLE_NAME,null, values);

         Cursor cursor = database.query(Database.TABLE_NAME,DataBase_table_names, Database.TABLE_ID + " = "+ insertId, null, null, null, null);
         cursor.moveToFirst();
         RecordText newPuan = parsePuan(cursor);
         cursor.close();
         return newPuan;
     }

	 public void deletePuan(RecordText p) {
		 long id = p.getId();
		 database.delete(Database.TABLE_NAME, Database.TABLE_ID + " = " + id, null);
	 }
	 public void deleteNotify(String deleterecord) {
		 List<RecordText> puanlist = new ArrayList<RecordText>();
		 Cursor cursor = database.query(Database.TABLE_NAME, DataBase_table_names, null, null, null, null, null);
		 cursor.moveToFirst();
		 double id=0;
		 while (!cursor.isAfterLast()) {
			 RecordText puan = parsePuan(cursor);
			 puanlist.add(puan);
			 if(puan.getReminderTextRecord().equals(deleterecord)){System.out.println(deleterecord); id=puan.getId();}
			 cursor.moveToNext();
		 }
		 cursor.close();
		 System.out.println(deleterecord);
		 database.delete(Database.TABLE_NAME,Database.TABLE_ID + " = " + id, null);
	 }
  
	 public List<RecordText> getAllPuan() {
		 List<RecordText> puanlist = new ArrayList<RecordText>();
		 Cursor cursor = database.query(Database.TABLE_NAME, DataBase_table_names, null, null, null, null, null);
		 cursor.moveToFirst();
		 while (!cursor.isAfterLast()) {
			 RecordText puan = parsePuan(cursor);
			 puanlist.add(puan);
			 cursor.moveToNext();
		 }
		 cursor.close();		
		 return puanlist;
	 }

private RecordText parsePuan(Cursor cursor) {		    	
RecordText student = new RecordText();
 student.setId((cursor.getInt(0)));
 student.setReminderTicket((cursor.getString(1)));
 student.setReminderTextRecord((cursor.getString(2)));
 
 return student;
}
	 
	 
}

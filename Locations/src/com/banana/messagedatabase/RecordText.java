package com.banana.messagedatabase;

import java.io.Serializable;

public class RecordText  implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private   String ReminderTextRecord ;
	private   String ReminderTicket ;
	
	public RecordText() {
	}

	public RecordText( String reminderTicket, String reminderTextRecord) {
		super();
		ReminderTextRecord = reminderTextRecord;
		ReminderTicket = reminderTicket;
	}
	
	public RecordText(RecordText r) {
		this.id = r.getId();
		this.ReminderTicket = r.getReminderTicket();
		this.ReminderTextRecord = r.getReminderTextRecord();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReminderTextRecord() {
		return ReminderTextRecord;
	}

	public void setReminderTextRecord(String reminderTextRecord) {
		ReminderTextRecord = reminderTextRecord;
	}

	public String getReminderTicket() {
		return ReminderTicket;
	}

	public void setReminderTicket(String reminderTicket) {
		ReminderTicket = reminderTicket;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

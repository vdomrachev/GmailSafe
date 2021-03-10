package com.dvv.gmailSafe.entities;

import java.util.Date;

public class Backup {
	public enum Status { IN_PROGRESS, OK, FAILED }
	String backupId;
	Date date;
	Status status;
	public Backup(String backupId, Date date, Status status) {
		super();
		this.backupId = backupId;
		this.date = date;
		this.status = status;
	}
	public String getBackupId() {
		return backupId;
	}
	public Date getDate() {
		return date;
	}
	public Status getStatus() {
		return status;
	}
}

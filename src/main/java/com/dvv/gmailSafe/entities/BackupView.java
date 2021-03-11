package com.dvv.gmailSafe.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.api.services.gmail.model.Message;

public class BackupView {
	String backupId;
	Date date;
	Backup.Status status;
	Set<Message> messages = new HashSet<>();
	public BackupView(String backupId, Date date, Backup.Status status, Set<Message> messages) {
		super();
		this.backupId = backupId;
		this.date = date;
		this.status = status;
		this.messages = messages;
	}
	public String getBackupId() {
		return backupId;
	}
	public Date getDate() {
		return date;
	}
	public Backup.Status getStatus() {
		return status;
	}
	public void setStatus(Backup.Status status) {
		this.status = status;
	}
	public Set<Message> getMessages() {
		return messages;
	}
}

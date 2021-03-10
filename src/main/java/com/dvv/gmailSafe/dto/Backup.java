package com.dvv.gmailSafe.dto;

public class Backup {
	private long backupId;

	public Backup(long backupId) {
		super();
		this.backupId = backupId;
	}

	public long getBackupId() {
		return backupId;
	}
}

package com.dvv.gmailSafe.backups.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.dvv.gmailSafe.entities.Backup;

public enum BackupController {
	INSTANCE;
	private Set<Backup> backups = new HashSet<>();
	
	public String start() {
		String id = UUID.randomUUID().toString();
		Date now = new Date();
		Backup backup = new Backup(id, now, Backup.Status.IN_PROGRESS);
		backups.add(backup);
		return id;
	}

	public Backup[] getAll() {
		return backups.toArray(new Backup[backups.size()]);
	}
}

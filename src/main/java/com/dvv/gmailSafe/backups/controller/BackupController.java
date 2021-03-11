package com.dvv.gmailSafe.backups.controller;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.dvv.gmailSafe.entities.Backup;
import com.dvv.gmailSafe.entities.BackupView;
import com.dvv.gmailSafe.google.GmailSimpleController;

public enum BackupController {
	INSTANCE;
	
	private Set<Backup> backups = ConcurrentHashMap.newKeySet();
	
	public String start() {
		final String id = UUID.randomUUID().toString();
		Runnable task = () -> { 
			System.out.println(String.format("Backup task %s is running", id)); 
			Date now = new Date();
			Backup backup = new Backup(id, now, Backup.Status.IN_PROGRESS);
			backups.add(backup);
			try {
				if (GmailSimpleController.INSTANCE.load(backup)) {
					backup.setStatus(Backup.Status.OK);
				} else {
					backup.setStatus(Backup.Status.FAILED);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(String.format("Backup task %s failed", id)); 
				backup.setStatus(Backup.Status.FAILED);
			}
			System.out.println(String.format("Backup task %s finished", id)); 
		};
		new Thread(task).start();		
		return id;
	}

	public Backup[] getAll() {
		return backups.toArray(new Backup[backups.size()]);
	}

	public BackupView get(String id) {
		Backup backup = backups.stream().filter(x->x.getBackupId().equals(id)).findFirst().get();
		if (backup != null && Backup.Status.OK.equals(backup.getStatus())) {
			return new BackupView(backup.getBackupId(), backup.getDate(), backup.getStatus(), backup.getMessages());
		}
		return null;
	}
}

package com.dvv.gmailSafe.controller.backup;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.dvv.gmailSafe.InjectByType;
import com.dvv.gmailSafe.Singleton;
import com.dvv.gmailSafe.entities.Backup;
import com.dvv.gmailSafe.entities.BackupView;
import com.dvv.gmailSafe.google.GmailController;

@Singleton
public class BackupController {
	
	private Map<String, Backup> backups = new ConcurrentHashMap<String, Backup>();
	
	@InjectByType
	private GmailController gmailController;
	
	public String start() {
		final String id = UUID.randomUUID().toString();
		Runnable task = () -> { 
			System.out.println(String.format("Backup task %s is running", id)); 
			Date now = new Date();
			Backup backup = new Backup(id, now);
			backup.setStatus(Backup.Status.IN_PROGRESS);
			backups.put(id, backup);
			try {
				if (gmailController.load(backup)) {
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
		return backups.values().toArray(new Backup[0]);
	}

	public BackupView get(String id) {
		Backup backup = backups.get(id);
		if (backup != null && Backup.Status.OK.equals(backup.getStatus())) {
			return new BackupView(backup.getBackupId(), backup.getDate(), backup.getStatus(), backup.getMessages());
		}
		return null;
	}
}

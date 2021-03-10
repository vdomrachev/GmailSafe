package com.dvv.gmailSafe.controllers;

import java.util.Random;

import com.dvv.gmailSafe.dto.Backup;

public enum BackupController {
	INSTANCE;
	public Backup getBackup(long id) {
		return new Backup(new Random().nextLong());
	}
}

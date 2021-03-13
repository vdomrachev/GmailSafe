package com.dvv.gmailSafe.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.api.services.gmail.model.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Backup {
	public enum Status { IN_PROGRESS, OK, FAILED }
	final String backupId;
	final Date date;
	Status status;
	transient Set<Message> messages = new HashSet<>();
}

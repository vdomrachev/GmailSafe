package com.dvv.gmailSafe;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.dvv.gmailSafe.entities.Backup;
import com.dvv.gmailSafe.google.GmailController;
import com.google.api.services.gmail.model.Message;

public class GmailControllerMock implements GmailController {

	@Override
	public boolean load(Backup backup) throws IOException, GeneralSecurityException {
		backup.getMessages().add(new Message());
		return true;
	}
}

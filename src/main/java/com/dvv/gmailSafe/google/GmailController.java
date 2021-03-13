package com.dvv.gmailSafe.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.dvv.gmailSafe.entities.Backup;

public interface GmailController {

	boolean load(Backup backup) throws IOException, GeneralSecurityException;

}
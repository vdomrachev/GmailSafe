package com.dvv.gmailSafe.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.dvv.gmailSafe.google.service.GmailCredentials;
import com.dvv.gmailSafe.google.service.GmailService;
import com.dvv.gmailSafe.google.service.GmailServiceImpl;

public class GmailController {
	public static void main(String[] args) {
        try {
            GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
            gmailService.setGmailCredentials(
            		new GmailCredentials("fairo10@gmail.com", 
            				"182175281326-lr9uqlu7cnptj39umvsmbd4dcvp1mn7c.apps.googleusercontent.com",
            				"TrBDn1yhB2r1U6ubTvFyLl-8",
            				"ya29.a0AfH6SMCKNGgffOgM--5tsiP-40Z5DhBfJAp8z9qiuq_tJVRghZ-65FX4vhMp0XsaJrnbiEpWRFWHyYApdf9d4HnTsxnCE3xdIpXTwvCOUyXBNBMx8niXtLfHrjlxoi8uq9AZrGrsmPzZp1eCniWrBL770v08",
            				"1//0cju99wTWXnBsCgYIARAAGAwSNwF-L9Ir4wkIhY6a6O2xhFhAPNy7pugEhZ1D9CYW4DH8wu8FkaBVxP-IXBkFI7v6rFiR2qBGXyY"));

            gmailService.sendMessage("RECIPIENT_EMAIL@gmail.com", "Subject", "body text");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
	}
}

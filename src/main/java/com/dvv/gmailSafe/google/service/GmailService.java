package com.dvv.gmailSafe.google.service;

import java.io.IOException;

public interface GmailService {
    void setGmailCredentials(GmailCredentials gmailCredentials);

	boolean sendMessage(String string, String string2, String string3) throws IOException;
}

package com.dvv.gmailSafe.google.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.StringUtils;

public final class GmailServiceImpl implements GmailService {

    private static final String APPLICATION_NAME = "YOUR_APP_NAME";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final HttpTransport httpTransport;
    private GmailCredentials gmailCredentials;

    public GmailServiceImpl(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    @Override
    public void setGmailCredentials(GmailCredentials gmailCredentials) {
        this.gmailCredentials = gmailCredentials;
    }

    @Override
    public boolean sendMessage(String recipientAddress, String subject, String body) throws IOException {
    	Gmail service = createGmail();
    	
    	ListMessagesResponse response = service.users().messages().list("me").execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null && messages.size() < 100) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list("me").setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            System.out.println(message.getId());
            Message test = service.users().messages().get("me", message.getId()).setFormat("full").execute();
            System.out.println(StringUtils.newStringUtf8(Base64.decodeBase64(test.getPayload().getBody().getData())));
            System.out.println(test.getSnippet());
        }    	
    	return true;
    }

    private Gmail createGmail() {
        Credential credential = authorize();
        return new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

//    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        emailContent.writeTo(buffer);
//
//        return new Message()
//                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
//    }

    private Credential authorize() {
        return new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(gmailCredentials.getClientId(), gmailCredentials.getClientSecret())
                .build()
                .setAccessToken(gmailCredentials.getAccessToken())
                .setRefreshToken(gmailCredentials.getRefreshToken());
    }
}

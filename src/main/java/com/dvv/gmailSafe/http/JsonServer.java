package com.dvv.gmailSafe.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.dvv.gmailSafe.entities.Backup;
import com.dvv.gmailSafe.entities.BackupView;
import com.dvv.gmailSafe.google.GmailSimpleController;
import com.dvv.gmailSafe.http.controller.JsonResponseController;
import com.dvv.gmailSafe.http.controller.RequestRouteController;
import com.dvv.gmailSafe.http.controller.ResponseController;
import com.dvv.gmailSafe.http.controller.ZipExportController;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

public class JsonServer {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;
    private static final int BACKLOG = 1;


    private static final Charset CHARSET = StandardCharsets.UTF_8;


    private static final String CONTEXT = "/gmail-safe";

    public static void main(final String... args) throws IOException {
    	String credentialsFilePath = null;
    	if (args.length > 0) {
    		credentialsFilePath = args[0];
    	} else {
    		return;
    	}
    	//TODO DI
    	GmailSimpleController.INSTANCE.setCredentialsFilePath(credentialsFilePath);
    	
        final HttpServer server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
        server.createContext(CONTEXT, he -> {
            try {
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
				switch (requestMethod) {
                    case Constants.METHOD_GET:
                    case Constants.METHOD_POST:
                        String path = he.getRequestURI().getRawPath().replaceFirst(CONTEXT, "");
                        
                        // TODO DI
                        Object result = new RequestRouteController().process(requestMethod, path);
                        
                        if (result instanceof BackupView) {
                        	// TODO DI
                        	byte[] data = new JsonWriter().write(result).getBytes(CHARSET);

                        	// TODO DI
                        	new ZipExportController().export(he, data);

                        } else {
                            // TODO DI
                            ResponseController responseController = new JsonResponseController();
                            final String responseBody = responseController.getResponse(result);
                            
                            headers.set(Constants.HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                            final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                            he.sendResponseHeaders(Constants.STATUS_OK, rawResponseBody.length);
                            he.getResponseBody().write(rawResponseBody);
                        }
                        break;
                }
            } finally {
                he.close();
            }
        });
        server.start();
    }
}
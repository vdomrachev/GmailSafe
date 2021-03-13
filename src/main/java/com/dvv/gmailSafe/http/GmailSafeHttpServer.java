package com.dvv.gmailSafe.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.dvv.gmailSafe.InjectByType;
import com.dvv.gmailSafe.controller.ExportController;
import com.dvv.gmailSafe.controller.StringResultWriter;
import com.dvv.gmailSafe.controller.ZipExportController;
import com.dvv.gmailSafe.entities.BackupView;
import com.dvv.gmailSafe.http.controller.JsonResponseController;
import com.dvv.gmailSafe.http.controller.RequestRouteController;
import com.dvv.gmailSafe.http.controller.ResponseController;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

public class GmailSafeHttpServer {
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 8080;
	private static final int BACKLOG = 1;


	private static final String CONTEXT = "/gmail-safe";

	@InjectByType
	RequestRouteController requestRouteController; 
	
	@InjectByType
	StringResultWriter writer;
	
	@InjectByType
	ExportController exportController;
	
	@InjectByType
	ResponseController responseController;
	
	public void start() throws IOException {
    	
        HttpServer server = null;
		server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
        server.createContext(CONTEXT, he -> {
            try {
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
                String path = he.getRequestURI().getRawPath().replaceFirst(CONTEXT, "");
                        
                Object result = requestRouteController.process(requestMethod, path);
                        
                if (result instanceof BackupView) {
                	String data = writer.write(result);

                    headers.set(Constants.HEADER_CONTENT_TYPE, exportController.getContentType());
                    headers.set("Content-Disposition", String.format("attachment; filename=%s", exportController.getFilename()));
                    he.sendResponseHeaders(Constants.STATUS_OK, 0);
                    exportController.export(he.getResponseBody(), new ByteArrayInputStream(data.getBytes()));

                } else {
                    final String responseBody = responseController.getResponse(result);
                            
                    headers.set(Constants.HEADER_CONTENT_TYPE, responseController.getContentType());
                    final byte[] rawResponseBody = responseBody.getBytes(Constants.CHARSET);
                    he.sendResponseHeaders(Constants.STATUS_OK, rawResponseBody.length);
                    he.getResponseBody().write(rawResponseBody);
                }
            } finally {
                he.close();
            }
        });
        server.start();
}}
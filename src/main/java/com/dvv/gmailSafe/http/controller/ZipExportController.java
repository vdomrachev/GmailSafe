package com.dvv.gmailSafe.http.controller;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.dvv.gmailSafe.http.Constants;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class ZipExportController {
	public void export(HttpExchange he, byte[] data) throws IOException {
		Headers headers = he.getResponseHeaders();
        headers.set(Constants.HEADER_CONTENT_TYPE, "application/zip");
        headers.set("Content-Disposition", "attachment; filename=data.zip");
        he.sendResponseHeaders(Constants.STATUS_OK, 0);
        try ( ZipOutputStream zos = new ZipOutputStream(he.getResponseBody()) ) {
        	ZipEntry e = new ZipEntry("backup.json");
        	zos.putNextEntry(e);

        	zos.write(data, 0, data.length);
        	zos.closeEntry();
        } catch (IOException e) {
        	e.printStackTrace();
        	throw e;
        }
	}

}

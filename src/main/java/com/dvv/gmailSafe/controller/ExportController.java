package com.dvv.gmailSafe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ExportController {

	void export(OutputStream outputStream, InputStream inputStream) throws IOException;
	
	String getContentType();

	String getFilename();

}
package com.dvv.gmailSafe.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.dvv.gmailSafe.http.Constants;

public class ZipExportController implements ExportController {
	protected static final String BACKUP_FILENAME = "backup.json";

	@Override
	public String getContentType() {
		return Constants.APPLICATION_ZIP;
	}

	@Override
	public String getFilename() {
		return "data.zip";
	}
	
	@Override
	public void export(OutputStream outputStream, InputStream inputStream) throws IOException {
       	InputStream zippedInputStream = compress(inputStream, BACKUP_FILENAME);
       	zippedInputStream.transferTo(outputStream);
	}
	
	private InputStream compress(InputStream in, String entryName) throws IOException {
        final int BUFFER = 2048;
        byte buffer[] = new byte[BUFFER];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(out);
        zos.putNextEntry(new ZipEntry(entryName));
        int length;
        while ((length = in.read(buffer)) >= 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        zos.close();
        return new ByteArrayInputStream(out.toByteArray());
	}
}

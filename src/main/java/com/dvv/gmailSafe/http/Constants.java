package com.dvv.gmailSafe.http;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {
	
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final int STATUS_OK = 200;
	public static final String APPLICATION_ZIP = "application/zip";
	public static final Charset CHARSET = StandardCharsets.UTF_8;
	
	private Constants() {};
}

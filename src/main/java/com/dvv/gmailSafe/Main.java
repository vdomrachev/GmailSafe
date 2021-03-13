package com.dvv.gmailSafe;

import java.io.IOException;
import java.util.HashMap;
import com.dvv.gmailSafe.http.GmailSafeHttpServer;

public class Main {

	public static void main(String[] args) throws IOException {
		String propertiesFilePath = null;
		if (args.length > 0) {
			propertiesFilePath = args[0];
		} else {
			return;
		}

		ApplicationContext context = Application.run("com.dvv", propertiesFilePath, 
				new HashMap<>());
		
		// new HashMap<>(Map.of(StringResultWriter.class, StringResultWriterMock.class)) for unit tests mock
		GmailSafeHttpServer httpServer = context.getObject(GmailSafeHttpServer.class);
		httpServer.start();
	}
}

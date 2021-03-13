package com.dvv.gmailSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dvv.gmailSafe.controller.backup.BackupController;
import com.dvv.gmailSafe.google.GmailController;

public class BackupControllerTest {
	@DisplayName("Test BackupController.create()")
    @Test
    void testGet() throws IOException, InterruptedException {
		// may user Mockito here
		ApplicationContext context = Application.run("com.dvv", null, 
				new HashMap<>(Map.of(GmailController.class, GmailControllerMock.class)));
		BackupController backupController = context.getObject(BackupController.class);
		String id = backupController.start();
		Thread.sleep(100);
		assertNotNull(backupController.get(id));
		assertEquals(1, backupController.getAll().length);
    }
}

package com.dvv.gmailSafe.http.controller;

import java.util.List;
import java.util.Map;

import com.dvv.gmailSafe.backups.controller.BackupController;
import com.dvv.gmailSafe.entities.BackupId;
import com.dvv.gmailSafe.http.Constants;

public class RequestController {

	private static final String BACKUPS = "/backups";

	public Object process(String requestMethod, String path, Map<String, List<String>> requestParameters) {
		Object response = null;
		switch (requestMethod) {
        	case Constants.METHOD_GET:
        		switch (path) {
        			case BACKUPS:
        				response = BackupController.INSTANCE.getAll();
        				break;
        		}
        		break;
        	case Constants.METHOD_POST:
        		switch (path) {
        			case BACKUPS:
        				String id = BackupController.INSTANCE.start();
        				response = new BackupId(id);
        				break;
        		}
        		break;
		}
		return response;
	}
}

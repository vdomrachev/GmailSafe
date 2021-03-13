package com.dvv.gmailSafe.http.controller;

import com.dvv.gmailSafe.InjectByType;
import com.dvv.gmailSafe.controller.backup.BackupController;
import com.dvv.gmailSafe.entities.BackupId;
import com.dvv.gmailSafe.http.Constants;

public class RequestRouteController {

	private static final String EXPORT = "/export/";
	private static final String BACKUPS = "/backups";

	@InjectByType
	private BackupController controller;
	
	public Object process(String requestMethod, String path) {
		Object response = null;
		switch (requestMethod) {
        	case Constants.METHOD_GET:
        		switch (path) {
        			case BACKUPS:
        				response = controller.getAll();
        				break;
        		}
        		
        		if (path!=null && path.startsWith(EXPORT)) {
        			String id = path.substring(path.indexOf(EXPORT) + EXPORT.length());
        			if (id != null && !id.isBlank()) {
        				response = controller.get(id);
        			}
        		}
        		
        		break;
        	case Constants.METHOD_POST:
        		switch (path) {
        			case BACKUPS:
        				String id = controller.start();
        				response = new BackupId(id);
        				break;
        		}
        		break;
		}
		return response;
	}
}

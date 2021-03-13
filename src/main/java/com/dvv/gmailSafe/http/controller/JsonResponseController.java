package com.dvv.gmailSafe.http.controller;

import com.dvv.gmailSafe.controller.JsonWriter;
import com.dvv.gmailSafe.http.Constants;

public class JsonResponseController implements ResponseController {
	@Override
	public String getResponse(Object obj) {
		return new JsonWriter().write(obj);
	}

	@Override
	public String getContentType() {
		return String.format("application/json; charset=%s", Constants.CHARSET);
	}
}

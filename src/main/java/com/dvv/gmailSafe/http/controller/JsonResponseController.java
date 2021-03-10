package com.dvv.gmailSafe.http.controller;

import com.dvv.gmailSafe.http.JsonWriter;
import com.dvv.gmailSafe.http.entities.Response;

public class JsonResponseController implements ResponseController {
	@Override
	public String getResponse(Object obj) {
		Response response = new Response(200, obj);
		return new JsonWriter().write(response);
	}
}

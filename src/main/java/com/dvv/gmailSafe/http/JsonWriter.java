package com.dvv.gmailSafe.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {

	private Gson gson = new GsonBuilder().create();
	
	public String write(Object obj) {
		return gson.toJson(obj);
	}
	
}

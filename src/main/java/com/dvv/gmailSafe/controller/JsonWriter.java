package com.dvv.gmailSafe.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter implements StringResultWriter {

	private Gson gson = new GsonBuilder().create();
	
	@Override
	public String write(Object obj) {
		return gson.toJson(obj);
	}
	
}

package com.example.androidnio;

import org.json.JSONException;
import org.json.JSONObject;

public class HeartBeat extends Head {
	public HeartBeat() {
		type = 1;
	}

	public String toJsonString() {
		JSONObject jsObj = new JSONObject();
		try {
			jsObj.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj.toString();
	}
}

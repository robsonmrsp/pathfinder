package br.com.mr.pathfinder.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class LatLongJson extends JSONObject {

	public LatLongJson(JSONValue jso) {
		this(jso.isObject().getJavaScriptObject());
	}

	public LatLongJson(Double lat, Double lon) {
		super();
		setLatitude(lat);
		setLongitude(lon);
	}

	public LatLongJson(JavaScriptObject jso) {
		super(jso);
	}

	public void setLatitude(Double latitude) {
		put("latitude", JsonUtil.asJsonNumber(latitude));
	}

	public Double getLongitude() {
		return JsonUtil.asDouble(get("longitude"));
	}

	public void setLongitude(Double longitude) {
		put("longitude", JsonUtil.asJsonNumber(longitude));
	}
}

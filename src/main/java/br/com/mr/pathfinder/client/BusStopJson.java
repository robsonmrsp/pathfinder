package br.com.mr.pathfinder.client;

import br.com.mr.pathfinder.client.json.JsonUtil;
import br.com.mr.pathfinder.client.json.LatLongJson;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class BusStopJson extends JSONObject {

	public BusStopJson(JSONValue jso) {
		this(jso.isObject().getJavaScriptObject());
	}

	public BusStopJson(JavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	public Long getId() {
		return JsonUtil.asLong(get("id"));
	}

	public void setId(Long id) {
		put("id", JsonUtil.asJsonNumber(id));
	}

	public String getDescription() {
		return JsonUtil.asString(get("description"));
	}

	public void setDescription(String description) {

	}

	public String getAddressLocation() {
		return JsonUtil.asString(get("addresslocation"));
	}

	public void setAddressLocation(String addressLocation) {
		put("addresslocation", JsonUtil.asJsonString(addressLocation));
	}

	public LatLongJson getGeoLocation() {
		return new LatLongJson(get("geoLocation"));
	}

	public void setGeoLocation(LatLongJson geoLocation) {
		put("geoLocation", geoLocation);
	}
}

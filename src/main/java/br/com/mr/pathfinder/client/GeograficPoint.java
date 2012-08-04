package br.com.mr.pathfinder.client;

import com.google.gwt.json.client.JSONObject;
public final class GeograficPoint extends JSONObject{

	protected GeograficPoint() {
	}

	public final native Double getLatitude() /*-{
		return this.latitude;
	}-*/;

	public final native void setLatitude(Double latitude) /*-{
		this.latitude = latitude;
	}-*/;

	public final native Double getLongitude() /*-{
		return this.longitude;
	}-*/;

	public final native void setLongitude(Double longitude) /*-{
		this.longitude = longitude;
	}-*/;

}

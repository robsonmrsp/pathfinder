package br.com.mr.pathfinder.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.ui.Widget;

public interface MapContainer {

	void addOverlay(Overlay overlay);

	void setCenter(LatLng center);

	Widget getMap();

}

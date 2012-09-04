package br.com.mr.pathfinder.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.SmallZoomControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MapPanel extends FlowPanel implements MapContainer {
	private MapWidget map;

	public MapPanel(double latitude, double longitude, int zoomLevel) {
		map = new MapWidget(LatLng.newInstance(latitude, longitude), zoomLevel);
		initComponents();
	}

	public MapPanel() {
		this(-17.2, -57.2, 4);
	}

	private void initComponents() {
		map.setScrollWheelZoomEnabled(true);
		map.setSize(Window.getClientWidth() - 20 + "px", Window.getClientHeight() - 20 + "px");
		map.addControl(new SmallZoomControl());
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				updateMapSize();
			}
		});
		add(map);
	}

	private void updateMapSize() {
		map.setSize(Window.getClientWidth() - 20 + "px", Window.getClientHeight() - 20 + "px");
	}

	@Override
	public void addOverlay(Overlay overlay) {
		map.addOverlay(overlay);
	}

	@Override
	public void setCenter(LatLng center) {
		map.setCenter(center);
	}

	@Override
	public Widget getMap() {
		return map;
	}

	public void addMapRightClickHandler(MapRightClickHandler handler) {
		map.addMapRightClickHandler(handler);
	}

	public void addMapClickHandler(MapClickHandler handler) {
		map.addMapClickHandler(handler);
	}

	public LatLng getEquivalentLatLng(Point point) {
		return map.convertContainerPixelToLatLng(point);
	}

	public void addMapDoubleClickHandler(MapDoubleClickHandler handler) {
		map.addMapDoubleClickHandler(handler);
	}

	public void setZoomLevel(int level) {
		map.setZoomLevel(level);
	}
}
package br.com.mr.pathfinder.client;

import java.util.List;

import br.com.mr.pathfinder.client.json.JsonUtil;
import br.com.mr.pathfinder.client.json.LatLongJson;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class Principal extends Composite {
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	private String url = null;// GWT.getModuleBaseURL() +
								// "ws/finder/findpoints";
	private String url2 = null;// GWT.getHostPageBaseURL() +
								// "ws/finder/findpoints";
	private RequestBuilder busStopsRequestBuilder = null;// new
															// RequestBuilder(RequestBuilder.POST,
															// URL.encode(url));

	private MapPanel mapPanel;
	private Point location;
	private LatLng geoLocation;

	final private PopupPanel popupPanel = new PopupPanel(true);

	public Principal() {
		initComponents();
		createPopupMenu();
		initRequestBuilders();
	}

	private void initRequestBuilders() {
		url = GWT.getModuleBaseURL() + "ws/finder/findpoints";
		url2 = GWT.getHostPageBaseURL() + "ws/finder/findpoints";
		busStopsRequestBuilder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		busStopsRequestBuilder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
		busStopsRequestBuilder.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (response.getText() != null) {
					long l = System.currentTimeMillis();
					System.err.println(response.getText());
					List<JSONObject> asList = JsonUtil.asList(JsonUtil.noSafeEval(response.getText()));
					for (JSONObject busStopJson : asList) {
						BusStopJson b = new BusStopJson(busStopJson);
						// System.out.println(b);
						LatLongJson geoLocation2 = b.getGeoLocation();
						processPoint(b);
					}
					
					Icon icon = Icon.newInstance("http://cdn1.iconfinder.com/data/icons/Aristocracy_WebDesignTuts/32/Marker.png");
//					Icon icon = Icon.newInstance("http://cdn1.iconfinder.com/data/icons/iconslandgps/PNG/32x32/Pinpoints/Flag3RightBlue2.png");
					icon.setIconAnchor(Point.newInstance(16, 32));
					MarkerOptions options = MarkerOptions.newInstance(icon);
					
					Marker marker = new Marker(geoLocation, options);
					mapPanel.addOverlay(marker);
					
					System.out.println("processados " + asList.size() + " em " + (System.currentTimeMillis() - l) + "ms");
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				System.out.println("Principal.requestBusStops(...).new RequestCallback() {...}.onError()");
			}
		});

	}

	private void initComponents() {
		initWidget(getMapPanel());
	}

	private FlowPanel getMapPanel() {
		if (mapPanel == null) {
			mapPanel = new MapPanel();
			mapPanel.addMapRightClickHandler(new MapRightClickHandler() {
				@Override
				public void onRightClick(MapRightClickEvent event) {
					location = event.getPoint();
					geoLocation = mapPanel.getEquivalentLatLng(location);
					showMenu(event.getPoint());
				}
			});

			Geolocation geolocation = Geolocation.getIfSupported();
			if (geolocation != null) {
				geolocation.getCurrentPosition(new Callback<Position, PositionError>() {
					@Override
					public void onSuccess(Position result) {
						mapPanel.setCenter(LatLng.newInstance(result.getCoordinates().getLatitude(), result.getCoordinates().getLongitude()));
						mapPanel.setZoomLevel(13);
					}

					@Override
					public void onFailure(PositionError reason) {
						System.out.println(reason.getMessage());
					}
				});
			} else {
				System.out.println("GeoLocation not available!");
			}
		}
		return mapPanel;
	}

	protected void showMenu(Point point) {
		popupPanel.setPopupPosition(point.getX(), point.getY());
		popupPanel.show();
	}

	Command showBusStopCommand = new Command() {
		public void execute() {
			try {
				requestBusStops(geoLocation);
			} catch (RequestException e) {
				e.printStackTrace();
			}
			popupPanel.hide();
		}
	};
	Command showPharmaCommand = new Command() {
		public void execute() {

			popupPanel.hide();
		}
	};
	Command showMotelCommand = new Command() {
		public void execute() {

			popupPanel.hide();
		}
	};

	private void createPopupMenu() {
		MenuBar popupMenuBar = new MenuBar(true);

		MenuItem alertItem = new MenuItem("Pontos de onibus", true, showBusStopCommand);
		MenuItem imageItem = new MenuItem("Farmácias ", true, showPharmaCommand);
		MenuItem sponserItem = new MenuItem("Moteis ", true, showMotelCommand);

		popupPanel.setStyleName("popup");

		alertItem.addStyleName("popup-item");
		imageItem.addStyleName("popup-item");
		sponserItem.addStyleName("popup-item");

		popupMenuBar.addItem(alertItem);
		popupMenuBar.addItem(imageItem);
		popupMenuBar.addItem(sponserItem);

		popupMenuBar.setVisible(true);
		popupPanel.add(popupMenuBar);
	}

	private void requestBusStops(LatLng geoLocation) throws RequestException {
		LatLongJson latLongJson = new LatLongJson(geoLocation.getLatitude(), geoLocation.getLongitude());
		System.out.println(latLongJson.toString());
		busStopsRequestBuilder.setRequestData(latLongJson.toString());
		busStopsRequestBuilder.send();
	}

	private void processPoint(BusStopJson p) {
		
		Icon icon = Icon.newInstance("http://cdn1.iconfinder.com/data/icons/iconslandgps/PNG/32x32/Pinpoints/Flag3RightBlue2.png");
		icon.setIconAnchor(Point.newInstance(4, 32));
		MarkerOptions mOpts = MarkerOptions.newInstance(icon);

		Marker overlay = new Marker(LatLng.newInstance(p.getGeoLocation().getLatitude(), p.getGeoLocation().getLongitude()), mOpts);
		
		mapPanel.addOverlay(overlay);
	}

	private void updateBusStops(List<BusStopJson> busStopJsons) {

	}

}

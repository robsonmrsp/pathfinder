package br.com.mr.pathfinder.server;

import java.io.Serializable;

public class BusStopTO implements Serializable {
	private static final long serialVersionUID = -6077419282949101022L;
	private Long id;
	private String description;
	private String addressLocation;
	private PointTO geoLocation;

	public BusStopTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddressLocation() {
		return addressLocation;
	}

	public void setAddressLocation(String addressLocation) {
		this.addressLocation = addressLocation;
	}

	public PointTO getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(PointTO geoLocation) {
		this.geoLocation = geoLocation;
	}

}

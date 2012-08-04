package br.com.mr.pathfinder.server;

import java.io.Serializable;

public class GeograficPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3842570417416731184L;
	private Double latitude;
	private Double longitude;

	public GeograficPoint() {}

	public GeograficPoint(Double latitude, Double longitude) {
		this();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
